package 实验2;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.*;
import javax.swing.event.*;

public class PaintJFrame extends JFrame implements ActionListener{
    private JButton[] button;
    private JTextField[] text;
    private Color color;
    private PaintCanvas canvas;
    String[] textstr = {"X","Y","Z","半径","高","体积","表面积","缩放比例","旋转角度"};
    String[] buttonstr = {"选择颜色","绘制","计算"};
    String[] numstr = {"10","10","10","50","50","0","0","1","0",};

    public PaintJFrame()
    {
        super("圆柱");
        Dimension dim = this.getToolkit().getScreenSize();
        this.setBounds(dim.width / 4, dim.height / 4, dim.width / 2, dim.height / 2);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel cmdpanel = new JPanel();
        this.getContentPane().add(cmdpanel, "North");      //边布局
       

        this.button = new JButton[buttonstr.length];
        for(int i = 0; i < buttonstr.length; i++)
        {
            cmdpanel.add(this.button[i] = new JButton(buttonstr[i]));
            this.button[i].addActionListener(this);
        
        }
        this.text = new JTextField[textstr.length];
        for (int i = 0; i < textstr.length; i++) {
            cmdpanel.add(new JLabel(textstr[i]));
            cmdpanel.add(this.text[i] = new JTextField(numstr[i], 3));
  //          this.text[i].addActionListener(this);
        }
        cmdpanel.add(new JLabel("PI"));

        this.color = Color.black;
        this.canvas = new PaintCanvas();
        this.getContentPane().add(this.canvas, "Center");
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent event) 
    {
        if (event.getSource() == this.button[0])    //颜色
        {
            final Color color = JColorChooser.showDialog(this, "选择颜色", this.color);
            if (color != null)
            {
                this.color = color;
                this.canvas.repaint();
            }
        }
        if (event.getSource() == this.button[1])    //画图
        {
            this.canvas.repaint();

      
        }
        if (event.getSource() == this.button[2])     //计算
        {
            double S ,V;
            double r = Double.parseDouble(this.text[3].getText());
            double h = Double.parseDouble(this.text[4].getText());
             
            S = 2*Math.PI*r*h+2*Math.PI*r*r;
            V = Math.PI*r*r*h;
            
            numstr[5] = String.valueOf(V);
            numstr[6] = String.valueOf(S);
//            cmdpanel.add(this.text[5] = new JTextField(numstr[5], 3));
//            cmdpanel.add(this.text[6] = new JTextField(numstr[6], 3));
            this.text[5].setText(numstr[5]);
            this.text[6].setText(numstr[6]);

        }

    }
    
    private class PaintCanvas extends Canvas 
    {
        public void paint(Graphics g)
        {
            int x0 = this.getWidth()/2;
            int y0 = this.getHeight()/2;
            int x =(int) Double.parseDouble(text[0].getText());
            int y =(int) Double.parseDouble(text[1].getText());
            int z =(int) Double.parseDouble(text[2].getText());
            int r =(int) Double.parseDouble(text[3].getText());
            int h =(int) Double.parseDouble(text[4].getText());
            g.drawLine(0, y0, x0*2, y0);
            g.drawLine(x0, 0, x0, y0*2);
            g.drawLine(x0-y0, y0*2, x0+y0, 0);
            g.setColor(PaintJFrame.this.color);
         
            g.drawOval(x0+y-x/2-r ,y0-z+x/2-r/2, 2*r, r);
            g.drawOval(x0+y-x/2-r ,y0-z+x/2-r/2-h, 2*r, r);
            g.drawLine(x0+y-x/2-r, y0-z+x/2,x0+y-x/2-r, y0-z+x/2-h);
            g.drawLine(x0+y-x/2+r, y0-z+x/2,x0+y-x/2+r, y0-z+x/2-h);
        }

            
    }


        public static void main(String arg[])
    {   
        new PaintJFrame();


    }

    
}
