package AddFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
public class AddFrame extends JFrame implements ActionListener{
	JComboBox<String>jc;
	String[] test= {"+","-","*","/","%"};
	private JButton button;
	private JTextField[] text;
	private MessageJDialog jdialog;
	public AddFrame() {
		super("整数算数运算器");
		this.setSize(600, 100);
		this.setLocation(300, 240);
		this.setLayout(new FlowLayout());
		text=new JTextField[3];
		this.getContentPane().add(this.text[0]=new JTextField(8));
		jc=new JComboBox<>(test);
		this.getContentPane().add(jc);
		this.getContentPane().add(this.text[1]=new JTextField(8));
		this.getContentPane().add(this.button=new JButton("="));
		this.getContentPane().add(this.text[2]=new JTextField(10));
		text[2].setEditable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.button.addActionListener(this);
		this.jc.addActionListener(this);
		this.jdialog=new MessageJDialog();
	}
	private class MessageJDialog extends JDialog{
		private JLabel jl;
		private MessageJDialog() {
			super(AddFrame.this,"提示",true);
			this.setSize(420, 110);
			this.jl=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(this.jl);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		private void show(String message) {
			this.jl.setText(message);
			this.setLocation(AddFrame.this.getX()+100, AddFrame.this.getY()+100);
			this.setVisible(true);
		}
	}
	public static void main(String[] arg) {
		new AddFrame();
	}
	public void actionPerformed(ActionEvent ev) {
		try {
		double num1 = Double.parseDouble(text[0].getText());
		double num2 = Double.parseDouble(text[1].getText());
		double num3=0;
		switch(jc.getSelectedIndex()) {
		case 0 : num3=num1+num2;break;
		case 1 : num3=num1-num2;break;
		case 2 : num3=num1*num2;break;
		case 3 :if(num2==0)
			this.jdialog.show("分母不能为零");
			num3=num1/num2;break;
		case 4 : if(num2==0)
			this.jdialog.show("分母不能为零");
			num3=num1%num2;break;
		}
		String str = String.valueOf(num3);
		text[2].setText(str);
		}catch(NumberFormatException ex) {
			System.out.println("字符串不能按十进制转换为整数,"+ex.toString());
			this.jdialog.show("字符串不能按十进制转换为整数");
		}
	}
	
}
