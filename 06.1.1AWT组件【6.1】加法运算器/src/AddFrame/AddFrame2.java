package AddFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;


import javax.swing.*;
public class AddFrame2 extends JFrame implements ActionListener {
	private JButton button;
	private JTextField t1,t2,t3;
	private JRadioButton[] radios;
	public AddFrame2() {
		super("��������������");
		this.setSize(800, 100);
		this.setLocation(300, 240);
		this.setLayout(new FlowLayout());
		this.getContentPane().add(this.t1=new JTextField(8));
		AddPanel();
		this.getContentPane().add(this.t2=new JTextField(8));
		this.getContentPane().add(this.button=new JButton("="));
		this.getContentPane().add(this.t3=new JTextField(10));
		t3.setEditable(false);
		this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.button.addActionListener(this);
	}
	public void AddPanel() {
		Panel panel=new Panel(new GridLayout(1,5));
		ButtonGroup bgroup=new ButtonGroup();
		String[] str= {"+","-","*","/","%"};
		this.radios=new JRadioButton[str.length];
		for(int i=0;i<this.radios.length;i++) {
			panel.add(this.radios[i]=new JRadioButton(str[i]));
			bgroup.add(this.radios[i]);
		}
		this.radios[0].setSelected(true);
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ev) {
		int n=0;
		for(int i=0;i<5;i++) {
			if(this.radios[i].isSelected()) {
				n=i;
			}
		}
		int num1 = Integer.parseInt(t1.getText());
		int num2 = Integer.parseInt(t2.getText());
		int num3=0;
		         
		switch(n) {
		case 0 : num3=num1+num2;break;
		case 1 : num3=num1-num2;break;
		case 2 : num3=num1*num2;break;
		case 3 : num3=num1/num2;break;
		case 4 : num3=num1%num2;break;
		}
			
		String str = String.valueOf(num3);
		t3.setText(str);
		
	}
	public static void main(String[] arg) {
		new AddFrame2();
	}

}
