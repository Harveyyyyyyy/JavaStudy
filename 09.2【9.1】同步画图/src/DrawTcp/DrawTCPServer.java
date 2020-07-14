package DrawTcp;
import java.net.*;
import java.io.*;
public class DrawTCPServer {
	public DrawTCPServer(String name,int port) throws IOException{
		ServerSocket server=new ServerSocket(port);
		DrawTCPSocketJFrame draw=new DrawTCPSocketJFrame(name);
		draw.setTitle(draw.getTitle()+":"+port);
		Socket socket=server.accept();
		draw.setSocket(socket);
		server.close();
	}
	public static void main(String args[]) throws IOException{
		new DrawTCPServer("»¨ÏÉ×Ó", 10011);
	}

}
