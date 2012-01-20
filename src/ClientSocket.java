/*import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
class ClientSocket
{
	public static void main(String[] args) throws IOException, FileNotFoundException
	{
		Socket MyClient;
		int PortNumber=5001;
	    MyClient = new Socket("127.0.0.1", 5004);
	    DataInputStream in = new DataInputStream(MyClient.getInputStream());
	    DataOutputStream out = new DataOutputStream(MyClient.getOutputStream());
	    if (MyClient != null && out != null && in != null) {
            try {
            	int a =10;          	
            	while(a==10){	
            	out.writeBytes("Hello");
            	}
            	
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                      break;
                    }
                
                }
            	
            	
                
                out.close();
                in.close();
                MyClient.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
	    
	    
	}
}
}*/

import java.io.*;
import java.net.*;
public class ClientSocket {
		public static void main(String[] args) {
        Socket smtpSocket = null;  
        DataOutputStream os = null;
        DataInputStream is = null;
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            smtpSocket = new Socket("127.0.0.1", 9997);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
        if (smtpSocket != null && os != null && is != null) {
            try {
            	String line=null;
            	os.writeBytes("HELO\n");    
                while(!line.equals("end")){
                	line=buf.readLine();
                	os.writeBytes(line);
                }
                os.writeBytes("QUIT\n");
            	/*String responseLine;
               while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                      break;
                    }
                }*/
                os.close();
                is.close();
                smtpSocket.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }           
}