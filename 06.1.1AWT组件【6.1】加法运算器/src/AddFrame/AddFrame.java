package AddFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
public class AddFrame extends Frame implements ActionListener{
	public AddFrame() {
		super("�ӷ�����");
		this.setSize(400, 100);
		this.setLocation(300, 240);
		this.setLayout(new FlowLayout());
		
		this.add(new TextField("10",8));
		this.add(new Label("+"));
		this.add(new TextField("20",8));
		this.add(new Button("="));
		this.add(new TextField(10));
		this.setVisible(true);
		this.addWindowListener(new WinClose());
		
	}
	public static void main(String[] arg) {
		new AddFrame();
	}
	public class WinClose implements WindowListener{
		public void windowClosing(WindowEvent event) {
			666
		}
	}
}
