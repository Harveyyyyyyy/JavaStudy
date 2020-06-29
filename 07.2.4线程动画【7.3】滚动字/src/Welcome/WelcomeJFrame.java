package Welcome;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class WelcomeJFrame extends JFrame {
	public WelcomeJFrame(String[] texts) {
		super("滚动字");
		this.setBounds(300, 300, 850, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		if(texts==null||texts.length==0)
			this.getContentPane().add(new RollbyJPanel("Welcome!"));
		else {
			this.getContentPane().setLayout(new GridLayout(texts.length,1));
			for(int i=0;i<texts.length;i++)
				this.getContentPane().add(new RollbyJPanel(texts[i]));
		}
		this.setVisible(true);
	}
	public WelcomeJFrame() {
		this(null);
	}
	private class RollbyJPanel extends JPanel implements ActionListener,Runnable{
		JTextField text_word,texts[];
		JButton[] buttons;
		Thread thread;
		JRadioButton[] radioButton;
		int sleeptime;
		String direction;
		Font font=new Font("宋体",1,20);
		public RollbyJPanel(String text) {
			this.setLayout(new GridLayout(2,1));
			this.text_word=new JTextField(String.format("%65s", text));
			this.add(this.text_word);
			this.text_word.setFont(font);
			
			JPanel cmdpanel=new JPanel();
			this.add(cmdpanel);
			String[] textstr= {"sleeptime","State1","State2","isAlive"};
			this.texts=new JTextField[textstr.length];
			for(int i=0;i<this.texts.length;i++) {
				cmdpanel.add(new JLabel(textstr[i]));
				cmdpanel.add(this.texts[i]=new JTextField(8));
				this.texts[i].setEditable(false);
			}
			this.sleeptime=(int)(Math.random()*100);
			this.texts[0].setText(""+sleeptime);
			this.texts[0].setEditable(true);
			this.texts[0].addActionListener(this);
			String[] buttonstr= {"启动","中断"};
			this.buttons=new JButton[buttonstr.length];
			for(int i=0;i<buttonstr.length;i++) {
				cmdpanel.add(this.buttons[i]=new JButton(buttonstr[i]));
				this.buttons[i].addActionListener(this);
			}
			this.buttons[1].setEnabled(false);
			
			ButtonGroup bgroup=new ButtonGroup();
			String[] radstr= {"左","右"};
			this.radioButton=new JRadioButton[radstr.length];
			for(int i=0;i<radstr.length;i++) {
				cmdpanel.add(radioButton[i]=new JRadioButton(radstr[i]));
				bgroup.add(this.radioButton[i]);
				radioButton[i].addActionListener(this);
			}
			radioButton[0].setSelected(true);
			this.direction="left";
			
			this.thread=new Thread(this);
			this.texts[1].setText(""+this.thread.getState());
			this.texts[3].setText(""+this.thread.isAlive());
		}
		public void run() {
			while(true) {
				try {
					String str=this.text_word.getText();
					if(this.direction=="left")
				      this.text_word.setText(str.substring(1)+str.substring(0,1));
					else
					  this.text_word.setText(str.substring(str.length()-1,str.length())+str.substring(0,str.length()-1));
					Thread.sleep(this.sleeptime);
				}catch(InterruptedException ex) {
					break;
				}			}
		}
		public void actionPerformed(ActionEvent ev) {
			if(ev.getSource()==this.texts[0]) {
				try {
					this.sleeptime=Integer.parseInt(this.texts[0].getText());
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "\""+this.texts[0].getText()+"\"不能转换成整数，请重新输入！");
				}
			}else if(ev.getSource()==this.buttons[0]) {
				if(this.thread.getState()!=Thread.State.NEW)
					this.thread=new Thread(this);
				this.thread.start();
				this.texts[1].setText(""+this.thread.getState());
				this.buttons[0].setEnabled(false);
				this.buttons[1].setEnabled(true);
				
				this.texts[2].setText(""+this.thread.getState());
			}else if(ev.getSource()==this.buttons[1]) {
				this.thread.interrupt();
				this.texts[1].setText(""+this.thread.getState());
				this.buttons[0].setEnabled(true);
				this.buttons[1].setEnabled(false);
				
				this.texts[2].setText(""+this.thread.getState());
			}else if(ev.getSource()==radioButton[1]) {
				this.direction="right";
			}else if(ev.getSource()==radioButton[0]) {
			    this.direction="left";	
			}
			this.texts[3].setText(""+this.thread.isAlive());
		}
	}
	public static void main(String args[]) {
		String texts[]= {"Welcome","Hello","Rollby"};
		new WelcomeJFrame(texts);
	}

}
