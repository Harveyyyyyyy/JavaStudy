package PipedDeal;
import java.awt.Font;
import javax.swing.*;
import java.io.*;
public class CardReceiveFromStreamJFrame extends JFrame implements Runnable{
	private InputStream in;
	private JTextArea text;
	
	public CardReceiveFromStreamJFrame(InputStream in,String title,int x,int y) {
		super(title);
		this.setBounds(x, y, 250, 150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.in=in;
		this.text=new JTextArea();
		this.getContentPane().add(this.text);
		this.text.setLineWrap(true);
		this.text.setEditable(false);
		this.text.setFont(new Font("Arial",Font.PLAIN,20));
		this.setVisible(true);
		new Thread(this).start();
	}
	public void run() {
		DataInputStream datain=new DataInputStream(this.in);
		while(true) {
			try {
				text.append(String.format("%4d", datain.readInt()));
				Thread.sleep(100);
			}catch(IOException|InterruptedException ex) {
				break;
			}
		}
		try {
			datain.close();
			this.in.close();
		}catch(IOException ex) {}
	}

}
