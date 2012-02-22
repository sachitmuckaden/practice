package src.server;
import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UplinkServer extends Thread{
	//ServerSocket myServer;
	public Socket client;
	String ip_address;
	public int port;
	
	public UplinkServer(int port){
		this.port=port;
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

			print("Uplink started");
			char[] receivemessage=new char[30000];
			while((is.read(receivemessage))>-1){

			}
			//os.write(receivemessage.length);
			//os.flush();
			//System.out.println("GOT IT");
			print("Uplink ended");
			is.close();
			os.close();
			client.close();
		}
		//}   
		catch (IOException e) {
			try{
				is.close();
				os.close();
				client.close();
				print("Error while receiving upload. Error code 1.");
			}
			catch(IOException e3){
				print("Error code 2.");
			}
			System.out.println(e);
		}
	}

	public void print(String s)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try{
			FileWriter fstream = new FileWriter(Values.UPLINKFILENAME);
			BufferedWriter of = new BufferedWriter(fstream);
			of.write(dateFormat.format(date) + "\t" + this.ip_address + "\t:\t" + s);
			of.close();
		}
		catch (Exception e){
			System.out.println("Error while writing to file: \t" +Values.UPLINKFILENAME+"\t"+ e);
		}
		
	}
}
