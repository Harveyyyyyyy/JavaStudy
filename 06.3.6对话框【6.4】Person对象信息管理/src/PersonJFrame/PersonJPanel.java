package PersonJFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import DateFormatException.DateFormatException;
import MyDate.MyDate;
import Person.Person;
public class PersonJPanel extends JPanel implements ActionListener {
	private JTextField text_name,text_date;
	private JRadioButton[] radios;
	public JComboBox<String> combox_province,combox_city;
	private static String[] provinces= {"江苏","浙江"};
	private static String[][] cities= {{"南京","苏州","无锡"},{"杭州","宁波","温州"}};
	
	public PersonJPanel() {
		this.setBorder(new TitledBorder("Person"));
		this.setLayout(new GridLayout(5, 1));
		this.add(this.text_name=new JTextField("姓名"));
		this.add(this.text_date=new JTextField("2000年1月1日"));
		
		String[] str= {"男","女"};
		JPanel rbpanel=new JPanel(new GridLayout(1, 2));
		this.add(rbpanel);
		ButtonGroup bgroup=new ButtonGroup();
		this.radios=new JRadioButton[str.length];
		for(int i=0;i<this.radios.length;i++) {
			rbpanel.add(this.radios[i]=new JRadioButton(str[i]));
			bgroup.add(this.radios[i]);
		}
		this.radios[0].setSelected(true);
		this.add(this.combox_province=new JComboBox<String>(PersonJPanel.provinces));
		this.add(this.combox_city=new JComboBox<String>(PersonJPanel.cities[0]));
		this.combox_province.addActionListener(this);
	}
	public void set(Person per) {
		if(per==null)
			return;
		this.text_name.setText(per.name);
		this.text_date.setText(per.birthdate.toString());
		if(per.gender.equals("男"))
			this.radios[0].setSelected(true);
		else
			this.radios[1].setSelected(true);
		this.combox_province.setSelectedItem(per.province);
		this.combox_city.setSelectedItem(per.city);
	}
	public Person get() {
		String gender=radios[0].isSelected()?radios[0].getText():radios[1].getText();
		try {
			MyDate birthdate=new MyDate(this.text_date.getText());
			return new Person(text_name.getText(),birthdate,gender,(String)combox_province.getSelectedItem(),(String)combox_city.getSelectedItem());
		}catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,ex.getMessage()+"字符串不能转换成整数");
		}catch(DateFormatException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	
		return null;
	}
	public void actionPerformed(ActionEvent ev) {
		int i=this.combox_province.getSelectedIndex();
		if(cities!=null&&i!=-1) {
			this.combox_city.removeAllItems();
			for(int j=0;j<PersonJPanel.cities[i].length;j++)
				this.combox_city.addItem(PersonJPanel.cities[i][j]);
		}
	}
}

