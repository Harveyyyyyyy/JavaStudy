package MoneyJFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class MoneyJFrame extends JFrame implements CaretListener,ComponentListener {
	private JTextField text_money,text_str;
	private MessageJDialog jdialog;
	
	public MoneyJFrame() {
		super("中文大写金额");
		this.setBounds(300, 240, 360, 110);
		this.setBackground(Color.LIGHT_GRAY);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addComponentListener(this);
		
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(new JLabel("金额",JLabel.RIGHT));
		this.text_money=new JTextField("12345678.90",40);
		this.getContentPane().add(this.text_money);
		this.text_money.setHorizontalAlignment(JTextField.RIGHT);
		this.text_money.addCaretListener(this);
		
		this.getContentPane().add(new JLabel("中文大写",JLabel.RIGHT));
		this.text_str=new JTextField(40);
		this.text_str.setHorizontalAlignment(JTextField.RIGHT);
		this.text_str.setEditable(false);
		this.getContentPane().add(this.text_str);
		
		caretUpdate(null);
		this.setVisible(true);
		this.jdialog=new MessageJDialog();
		
		
	}
	private class MessageJDialog extends JDialog{
		private JLabel jl;
		private MessageJDialog() {
			super(MoneyJFrame.this,"提示",true);
			this.setSize(420, 110);
			this.jl=new JLabel("",JLabel.CENTER);
			this.getContentPane().add(this.jl);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.addComponentListener(MoneyJFrame.this);
		}
		private void show(String message) {
			this.jl.setText(message);
			this.setLocation(MoneyJFrame.this.getX()+100, MoneyJFrame.this.getY()+100);
			this.setVisible(true);
		}
	}
	public void caretUpdate(CaretEvent ev) {
		String money=this.text_money.getText();
		if(money.isEmpty())
			this.text_str.setText("");
		else
			try {
				double x=Double.parseDouble(money);
				this.text_str.setText(RMB.toString(x));
			}catch(NumberFormatException ex) {
				this.jdialog.show("\""+money+"\"不能转换成浮点数.");
			}
	}
	public void componentResized(ComponentEvent ev) {
		Component comp=ev.getComponent();
		int size=(comp.getWidth()+comp.getHeight())/40;
		Font font=new Font("宋体",1,size);
		if(comp instanceof JFrame) {
			int n=this.getContentPane().getComponentCount();
			for(int i=0;i<n;i++)
				this.getContentPane().getComponent(i).setFont(font);
		}else if(comp instanceof JDialog)
			this.jdialog.jl.setFont(font);
		
	}

	public void componentMoved(ComponentEvent e) {		
	}
	public void componentShown(ComponentEvent e) {		
	}
	public void componentHidden(ComponentEvent e) {		
	}
	public static void main(String[] arg) {
		new MoneyJFrame();
	}

}
