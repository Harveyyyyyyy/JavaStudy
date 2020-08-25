package 课设ing;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DrawTCPSocketJFrame extends JFrame implements MouseListener, MouseMotionListener,Runnable, ActionListener {
    public Pixel start,end;
    private Canvas canvas;
    private Socket socket;
    private ObjectOutputStream objout;
    private  JButton[] jbutton=new JButton[3];
    private Color color=Color.blue;
    protected JFileChooser fchooser;
    private File file;
    private java.util.List<Pixel> list;
    


    public DrawTCPSocketJFrame(String name)throws IOException{
        super("同步画图"+name+"  "+InetAddress.getLocalHost().toString());
        this.setBounds(400,300,580,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.start=this.end=null;
        this.list=new ArrayList<Pixel>();   
        JPanel cmdpanel=new JPanel();
        this.getContentPane().add(cmdpanel,"North");
        cmdpanel.add(this.jbutton[0]=new JButton("选择颜色"));
        this.jbutton[0].addActionListener(this);


        cmdpanel.add(this.jbutton[1]=new JButton("打开"));
        this.jbutton[1].addActionListener(this);
        cmdpanel.add(this.jbutton[2]=new JButton("保存"));
        this.jbutton[2].addActionListener(this);

        this.canvas=new DrawCanvas();
        this.getContentPane().add(this.canvas);
        this.canvas.addMouseMotionListener(this);
        this.canvas.addMouseListener(this);
        this.setVisible(true);
        this.objout=null;
    }

    public DrawTCPSocketJFrame(String name,String host,int port)throws  IOException{
        this(name);
        Socket socket=new Socket(host,port);
        this.setTitle(this.getTitle()+" : "+socket.getLocalPort());
        this.setSocket(socket);
    }

    public void setSocket(Socket socket)throws IOException{
        this.socket=socket;
        this.objout=new ObjectOutputStream(this.socket.getOutputStream());
        new Thread(this).start();
    }

    public void run(){
        try {
            ObjectInputStream objin = new ObjectInputStream(this.socket.getInputStream());
            while (true) {
                try {
                    this.end = (Pixel) objin.readObject();
                    this.list.add(this.end);
                    this.canvas.repaint();
                } catch (EOFException ex) {
                    break;
                }

            }
            objin.close();
            this.objout.close();
            this.socket.close();
        }

        catch (IOException|ClassNotFoundException ex){}
    }

    public void actionPerformed(ActionEvent event){
        if (event.getSource()==this.jbutton[0]){
            Color color=JColorChooser.showDialog(this,"选择颜色",this.color);
            if (color!=null)
                this.color=color;
        }
        if (event.getSource()==this.jbutton[1]){
              this.fchooser=new JFileChooser(new File("D:\\","课程设计"));
              if (fchooser.showOpenDialog(this)==0) {
                  this.file = fchooser.getSelectedFile();
                  this.readFrom(this.file.getName(), list);
                  this.canvas.repaint();
                  return;
              }
           
        }
        if (event.getSource()==this.jbutton[2]){
            this.fchooser=new JFileChooser(new File("D:\\","课程设计"));
            if (fchooser.showSaveDialog(this)==0) {
                this.writeTo(this.fchooser.getSelectedFile().getName(),list);
            }
        }
    }

    public  <T> void writeTo(String filename,List<T> list){
        try{
            OutputStream out=new FileOutputStream(filename);
            ObjectOutputStream objout=new ObjectOutputStream(out);
            for (int i=0;i<list.size();i++)
                objout.writeObject(list.get(i));
            objout.close();
            out.close();
        }
        catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"\""+filename+"\"文件不存在.");
        }
        catch (IOException ex){}
    }

    public  void readFrom(String filename,List<Pixel> list){
       
        try{
            InputStream in=new FileInputStream(filename);
            ObjectInputStream objin=new ObjectInputStream(in);
            while (true){
                try {
                    list.add((Pixel) objin.readObject());

                } catch (EOFException ex) {
                    break;
                }

            }
            objin.close();
            in.close();
        }
        catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"\""+filename+"\"文件不存在.");
        }
        catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"指定类未找到错误");
        }
        catch (IOException ex){}
    }

    public void mousePressed(MouseEvent event){
        try{
            if(this.objout!=null) {
                this.objout.writeObject(null);              
            }
        }
        catch (IOException ex){}
        this.list.add(null);
        this.end=new Pixel(new Point(event.getX(),event.getY()),this.color);
        this.list.add(this.end);
        try{
            if(this.objout!=null) {
                this.objout.writeObject(this.end);   
            }
        }
        catch (IOException ex){}
    }
    public void mouseReleased(MouseEvent event){}
    public void mouseClicked(MouseEvent event){}
    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mouseMoved(MouseEvent event){}

    public void mouseDragged(MouseEvent event){
        this.start=this.end;
        this.end=new Pixel(new Point(event.getX(),event.getY()),this.color);
        this.list.add(this.end);
        try{
            if (this.objout!=null) {
                this.objout.writeObject(this.end);
            }
        }
        catch (IOException ex){}
        this.canvas.repaint();
    }

    public class DrawCanvas extends Canvas{
        public void paint(Graphics g) {
            for (int i=1;i<list.size();i++) {
                start=list.get(i-1);
                end=list.get(i);
                if (start != null && end != null) {
                    g.setColor(end.color); 
                    g.drawLine(start.x, start.y, end.x, end.y);
                }
            }
           
        }


        public void update(Graphics g){
            this.paint(g);
        }

    }

    public static void main(String[] args)throws IOException {
        new DrawTCPSocketJFrame("用户B","127.0.0.1",10011);
    }
}
