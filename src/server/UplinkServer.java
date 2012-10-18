package server;
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
		PrintWriter os=null;


		try {
			//while(true){
			//clientSocket = myServer.accept();
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintWriter(client.getOutputStream(), true);

			System.out.println("Uplink started");
			String message = "";
			String receivemessage= null;
			long start_time=0, end_time = 0;
			int count = 0;
			int flag = 0;
			
			while(!(receivemessage = is.readLine()).equals("end")){
				if(flag==0)
				{
					start_time = System.currentTimeMillis();
					flag = 1;
				}
				message+=receivemessage;
				end_time = System.currentTimeMillis();
				//count++;
				//receivemessage = is.readLine();
				//end_time = System.currentTimeMillis();
			}
			end_time=System.currentTimeMillis(); 
			byte[] array = message.getBytes();
			int total = array.length;
			long time = end_time-start_time; 
			System.out.println("Sending details "+ time + " " + total + " " + count + " " + message.length());
			os.println(total);
			os.println(time);
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
