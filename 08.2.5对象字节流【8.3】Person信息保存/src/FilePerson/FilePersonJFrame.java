package FilePerson;
import java.awt.event.*;
import java.io.*;
import Person.Person;
import PersonJFrame.PersonJFrame;
import PersonJFrame.PersonJPanel;
public class FilePersonJFrame extends PersonJFrame implements WindowListener{
	private String filename;
	
	public FilePersonJFrame(Person[] pers,PersonJPanel person,String filename) {
		super(pers,person);
		this.filename=filename;
		this.setTitle("读写Person对象文件"+filename);
		this.addWindowListener(this);
		ListModelObjectFile.readFrom(this.filename,this.listmodel);
	}
	public FilePersonJFrame(String filename) {
		this(null,new PersonJPanel(),filename);
	}
	public void windowOpened(WindowEvent e) {
		
	}
	public void windowClosing(WindowEvent e) {
		ListModelObjectFile.writeTo(this.filename,this.listmodel);
	}
	public void windowClosed(WindowEvent e) {
		
	}
	public void windowIconified(WindowEvent e) {
		
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
	public void windowActivated(WindowEvent e) {
		
	}
	public void windowDeactivated(WindowEvent e) {
		
	}
	public static void main(String[] args) {
		new FilePersonJFrame("Person.obj");
	}

}
