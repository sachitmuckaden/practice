package server;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UplinkServer extends Thread{
	ServerSocket myServer;
	Socket client;
	String ip_address;
	UplinkServer(int port){
		try {
			myServer = new ServerSocket(port);
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	public void setSocket(Socket client){
		this.client = client;
		ip_address = client.getInetAddress().toString().substring(1) + ":" + client.getPort();
	}

	public void run() {
		//ServerSocket myServer = null;
		BufferedReader is=null;
		PrintStream os=null;


		try {
			//while(true){
			//clientSocket = myServer.accept();
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintStream(client.getOutputStream());

			char[] receivemessage=new char[30000];
			while((is.read(receivemessage))>-1){

			}
			//os.write(receivemessage.length);
			//os.flush();
			System.out.println("GOT IT");
			print("Ended");
			is.close();
			os.close();
			client.close();
		}
		//}   
		catch (IOException e) {
			try{
				is.close();
				os.close();
			}
			catch(IOException e3){}
			System.out.println(e);
		}
	}

	public void print(String s)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		System.out.println(dateFormat.format(date) + "\t" + this.ip_address + "\t:\t" + s);
	}
}
