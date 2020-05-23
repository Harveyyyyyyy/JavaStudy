package AddFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
public class AddFrame extends Frame implements ActionListener {
	JComboBox<String>jc=new JComboBox<>(new MyComboBox());
	private Button button;
	private TextField t1,t2,t3;
	public AddFrame() {
		super("�ӷ�����");
		this.setSize(400, 100);
		this.setLocation(300, 240);
		this.setLayout(new FlowLayout());
		this.add(this.t1=new TextField("10",8));
		this.add(jc);
		this.add(this.t2=new TextField("20",8));
		this.add(this.button=new Button("="));
		this.add(this.t3=new TextField(10));
		this.setVisible(true);
		this.addWindowListener(new WinClose());
		this.button.addActionListener(this);
		
		
	}
	public static void main(String[] arg) {
		new AddFrame();
	}
	public class WinClose implements WindowListener{
		public void windowClosing(WindowEvent event) {
			System.exit(0);
		}
		public void windowOpened(WindowEvent event) {}
		public void windowActivated(WindowEvent event) {}
		public void windowDeactivated(WindowEvent event) {}
		public void windowIconified(WindowEvent event) {}
		public void windowDeiconified(WindowEvent event) {}
		public void windowClosed(WindowEvent event) {}
	}
	
	public void actionPerformed(ActionEvent ev) {
		int num1 = Integer.parseInt(t1.getText());
		int num2 = Integer.parseInt(t2.getText());
		int num3=num1+num2;
		String str = String.valueOf(num3);
		t3.setText(str);
		
	}
	class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String>{
		String selecteditem="+";
		String[] test= {"+","-","*","/"};
		public String getElementAt(int index) {
			return test[index];
		}
		public int getSize() {
			return test.length;
		}
		public void setSelectedItem(Object item) {
			selecteditem=(String)item;
		}
		public Object getSelectedItem() {
			return selecteditem;
		}
		
	}
}
