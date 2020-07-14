import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
public class ArraysPoint extends JFrame implements ActionListener,Runnable {
	private String[] textstr= {"范围","sleeptime"};
	private String[] buttonstr= {"生成","打开","保存"};
	private JButton[] buttons;
	private JPanel cmdpanel,Southpanel;
	private JTextField text_count,text_sleep,text_filename,text_south;
	private DefaultTableModel tablemodel;
	private Thread thread;
	private int sleeptime;
	private JRadioButton[] radioButton;
	private String direction;
	public ArraysPoint(String filename) {
		super("大数据191-202190338-周家辉");
		this.setBounds(300, 300, 750, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.cmdpanel=new JPanel();
		this.getContentPane().add(this.cmdpanel,"North");
		this.getContentPane().add(this.Southpanel=new JPanel(),"South");
		for(int i=0;i<textstr.length;i++) {
			this.cmdpanel.add(new JLabel(textstr[i]));
		}
		this.cmdpanel.add(this.text_count=new JTextField("10",7),1);
		this.cmdpanel.add(this.text_sleep=new JTextField("600",7));
		this.buttons=new JButton[buttonstr.length];
		for(int i=0;i<buttonstr.length;i++) {
		this.cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
		this.buttons[i].addActionListener(this);
		}
		ButtonGroup bgroup=new ButtonGroup();
		String[] radstr= {"左","右"};
		this.radioButton=new JRadioButton[radstr.length];
		for(int i=0;i<radstr.length;i++) {
			this.Southpanel.add(radioButton[i]=new JRadioButton(radstr[i]));
			bgroup.add(this.radioButton[i]);
			radioButton[i].addActionListener(this);
		}
		radioButton[0].setSelected(true);
		this.direction="left";
		this.thread=new Thread(this);
		this.cmdpanel.add(new JLabel("文件名"),5);
		this.cmdpanel.add(text_filename=new JTextField("大数据191-202190338-周家辉!.int"),6);
		this.Southpanel.add(this.text_south=new JTextField(filename,20),"South");
		String[] title= {"1","2","3","4","5","6","7","8","9","10"};
		this.tablemodel=new DefaultTableModel(title,0);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));

		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("生成")||ev.getSource()==this.text_count) {
			if(Integer.parseInt(this.text_count.getText())>=0&&Integer.parseInt(this.text_count.getText())<=50)
				random(this.tablemodel,this.text_count);
			else 
				JOptionPane.showMessageDialog(this, "超出0--50范围！");
			this.sleeptime=Integer.parseInt(this.text_sleep.getText());
		}else {
			switch(ev.getActionCommand()) {
			case "打开" :readFrom(this.text_filename.getText(),this.tablemodel);break;
			case "保存" :writeTo(this.text_filename.getText(),this.tablemodel);break;
			}
		}
		if(ev.getSource()==this.text_sleep) {
			try {
				this.sleeptime=Integer.parseInt(this.text_sleep.getText());
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "\""+this.text_sleep.getText()+"\"不能转换成整数，请重新输入！");
			}
		}else if(ev.getSource()==this.buttons[0]) {
			if(this.thread.getState()!=Thread.State.NEW)
				this.thread=new Thread(this);
			this.thread.start();
		}
		else if(ev.getSource()==radioButton[1]) {
			this.direction="right";
		}else if(ev.getSource()==radioButton[0]) {
		    this.direction="left";	
		}
	}

	protected void random(DefaultTableModel tavlemodel,JTextField text) {

		try {
			int n=Integer.parseInt(text.getText()),i=0;
			int columns=tablemodel.getColumnCount();
			int rows=(n%columns==0)? n/columns:n/columns+1;
			tablemodel.setRowCount(rows);
			for(i=0;i<n;i++) 
				tavlemodel.setValueAt((int)(Math.random()*100), i/columns, i%columns);
			for(i=n;i/columns<rows&&i%columns<columns;i++)
				tavlemodel.setValueAt(null, i/columns, i%columns);
			
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "\""+text.getText()+"\"不能转换成整数。");
		}
	}
	protected void readFrom(String filename,DefaultTableModel tablemodel) {
		try {
			InputStream in=new FileInputStream(filename);
			DataInputStream datain=new DataInputStream(in);
			tablemodel.setRowCount(1);
			int i=0,j=0;
			while(true) {
				try {
					for(j=0;j<tablemodel.getColumnCount();j++)
						tablemodel.setValueAt(datain.readInt(), i, j);
					i++;
					tablemodel.addRow(new Object[tablemodel.getColumnCount()]);
				}catch(EOFException eof) {
					this.text_count.setText((i*tablemodel.getColumnCount()+j)+"");
					while(j<tablemodel.getColumnCount())
						tablemodel.setValueAt(null, i, j++);
					break;
				}
			}
		datain.close();
		in.close();
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(this, filename+"文件不存在。");
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(this, "读取文件时数据错误");
		}
	}
	protected void writeTo(String filename,DefaultTableModel tablemodel) {
		try {
			OutputStream out=new FileOutputStream(filename);
			DataOutputStream dataout=new DataOutputStream(out);
			int n=0;
			for(int i=0;i<tablemodel.getRowCount();i++) {
				for(int j=0;j<tablemodel.getColumnCount();j++) {
					Object obj=tablemodel.getValueAt(i, j);
					if(obj!=null&&obj instanceof Integer) {
						dataout.writeInt(((Integer)obj).intValue());
						n++;
					}else if(obj!=null&&obj instanceof String&&!obj.equals("")) {
						try {
							dataout.writeInt(Integer.parseInt((String)obj));
							n++;
						}catch(NumberFormatException ex) {}
					}
				}
				dataout.close();
				out.close();
				this.text_count.setText(n+"");
			}
		  }catch(FileNotFoundException ex) {
							JOptionPane.showMessageDialog(this, "\""+filename+"\"文件不存在。");
						}catch(IOException ex) {
							JOptionPane.showMessageDialog(this, "写入文件是数据错误");
						}
					}
     public void run() {
			while(true) {
				try {
					String str=this.text_south.getText();
					if(this.direction=="left")
				      this.text_south.setText(str.substring(1)+str.substring(0,1));
					else
					  this.text_south.setText(str.substring(str.length()-1,str.length())+str.substring(0,str.length()-1));
					Thread.sleep(this.sleeptime);
				}catch(InterruptedException ex) {
					break;
				}			}
		}
//     public int foundPoint(int[][] mat) {
//    	 for(int i=0;i<mat.length;i++) {
//    		 int max=mat[i][0];
//    		 int min=mat[i][0];
//    		 for(int j=0;j<mat[i].length;j++) {
//    			 if(mat[i][j]>max)
//    				 max=mat[i][j];
//    			 if(mat[i][j]<min)
//        			 min=mat[i][j];
//    		 }	 
//     }	 
//     }
	
	public static void main(String args[]) {
		new ArraysPoint("大数据191-202190338-周家辉-8!");
	}

}
