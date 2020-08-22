package FilePerson;
import javax.swing.*;
import java.io.*;
public class ListModelObjectFile{
	//��
	public static<T> void readFrom(String filename,DefaultListModel<T> listmodel) {
		try {
			InputStream in=new FileInputStream(filename);
			ObjectInputStream objin=new ObjectInputStream(in);
			listmodel.removeAllElements();
			while(true) {
				try {
					listmodel.addElement((T)objin.readObject());
				}catch(EOFException eof) {
					break;
				}
			}
			objin.close();
			in.close();
		}catch(FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ������ڡ�");
		}catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "ָ����δ�ҵ�����");
		}catch(IOException ex) {}
		}
	
	//����
public static <T> void writeTo(String filename,ListModel<T> listmodel) {
	try {
		OutputStream out=new FileOutputStream(filename);
		ObjectOutputStream objout=new ObjectOutputStream(out);
		for(int i=0;i<listmodel.getSize();i++) 
			objout.writeObject(listmodel.getElementAt(i));
		objout.close();
		out.close();
	}catch(FileNotFoundException ex) {
		JOptionPane.showMessageDialog(null, "\""+filename+"\"�ļ��������ڡ�");
	}catch(IOException ex) {}
}
}
