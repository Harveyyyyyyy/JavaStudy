package 实验2;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
public class MonthlyWageJFrame extends JFrame implements ActionListener,ChangeListener
{
	private JTextField[] texts;
	private JSpinner spin_year;
	private JButton button;
	private DefaultTableModel tablemodel;
	private String[]are= {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月"
			,"十一月","十二月","合计","平均值"};
	
	public MonthlyWageJFrame()
	{
		super("计算月工资");
		this.setBounds(100,100,400,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel cmdpanel=new JPanel();
		this.getContentPane().add(cmdpanel,"North");
		String[] str= {"年份"};
		int i=0;
		{cmdpanel.add(new JLabel(str[i]));
		}
		Calendar start=Calendar.getInstance();
		int year=start.get(Calendar.YEAR);
		this.spin_year=new JSpinner(new SpinnerNumberModel(year-1,year-2,year,1));
		cmdpanel.add(this.spin_year,1);
		cmdpanel.add(this.button=new JButton("计算"));
		this.button.addActionListener(this);
		String[] titles= {"月份","工资(元)"};
		this.tablemodel=new DefaultTableModel(titles,1);
		JTable jtable=new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
		this.tablemodel.setRowCount(14);
		for(int row=0;row<14;row++)
		{
			this.tablemodel.setValueAt(are[row], row ,0);
			}
		this.spin_year.addChangeListener(this);
		}

	public void actionPerformed(ActionEvent event)
	{
		int sum=0;double j=0;
		try {	
	    for(int row=0;row<12;row++)
	    	{
	    	 if(this.tablemodel.getValueAt(row, 1)!=null&&!this.tablemodel.getValueAt(row, 1).equals("0")) {
	    	 sum+=Integer.parseInt((String) this.tablemodel.getValueAt(row,1));
	    	 j++;
	    	   }
	    	 }
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "字符串不能按十进制转换为整数"+ex.toString());
			return;
	}
	    double avg;
	    if(j==0)
	    	avg=0;
	    else 
	    	avg=sum/j;
	    
		this.tablemodel.setValueAt(String.valueOf(sum), 12, 1);
        this.tablemodel.setValueAt(String.valueOf(avg), 13, 1);
        }

	public void stateChanged(ChangeEvent ev) {
		for(int row=0;row<14;row++)
			this.tablemodel.setValueAt(null, row, 1);
		
	}
	

public static void main(String[] arg) 
{
	new MonthlyWageJFrame();
}

}
