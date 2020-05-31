package QueryFrame;
import java.awt.*;
import java.awt.event.*;
public class QueryFrame extends Frame implements ActionListener{   
	private TextField text_char,text_uni;
	private Button button_char,button_uni;
	
	public QueryFrame() {
		super("�ַ������ѯ��");
		this.setBounds(300, 240, 310, 90);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new GridLayout(2, 3));
		
		this.add(new Label("�ַ�",Label.RIGHT));
		this.text_char=new TextField("����",10);
		this.add(this.text_char);
		this.button_char=new Button("��ѯUnicode��");
		this.add(this.button_char);
		this.button_char.addActionListener(this);
		
		this.add(new Label("Unicode����",Label.RIGHT));
		this.add(this.text_uni=new TextField(10));
		this.add(this.button_uni=new Button("��ѯ�ַ�"));
		this.button_uni.addActionListener(this);
		
		this.setVisible(true);
		this.addWindowListener(new WinClose());
	}

	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()==this.button_char) {
			String str=this.text_char.getText();
			if(!str.isEmpty()) {
				char ch=str.charAt(0);
				this.text_char.setText(""+ch);
				this.text_uni.setText(""+(int)ch);
			}
		}
		else if(ev.getSource()==this.button_uni) {
			String str=this.text_uni.getText();
			if(!str.isEmpty()) {
				this.text_char.setText(""+(char)Integer.parseInt(str));
			}
		}
		
		
	}
	public static void main(String[] arg) {
		new QueryFrame();
	}
}


