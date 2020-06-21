package PersonJFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import CompareArray.Comparator;
import CompareArray.CompareBirthdate;
import CompareArray.CompareName;
import CompareArray.EqualBirthdate;
import CompareArray.EqualName;
import CompareArray.Equalable;
import Person.Person;

public class PersonJFrame extends JFrame implements ActionListener,ListSelectionListener{
	protected JList<Person> jlist;
	protected DefaultListModel<Person> listmodel;
	protected PersonJPanel person;
	protected JComboBox<String>[] comboxs;
	private static Equalable[] equal= {new EqualName(),new EqualBirthdate()};
	private static Comparator[] comparators= {new CompareName(),new CompareBirthdate()};
	
	public PersonJFrame(Person[] pers) {
		this(pers,new PersonJPanel());
	}

	public PersonJFrame(Person[] pers, PersonJPanel person) {
		super("Person对象信息管理");
		this.setSize(700, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.getContentPane().add(split);
		this.person=person;
		split.add(this.person);
		split.setDividerLocation(110);
		split.setOneTouchExpandable(true);
		
		JPanel rightpanel=new JPanel(new BorderLayout());
		split.add(rightpanel);
		this.listmodel=new DefaultListModel<Person>();
		if(pers!=null)
			for(int j=0;j<pers.length;j++)
				this.listmodel.addElement(pers[j]);
		this.jlist=new JList<Person>(this.listmodel);
		this.jlist.addListSelectionListener(this);
		rightpanel.add(new JScrollPane(this.jlist));
		JPanel cmdpanel=new JPanel();
		rightpanel.add(cmdpanel,"South");
		
		String[][] str= {{"添加","删除","删除选中项"},{"查找关键字","排序关键字"},{"姓名","出生日期"}};
		for(int i=0;i<str[0].length;i++) {
			JButton button=new JButton(str[0][i]);
			button.addActionListener(this);
			cmdpanel.add(button);
		}
		this.comboxs=new JComboBox[str[1].length];
		for(int i=0;i<str[1].length;i++) {
			cmdpanel.add(new JLabel(str[1][i]));
			cmdpanel.add(this.comboxs[i]=new JComboBox<String>(str[2]));
			this.comboxs[i].addActionListener(this);
		}
		this.setVisible(true);
	}
	public PersonJFrame() {
		this(null,new PersonJPanel());
	}
	
	public void valueChanged(ListSelectionEvent ev) {
		this.person.set(this.jlist.getSelectedValue());
	}
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() instanceof JButton) {
			Person per=null;
			switch(ev.getActionCommand()) {
			case "添加":
				if((per=this.person.get())!=null)
					this.listmodel.addElement(per);
				break;
			case "删除":
				remove(this.listmodel,this.person.get());break;
			case "删除选中项":
				this.removeSelected(this.jlist,this.listmodel);break;
			}
		}else if(ev.getSource() instanceof JComboBox) {
			jlist.clearSelection();
			if(ev.getSource() ==this.comboxs[0]) {
				int i=this.comboxs[0].getSelectedIndex();
				selectAll(this.listmodel,this.jlist,this.person.get(),equal[i]);
			}else if(ev.getSource() == this.comboxs[1]) {
				int i=this.comboxs[1].getSelectedIndex();
				sort(this.listmodel,comparators[i]);
			}
		}
	}
	
	public <T> void remove(DefaultListModel<T> listModel,T obj) {
		if(obj!=null&&JOptionPane.showConfirmDialog(this, "删除"+obj.toString()+"?","确认",JOptionPane.YES_NO_OPTION)==0) {
			boolean remove=this.listmodel.removeElement(obj);
			JOptionPane.showMessageDialog(this, remove?"删除成功":"查找不成功，没有删除");
		}
	}
	
	public <T> void removeSelected(JList jlist,DefaultListModel<T> listmodel) {
		if(this.listmodel.getSize()==0)
			JOptionPane.showMessageDialog(this, "列表框为空，不能删除");
		else {
			int i=this.jlist.getSelectedIndex();
			if(i==-1)
				JOptionPane.showMessageDialog(this, "请选中列表框数据项");
			else {
				String str=this.jlist.getSelectedValue().toString();
				if(JOptionPane.showConfirmDialog(this, "删除第"+i+"项("+str+")?","确认",JOptionPane.YES_NO_OPTION)==0)
					this.listmodel.removeElementAt(i);
			}
		}
	}
	
	
	public <T> void selectAll(DefaultListModel<? super T> listmodel,JList<? super T> jlist,T key,Equalable<? super T> eq) {
		int n=listmodel.getSize();
		for(int i=0;i<n;i++) 
			if(eq.equals(key, (T)listmodel.getElementAt(i)))
					jlist.addSelectionInterval(i, i);
	}
	
	
	public <T> void sort(DefaultListModel<? super T> listmodel,Comparator<? super T> c) {
		for(int i=0;i<listmodel.getSize()-1;i++) {
			int min=i;
			for(int j=i+1;j<listmodel.getSize();j++)
				if(c.compare((T)listmodel.getElementAt(j),(T)listmodel.getElementAt(min))<0)
					min=j;
			if(min!=i) {
				T temp=(T)listmodel.getElementAt(i);
				listmodel.setElementAt((T)listmodel.getElementAt(min), i);
				listmodel.setElementAt(temp, min);
			}
		}
	}
	public static void main(String[] args) {
		Person[] pers= {new Person(),new Person()};
		new PersonJFrame(pers,new PersonJPanel());
	}

}
