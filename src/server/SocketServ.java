package server;
import java.io.*;
import java.net.*;

public class SocketServ {
    public static void main(String args[]) {
        ServerSocket myServer = null;
        BufferedReader is;
        PrintStream os;
        Socket clientSocket = null;
        try {
           myServer = new ServerSocket(9901);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
        try {
        	while(true){
	           clientSocket = myServer.accept();
	           is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	           os = new PrintStream(clientSocket.getOutputStream());
	           
	           char[] receivemessage=new char[30000];
	           while((is.read(receivemessage))>-1){
	        	   System.out.println("GOT IT");
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