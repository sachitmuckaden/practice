import java.io.*;
import java.net.*;

public class SocketServ {
    public static void main(String args[]) {
        ServerSocket myServer = null;
        String line;
        BufferedReader is;
        PrintStream os;
        Socket clientSocket = null;
        try {
           myServer = new ServerSocket(9997);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
        try {
           clientSocket = myServer.accept();
           is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
           os = new PrintStream(clientSocket.getOutputStream());
           do {
             line = is.readLine();
             System.out.println(line); 
           }while (!line.equals("end"));
        }   
    catch (IOException e) {
           System.out.println(e);
        }
    }
}