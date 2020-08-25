import javax.swing.*;
import java.io.*;
import java.util.*;
public class CollectionFile {
	public static<T>void readFrom(String filename,Collection<T> coll){//´ò¿ª
		try {
			InputStream in=new FileInputStream(filename);
			ObjectInputStream objin=new ObjectInputStream(in);
			coll.clear();
			while(true) {
				try {
					coll.add((T)objin.readObject());
				}catch(EOFException ex) {
					break;
				}
			}
			objin.close();
			in.close();
		}catch(IOException | ClassNotFoundException ex) {}
	}
	
	public static<T>void writeTo(String filename,Collection<T> coll){//±£´æ
		try {
			OutputStream out=new FileOutputStream(filename);
			ObjectOutputStream objout=new ObjectOutputStream(out);
			for(T obj:coll)
				objout.writeObject(obj);
			objout.close();
		}catch(IOException ex) {}
	}
}