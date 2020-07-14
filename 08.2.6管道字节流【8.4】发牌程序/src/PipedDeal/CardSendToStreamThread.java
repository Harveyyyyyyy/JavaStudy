package PipedDeal;
import java.io.*;
public class CardSendToStreamThread extends Thread{
	private OutputStream[] outs;
	private int cardMax;
	
	public CardSendToStreamThread(OutputStream[] outs,int cardMax) {
		this.outs=outs;
		this.cardMax=cardMax;
		this.setPriority(Thread.MAX_PRIORITY);
	}
	public void run() {
		DataOutputStream[] dataouts=new DataOutputStream[this.outs.length];
		for(int i=0;i<dataouts.length;i++)
			dataouts[i]=new DataOutputStream(this.outs[i]);
		try {
			int value=1;
			while(value<=this.cardMax)
				for(int i=0;value<=this.cardMax&&i<dataouts.length;i++)
					dataouts[i].writeInt(value++);
			for(int i=0;i<dataouts.length;i++) {
				dataouts[i].close();
				this.outs[i].close();
			}
		}catch(IOException ex) {
			
		}
	}

}
