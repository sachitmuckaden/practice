package server;
import java.io.*;
import java.net.*;

public class SocketServ{
	ServerSocket myServer;
	Socket clientSocket;
	SocketServ(int port){
		try {
	           myServer = new ServerSocket(port);
	        }
	        catch (IOException e) {
	           System.out.println(e);
	        }
	}
	/*public void setSocket(Socket client){
		this.client = client;
		//ip_address = client.getInetAddress().toString().substring(1) + ":" + client.getPort();
	}*/
	
    public void main()throws IOException {
        ServerSocket myServer = new ServerSocket(9905);
        BufferedReader is;
        PrintStream os;
        
           
        try {
        	while(true){
	           clientSocket = myServer.accept();
	           is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	           os = new PrintStream(clientSocket.getOutputStream());
	           
	           char[] receivemessage=new char[30000];
	           while((is.read(receivemessage))>-1){
	        	   //System.out.println("GOT IT");
	           }
	           //os.write(receivemessage.length);
	           //os.flush();
	           is.close();
	           os.close();
	           clientSocket.close();
        	}
        	}   
    catch (IOException e) {
           System.out.println(e);
        }
    }
}