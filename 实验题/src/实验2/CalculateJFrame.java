
package 实验2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;
import javax.swing.*;

public class CalculateJFrame extends JFrame {
	
		private JTextField jt[];
		private JButton btn;
//		private JComboBox comboBox1;
//		private JComboBox comboBox2;
//		private JComboBox comboBox3;
//		private JComboBox comboBox4;
		private JComboBox[] comboBox;
		private String items[]= {"+","-","*","/","%"};//用字符串数组添加元素
	public static void main(String[] arg){
		new CalculateJFrame();
	}
		

	public CalculateJFrame(){
		super("算数运算器");       //设置标题
		this.setBounds(300,400,300,350);    //设置位置和大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭
		
		Container c=this.getContentPane();//获取窗体容器
		c.setLayout(null);//容器设置绝对布局
		comboBox=new JComboBox[4];
		
//		String items[]= {"+","-","*","/","%"};//用字符串数组添加元素
	    comboBox[0] =new JComboBox(items);//创建下拉列表框1
	    comboBox[0].setBounds(50,50,40,30);
	    
	    c.add(comboBox[0]);//加入列表框1
	    comboBox[1] =new JComboBox(items);//创建下拉列表框2
	    comboBox[1].setBounds(50,100,40,30);
	   
	    c.add(comboBox[1]);//加入列表框2
	    comboBox[2] =new JComboBox(items);//创建下拉列表框3
	    comboBox[2].setBounds(50,150,40,30);
	    
	    c.add(comboBox[2]);//加入列表框3
	    comboBox[3] =new JComboBox(items);//创建下拉列表框4
	    comboBox[3].setBounds(50,200,40,30);
	   
	    c.add(comboBox[3]);//加入列表框4
		
		
		
		int n=6;
		JTextField jt[]=new JTextField[n];//设置文本框
	    for(int i=0;i<n;i++){             //for循环放置文本框
	    	jt[i]=new JTextField(20);	 
	    	jt[i].setBounds(100, 50*i+5, 150, 30);
	    	c.add(jt[i]);
	    }
	  
		JButton btn=new JButton("=");//设置按钮
		btn.addActionListener(new ActionListener(){//设置监听
			public void actionPerformed(ActionEvent e) {
				int count=0;
				for(int i=0;i<jt.length;i++) {
			    	if(jt[i].getText()!=null) 
			    		count++;
			    }
				double[] num=new double[count];
				try {
//					double num1 = Double.parseDouble(jt[0].getText());
//					double num2 = Double.parseDouble(jt[1].getText());	
//					double num3 = Double.parseDouble(jt[2].getText());
//					double num4 = Double.parseDouble(jt[3].getText());
//				    double num5 = Double.parseDouble(jt[4].getText());
//				    double num6 = Double.parseDouble(jt[5].getText());;
//				    double num6=0;
//				    double[] num=new double[count];
//				    double temp1=yunsuan(num1,num2,comboBox1);
//				    double temp2=yunsuan(temp1,num3,comboBox2);
//				    double temp3=yunsuan(temp2,num4,comboBox3);
//				    num6=yunsuan(temp3,num5,comboBox4);
//				    jt[5].setText(String.valueOf(num6));
					int[] index=new int[count-1];
					for(int i=0,j=0;i<num.length;i++,j++) {
						if(jt[j].getText()!=null)
						num[i]=Double.parseDouble(jt[j].getText());
						index[i]=j;
					}
					double temp1=yunsuan(num[1],num[2],comboBox[index[1]]);
					}catch(NumberFormatException ex) {
						System.out.println("字符串不能按十进制转换为整数,"+ex.toString());
			//			this.jdialog.show("字符串不能按十进制转换为整数");
					}				
				}			
		});
		btn.setBounds(50, 255, 50, 30);//设置按钮的位置和大小
		c.add(btn);

		this.setVisible(true);     //设置窗体可见
	}	
	public double yunsuan(double t1,double t2,JComboBox comboBox) {
		double t3=0;
		switch(comboBox.getSelectedIndex()) {
		case 0 : t3=t1+t2;break;
		case 1 : t3=t1-t2;break;
		case 2 : t3=t1*t2;break;
		case 3 :if(t2==0)
	//		this.jdialog.show("分母不能为零");
			t3=t1/t2;break;
		case 4 : if(t2==0)
	//		this.jdialog.show("分母不能为零");
			t3=t1%t2;break;
		}
		return t3;
	}
}
	
	
