package FilePerson;
import javax.swing.*;
import java.io.*;
public class ListModelObjectFile{
	//打开
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
			JOptionPane.showMessageDialog(null, "\""+filename+"\"文件不存在。");
		}catch(ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "指定类未找到错误");
		}catch(IOException ex) {}
		}
	
	//保存
public static <T> void writeTo(String filename,ListModel<T> listmodel) {
	try {
		OutputStream out=new FileOutputStream(filename);
		ObjectOutputStream objout=new ObjectOutputStream(out);
		for(int i=0;i<listmodel.getSize();i++) 
			objout.writeObject(listmodel.getElementAt(i));
		objout.close();
		out.close();
	}catch(FileNotFoundException ex) {
		JOptionPane.showMessageDialog(null, "\""+filename+"\"文件名不存在。");
	}catch(IOException ex) {}
}
}
