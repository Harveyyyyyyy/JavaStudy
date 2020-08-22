


//15min 讲解 PPt 类比报告  
//开学讲 录视频

//socket和io通信难点 把love当作一个对象类 把整个对象传过去是否可行？————
//                  把每次画出的一段曲线即点的集合传过去是否可行？
//集合保存难点
//Point 试2020,8,18 Line 完成Socket测试
// Point 2020,8,19 From Line to Love 完成Socket通信 Pixel 的传输
//最小化打开不能画出来 2020，8，20 (Problem) 
//@Harveyyyyyyy
import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
public class LoveJFrame extends JFrame implements ActionListener,CaretListener,
WindowListener,Runnable{
	private JButton[] buttons;
	private JButton readButton,writeButton;
	private Color color;
	private JTextField[] text;
	private JTextField sleeptime;
	private LoveCanvas lovecanvas;
	private MessageJDialog jdialog;
	private ArrayList<Pixel> list;
	private Socket socket;
	private Thread socketThread;
	private ObjectOutputStream objout;
    private int i=0;
    private Point lovepoint=new Point(),endpoint;
	public JPanel cmdpanel,sleeppanel;
	String[] textstr= {"大小","位置x:","y:","缩放比例","旋转角度"};
	String[] buttonstr= {"选择颜色","启动","中断"};
	String[] numstr= {"7","0","0","10","4.7"};
	public LoveJFrame(String name) throws IOException {
		super(name);
		Dimension dim =this.getToolkit().getScreenSize();
		this.setBounds(dim.width/4, dim.height/4, dim.width/2, dim.height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.cmdpanel=new JPanel();
		this.sleeppanel=new JPanel();
		this.getContentPane().add(cmdpanel,"North");
		this.getContentPane().add(sleeppanel,"South");
		sleeppanel.add(new JLabel("sleeptime"));
		sleeppanel.add(sleeptime=new JTextField("5",10));
		sleeppanel.add(this.writeButton=new JButton("保存"));
		sleeppanel.add(this.readButton=new JButton("打开"));
		this.writeButton.addActionListener(this);
		this.readButton.addActionListener(this);
		this.buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttonstr.length;i++) {
			cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
			this.buttons[i].addActionListener(this);
		}
		this.text=new JTextField[textstr.length];
		for(int i=0;i<textstr.length;i++) {
			cmdpanel.add(new JLabel(textstr[i]));
			cmdpanel.add(this.text[i]=new JTextField(numstr[i],3));
			this.text[i].addCaretListener(this);
		}
		cmdpanel.add(new JLabel("PI"));
		lovecanvas=new LoveCanvas();
		this.lovecanvas.setBackground(Color.WHITE);
		this.color=Color.red;
		this.getContentPane().add(this.lovecanvas,"Center");
		this.setVisible(true);
		this.jdialog=new MessageJDialog();
		this.addWindowListener(this);
		this.objout=null;
		this.list=new ArrayList<Pixel>(1000000);
	}
	
	public LoveJFrame(String name,String host,int port) throws IOException{
		this(name);
		Socket socket=new Socket(host,port);
		this.setTitle(this.getTitle()+":"+socket.getLocalPort());
		this.setSocket(socket);
	}
	
	public void setSocket(Socket socket) throws IOException{
		this.socket=socket;
		this.objout=new ObjectOutputStream(this.socket.getOutputStream());
		this.socketThread=new Thread(this);
		this.socketThread.start();
	}
	
	//对按钮的事件处理
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()==this.buttons[0]) {
		Color color=JColorChooser.showDialog(this, "选择颜色", this.color);
		if(color!=null) {
			this.color=color;
		}
		}
	    if(ev.getSource()==this.buttons[1]) {
			if(lovecanvas.thread.getState()!=Thread.State.NEW)
				lovecanvas.thread=new Thread(this);
			lovecanvas.thread.start();
			this.buttons[1].setEnabled(false);
			this.buttons[2].setEnabled(true);
	    }
	    if(ev.getSource()==this.buttons[2]) {
	    	lovecanvas.thread.interrupt();
			this.buttons[1].setEnabled(true);
			this.buttons[2].setEnabled(false);
			this.print();
		}
	    if(ev.getSource()==this.writeButton) 
	    	CollectionFile.writeTo(getTitle(),this.list);  //保存  
	    if(ev.getSource()==this.readButton) {
	    	CollectionFile.readFrom(getName(), this.list);//打开时要重画 读取对象
	    	this.lovecanvas.repaint();
	    }
	    }	
	//异常处理 输入格式错误之类
	public void caretUpdate(CaretEvent ev) {
		for(int i=0;i<textstr.length;i++) {
			if(text[i].getText().isEmpty()||text[i].getText()=="") {
				this.jdialog.show("该空不能为空");
			}else if(Double.parseDouble(text[i].getText())<0){
				this.jdialog.show("该空不能为负数");
			}else
				try {
					double x=Double.parseDouble(text[i].getText());
				}catch(NumberFormatException ex) {
					this.jdialog.show("\""+text[i].getText()+"\"不能转换成浮点数.");
				}
		}
			
	}
	//对话框类
	private class MessageJDialog extends JDialog{
		private JLabel jlabel;
		private MessageJDialog() {
			super(LoveJFrame.this,"提示",true);
			this.setSize(420, 110);
			this.jlabel=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(this.jlabel);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		private void show(String message) {
			this.jlabel.setText(message);
			this.setLocation(LoveJFrame.this.getX()+100, LoveJFrame.this.getY()+100);
			this.setVisible(true);
		}
	}
	//画布
	 public class LoveCanvas extends Canvas implements Runnable {
		Thread thread=new Thread(this);
		int x0,y0;
		public void paint(Graphics g) {//画图与Socket分开 List初值测试 最后数据from where
			g.setColor(Color.black);
			x0=this.getWidth()/2;
			y0=this.getHeight()/2;
			g.drawLine(0, y0, x0*2, y0);
			g.drawLine(x0,0,x0,y0*2);
			g.setColor(LoveJFrame.this.color);
			g.fillOval(x0+lovepoint.x, y0+lovepoint.y, 2, 2);
			}
		
		public void update(Graphics g) {
			this.paint(g);
		}
		//多此一举 同paint来画 
//		public void draw(Graphics g,ArrayList<Pixel> list) {
//			g.setColor(LoveJFrame.this.color);
//			for(int i=0;i<list.size();i++) {
//				if(list.get(i)!=null)
//			       g.fillOval(list.get(i).x+x0, list.get(i).y+y0, 2, 2);
//		}
//		}
		
		//画布中画画的线程run方法 （内部类）
		public void run() {
			while(true&i<1200) {
				i++;
					double angle=i*Math.PI/512;
					double a=Double.parseDouble(text[0].getText())*Double.parseDouble(text[3].getText());
					double radius=a*(1-Math.cos(angle));
					lovepoint.x=(int)Math.round(radius*Math.cos(angle+Double.parseDouble(text[4].getText())));
					lovepoint.y=(int)Math.round(radius*Math.sin(angle+Double.parseDouble(text[4].getText())));		
				endpoint=new Point(lovepoint.x,lovepoint.y);
				list.add(new Pixel(lovepoint,LoveJFrame.this.color));
				try {
					if(objout!=null)
						objout.writeObject(endpoint);
				}catch(IOException ex) {
				}
				try {
					Thread.sleep(Integer.parseInt(sleeptime.getText()));
					lovecanvas.repaint();
				}catch(InterruptedException e) {
					break;
				}
			}
		}
		}
	 
	
	//window事件处理 即最小化和最大化对集合的处理
	public void windowOpened(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {
//		for(int i=0;i<list.size();i++) {
//			if(list.get(i)!=null)
//				lovepoint=list.get(i);
//		    lovecanvas.paint(lovecanvas.getGraphics());   repaint**
//		}
	}
	public void windowDeactivated(WindowEvent e) {}
	
	
	//Socket通信的线程run方法
	public void run() {
		try {
			ObjectInputStream objin=new ObjectInputStream(this.socket.getInputStream());
			while(true) {
				try {
					this.lovepoint=(Point)objin.readObject();
					this.lovecanvas.repaint();
				}catch(EOFException ex) {
					break;
				}
			}
			objin.close();
			this.objout.close();
			this.socket.close();
		}catch(IOException|ClassNotFoundException ex) {
			
		}
	}
	 
	public void print() {
		for(int i=0;i<list.size();i++) {
			if(list.get(i)!=null)
			  System.out.println(list.get(i).toString());
		}
	}
	
	
	
	
	//main 主函数
	public static void main(String args[]) throws IOException {
           new LoveJFrame("心形线.obj","127.0.0.1", 10011);
	}
	
	
	
	}
