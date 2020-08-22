import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LoveJFrameServer {
	public LoveJFrameServer(String name,int port) throws IOException{
		ServerSocket server=new ServerSocket(port);
		LoveJFrame draw=new LoveJFrame(name);
		draw.remove(draw.sleeppanel);
		draw.remove(draw.cmdpanel);
		draw.setTitle(draw.getTitle()+":"+port);
		Socket socket=server.accept();
		draw.setSocket(socket);
		server.close();
	}
	public static void main(String args[]) throws IOException{
		new LoveJFrameServer("心形线同步", 10011);
	}
}
