package ʵ��2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class QueryFrame0 extends JFrame implements ActionListener
{
	private JTextField text_str,text_uni;
//	private MessageJDialog jdialog;
	private DefaultTableModel tablemodel;
	
	public QueryFrame0() 
	{
		super("�ַ���Unicode�����ѯ��");
		this.setBounds(300, 240, 500, 400);
		this.setBackground(Color.lightGray);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(new JLabel("�ַ���",JLabel.CENTER));
		this.text_str=new JTextField("��ѯ���ֱ���",28);
		this.getContentPane().add(this.text_str);
		this.text_str.addActionListener(this);
	//	caretUpdate(null);
	//	this.jdialog=new MessageDialog();
		
		String[] titles= {"�ַ�","Unicodeֵ"};
		this.tablemodel=new DefaultTableModel(titles,1);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
	}
/*	private class MessageDialog extends JDialog
	{
		private JLabel jlabel;
		private MessageDialog()
		{
			super(QueryFrame0.this,"��ʾ",true);
			this.setSize(420, 110);
			this.jlabel=new JLabel("",JLabel.CENTER);
		}
		private void show(String message)
		{
			this.jlabel.setText(message);
			this.setLocation(QueryFrame0.this.getX()+100,QueryFrame0.this.getY()+100);

	*/	
			
		public void actionPerformed(ActionEvent ev) {
			String str=this.text_str.getText();
			if(!str.isEmpty())
			{
				char[] ch=new char[str.length()];
				this.tablemodel.setRowCount(str.length());
				for(int i=0;i<str.length();i++) {
					ch[i]=str.charAt(i);
  			       this.tablemodel.setValueAt(ch[i],i,0);
				   this.tablemodel.setValueAt((int)ch[i],i,1);
				}
			
		}
	
	
	
	
		
		
/*		JPanel cmdpanel = new JPanel();
		this.getContentPane().add(cmdpanel, "North");
		String[] str= {"�ַ���"};
		String[] str_text= {""};
		this.text_str=new JTextField[str_text.length];
		//this.texts.addCaretListener;
		int i=0;
		for(i=0;i<str_text.length;i++)
		{
			cmdpanel.add(new JLabel(str[i]));
			cmdpanel.add(this.text_str[i]=new JTextField(str_text[i],28));
			
		}
		for(;i<str.length;i++)
			cmdpanel.add(new JLabel(str[i]));
		
		String[] titles= {"�ַ�","Unicodeֵ"};
		this.tablemodel=new DefaultTableModel(titles,1);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
	*/	
		
	//	this.setVisible(true);
	}

	public static void main(String[] args) {
		new QueryFrame0();
	}

}
