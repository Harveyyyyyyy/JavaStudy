package 实验2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Figure extends JFrame implements ActionListener
{
	private JButton button;
	private JTextField t1,t2,t3;
    private Color color;
	private FCanvas canvas;
	static double a,b,c ;
	private int j;
//	private Thread thread;
    
    public Figure()
   {
    super("心型线");
    Dimension dim= this.getToolkit().getScreenSize();
    this.setBounds(dim.width/4, dim.height/4, dim.width/2, dim.height/2); 
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
    JPanel cmdpanel = new JPanel();
    this.getContentPane(). add( cmdpanel, "North");
    cmdpanel. add(this . button = new JButton("选择颜色"));
    this.button. addActionListener(this);
    
    cmdpanel. add(new JLabel("图像大小"));
    cmdpanel. add(this.t1=new JTextField("80",3));
    this.t1.addActionListener(this);
    cmdpanel. add(new JLabel("缩放比例"));
    cmdpanel. add(this.t2=new JTextField("1",3));
    this.t2.addActionListener(this);
    cmdpanel. add(new JLabel("旋转角度"));
    cmdpanel. add(this.t3=new JTextField("90",3));
    this.t3.addActionListener(this);
    this.setVisible(true);
    this.color = Color.blue;
    this.canvas=new FCanvas();
//    thread=new Thread(this);
    this.getContentPane().add(this.canvas, "Center");
    this.setVisible(true);
//    this.thread.start();
    canvas.thread.start();
    
    }
    
//    public void run() {
//		while(true) {
//			if(j>1024)
//				j=1;
//			else {
//				j=j+120;
//			}
//			try {
//				this.repaint();
//				Thread.sleep(200);
//			}catch(InterruptedException e) {
//				return;
//			}
//		}
//	}
    
    public void actionPerformed(ActionEvent event)
    {
     if(event. getSource() == this. button)
              {Color color = JColorChooser.showDialog(this,"选择颜色",this.color);
    		  if(color != null)
    		  {this.color = color; 
    		  }
    		  
        }
    
     if(t1!=null||t2!=null||t3!=null)
       {
    	 try
    	{          	         
    	 a=Double.parseDouble(t1.getText());
    	 b=Double.parseDouble(t2.getText());
    	 c=Double.parseDouble(t3.getText());
    	 this. canvas. repaint( );
        }
    	 catch(NumberFormatException ex)
    	 {
    		 JOptionPane.showMessageDialog(this,"不能转换成浮点数，请重新输入");
    	 }
       }
    }
       
    public class FCanvas extends Canvas implements Runnable
    {
    	Thread thread=new Thread(this);
    	public void paint(Graphics g)
    	{
    	 int x0 = this. getWidth()/2;
    	 int y0 = this. getHeight()/2;
    	 g. drawLine(0,y0, x0*2,y0);
    	 g. drawLine(x0,0, x0,y0*2);
    	 g.setColor(Figure.this.color);
    	 
     	for(int i=1; i < j; i++)
    	{
    		double angle = i*Math.PI/512;
    		double radius =a*b*(1-Math. cos(angle+(c*Math.PI/180)));    	
    		int x=(int)(radius*Math. cos(angle));
    		int y=(int)(radius*Math. sin(angle));
    		g.fillOval(x0+x,y0+y,2,2);
    	}
    	}
    	 public void run() {
    			while(true) {
    				if(j>1024)
    					j=1;
    				else {
    					j=j+120;
    				}
    				try {
    					this.repaint();
    					Thread.sleep(200);
    				}catch(InterruptedException e) {
    					return;
    				}
    			}
    		}
    }

	public static void main(String[] args)
	{
          new Figure();
	}


}

