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
		
	}

}
