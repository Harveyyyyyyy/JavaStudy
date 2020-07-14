package FlieRandom;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
public class FileRandomJFrame extends JFrame implements ActionListener{
	protected JPanel cmdpanel;
	protected JTextField text_filename,text_count;
	protected DefaultTableModel tablemodel;
	
	
	public FileRandomJFrame(String filename) {
		super("随机数序列");
		this.setBounds(300, 240, 530, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.cmdpanel=new JPanel();
		this.getContentPane().add(this.cmdpanel,"North");
		this.cmdpanel.add(new JLabel("随机数个数"));
		this.cmdpanel.add(this.text_count=new JTextField(10+"",5));
		this.text_count.addActionListener(this);
		String[] bstr= {"生成","打开","保存"};
		for(int i=0;i<bstr.length;i++) {
			JButton button=new JButton(bstr[i]);
			this.cmdpanel.add(button);
			button.addActionListener(this);
		}
		this.cmdpanel.add(new JLabel("文件名"),3);
		String[] title= {"1","2","3","4","5","6","7","8","9","10"};
		this.cmdpanel.add(this.text_filename=new JTextField(filename,10),4);
		this.tablemodel=new DefaultTableModel(title,0);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ev) {
		if(ev.getActionCommand().equals("生成")||ev.getSource()==this.text_count)
			random(this.tablemodel,this.text_count);
		else {
			switch(ev.getActionCommand()) {
			case "打开" :readFrom(this.text_filename.getText(),this.tablemodel);break;
			case "保存" :writeTo(this.text_filename.getText(),this.tablemodel);break;
			}
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
	public static void main(String args[]) {
		new FileRandomJFrame("random.int");
	}
}
