package LoveLove;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class LovesJFrame extends JFrame implements ActionListener,CaretListener{
	private JButton[] buttons;
	private Color color;
	private JTextField[] text;
	private JTextField sleeptime;
	private LoveCanvas lovecanvas;
	private MessageJDialog jdialog;
	int n=1,f,d;
	String[] textstr= {"大小","位置x:","y:","缩放比例","旋转角度"};
	String[] buttonstr= {"选择颜色","启动","中断"};
	String[] numstr= {"7","0","0","10","4.7"};
	public LovesJFrame(int x,int y,int f,int d) {
		super("心形线");
		this.f=f;
		this.d=d;
		Dimension dim =this.getToolkit().getScreenSize();
		this.setBounds(x, y, dim.width/2, dim.height/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel cmdpanel=new JPanel();
		JPanel sleeppanel=new JPanel();
		this.getContentPane().add(cmdpanel,"North");
		this.getContentPane().add(sleeppanel,"South");
		sleeppanel.add(new JLabel("sleeptime"));
		sleeppanel.add(sleeptime=new JTextField("200",10));
		this.buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttonstr.length;i++) {
			cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
			this.buttons[i].addActionListener(this);
		}
		this.text=new JTextField[textstr.length];
		for(int i=0;i<textstr.length;i++) {
			cmdpanel.add(new JLabel(textstr[i]));
			cmdpanel.add(this.text[i]=new JTextField(numstr[i],3));
//			this.text[i].addActionListener(this);
			this.text[i].addCaretListener(this);
		}
		cmdpanel.add(new JLabel("PI"));
		lovecanvas=new LoveCanvas();
		this.color=Color.red;
		this.getContentPane().add(this.lovecanvas,"Center");
		this.setVisible(true);
		lovecanvas.thread.start();
		this.jdialog=new MessageJDialog();
	}
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()==this.buttons[0]) {
		Color color=JColorChooser.showDialog(this, "选择颜色", this.color);
		if(color!=null) {
			this.color=color;
		}
		}
	    if(ev.getSource()==this.buttons[1]) {
			if(lovecanvas.thread.getState()!=Thread.State.NEW)
				lovecanvas.thread=new Thread(lovecanvas);
			lovecanvas.thread.start();
			this.buttons[1].setEnabled(false);
			this.buttons[2].setEnabled(true);
		}
	    if(ev.getSource()==this.buttons[2]) {
			lovecanvas.thread.interrupt();
			this.buttons[1].setEnabled(true);
			this.buttons[2].setEnabled(false);
		}
			
	}
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
	private class MessageJDialog extends JDialog{
		private JLabel jlabel;
		private MessageJDialog() {
			super(LovesJFrame.this,"提示",true);
			this.setSize(420, 110);
			this.jlabel=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(this.jlabel);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		private void show(String message) {
			this.jlabel.setText(message);
			this.setLocation(LovesJFrame.this.getX()+100, LovesJFrame.this.getY()+100);
			this.setVisible(true);
		}
	}
	private class LoveCanvas extends Canvas implements Runnable{
		Thread thread=new Thread(this);
		public void paint(Graphics g) {
			g.setColor(Color.black);
			int x0=LovesJFrame.this.f*this.getWidth()/2;
			int y0=this.getHeight()/2;
			g.setColor(LovesJFrame.this.color);
			
				for(int i=0;i<n;i++) {
					double angle=i*Math.PI/512;
					double a=Double.parseDouble(text[0].getText())*Double.parseDouble(text[3].getText());
					double radius=a*(1-Math.cos(angle));
					int x=(int)Math.round(radius*Math.cos(angle+Double.parseDouble(text[4].getText())));
					int y=(int)Math.round(radius*Math.sin(angle+Double.parseDouble(text[4].getText())));
					g.fillOval(x0+x*LovesJFrame.this.d+Integer.parseInt(text[1].getText()), y0+y+Integer.parseInt(text[2].getText()), 2, 2);
			}
			}
		public void run() {
			while(true) {
				if(n>1000) {
					n=1;
		    	}else {
					n=n+100;
					this.repaint();
				}
				try {
					Thread.sleep(Integer.parseInt(sleeptime.getText()));	
				}catch(InterruptedException e) {
					break;
				}
			}
		}
		}
	
	public static void main(String args[]) {
		new LovesJFrame(0,150,2,-1);
		new LovesJFrame(670,150,0,1);
	}
	}




