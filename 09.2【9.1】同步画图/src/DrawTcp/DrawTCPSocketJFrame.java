package DrawTcp;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;
public class DrawTCPSocketJFrame extends JFrame implements MouseListener,MouseMotionListener,Runnable{
	private Point start,end;
	private Canvas canvas;
	private Socket socket;
	private ObjectOutputStream objout;
	
	public DrawTCPSocketJFrame(String name) throws IOException{
		super("ͬ����ͼ"+name+" "+InetAddress.getLocalHost().toString());
		this.setBounds(400, 300, 580, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.start=this.end=null;
		this.canvas=new DrawCanvas();
		this.getContentPane().add(this.canvas);
		this.canvas.addMouseListener(this);
		this.canvas.addMouseMotionListener(this);
		this.setVisible(true);
		this.objout=null;
	}
	public DrawTCPSocketJFrame(String name,String host,int port) throws IOException{
		this(name);
		Socket socket=new Socket(host,port);
		this.setTitle(this.getTitle()+":"+socket.getLocalPort());
		this.setSocket(socket);
	}
	public void setSocket(Socket socket) throws IOException{
		this.socket=socket;
		this.objout=new ObjectOutputStream(this.socket.getOutputStream());
		new Thread(this).start();
	}
	public void run() {
		try {
			ObjectInputStream objin=new ObjectInputStream(this.socket.getInputStream());
			while(true) {
				try {
					this.start=this.end;
					this.end=(Point)objin.readObject();
					this.canvas.repaint();
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
	public void mouseDragged(MouseEvent ev) {
		this.start=this.end;
		this.end=new Point(ev.getX(),ev.getY());
		this.canvas.repaint();
		try {
			if(this.objout!=null)
				this.objout.writeObject(this.end);
		}catch(IOException ex) {
			
		}
	}
	public void mouseMoved(MouseEvent ev) {
	}
	public void mouseClicked(MouseEvent ev) {
	}
	public void mousePressed(MouseEvent ev) {
		this.start=null;
		this.end=new Point(ev.getX(),ev.getY());
		try {
			if(this.objout!=null)
				this.objout.writeObject(this.end);
		}catch(IOException ex) {
			
		}
	}
	public void mouseReleased(MouseEvent ev) {
	}
	public void mouseEntered(MouseEvent ev) {
	}
	public void mouseExited(MouseEvent ev) {	
	}
	private class DrawCanvas extends Canvas{
		public void paint(Graphics g) {
			if(start!=null&&end!=null) {
				g.setColor(Color.blue);
				g.drawLine(start.x, start.y, end.x, end.y);
			}
		}
		public void update(Graphics g) {
			this.paint(g);
		}
	}
	public static void main(String args[]) throws IOException{
		
		new DrawTCPSocketJFrame("С�۷�", "127.0.0.1", 10011);
	}

}
