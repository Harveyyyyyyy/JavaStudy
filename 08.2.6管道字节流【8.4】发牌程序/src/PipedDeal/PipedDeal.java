package PipedDeal;
import java.io.*;
public class PipedDeal {
	public PipedDeal(int cardMax,int number) throws IOException{
		PipedInputStream[] pipedins=new PipedInputStream[number];
		PipedOutputStream[] pipedouts=new PipedOutputStream[number];
		for(int i=0;i<number;i++) {
			pipedins[i]=new PipedInputStream();
			pipedouts[i]=new PipedOutputStream(pipedins[i]);
		}
		new CardSendToStreamThread(pipedouts,cardMax).start();
		String[] titles= {"北","东","南","西"};
		int x[]= {300,550,300,50},y[]= {200,320,440,320};
		for(int i=0;i<number;i++)
			new CardReceiveFromStreamJFrame(pipedins[i],titles[i],x[i],y[i]);
	}
	public static void main(String args[]) throws IOException {
		new PipedDeal(52,4);
	}

}
