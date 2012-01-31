package server;
import java.io.*;
import java.net.*;

public class SocketServ extends Thread{
	ServerSocket myServer;
	Socket client;
	SocketServ(int port){
		try {
	           myServer = new ServerSocket(port);
	        }
	        catch (IOException e) {
	           System.out.println(e);
	        }
	}
	public void setSocket(Socket client){
		this.client = client;
		//ip_address = client.getInetAddress().toString().substring(1) + ":" + client.getPort();
	}
	
    public void run() {
        //ServerSocket myServer = null;
        BufferedReader is;
        PrintStream os;
        
           
        try {
        	//while(true){
	           //clientSocket = myServer.accept();
	           is = new BufferedReader(new InputStreamReader(client.getInputStream()));
	           os = new PrintStream(client.getOutputStream());
	           
	           char[] receivemessage=new char[30000];
	           while((is.read(receivemessage))>-1){
	        	   //System.out.println("GOT IT");
	           }
	           //os.write(receivemessage.length);
	           //os.flush();
	           is.close();
	           os.close();
	           client.close();
        	}
        //}   
    catch (IOException e) {
           System.out.println(e);
        }
    }
}