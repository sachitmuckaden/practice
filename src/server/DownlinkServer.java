package src.server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DownlinkServer extends Thread{


	public Socket client;
	public int port;
	public String ip_address;

	public void setSocket(Socket client){
		this.client = client;
		ip_address = client.getInetAddress().toString().substring(1) + ":" + client.getPort();
	}

	public DownlinkServer(int port){
		this.port=port;
	}

	public static String generateRandom()
	{
		Random number=new Random();
		StringBuilder message=new StringBuilder();
		for(int i=0;i<1405;i++)
		{
			message.append('a'+number.nextInt(52));
		}
		return message.toString();
	}

	public void run(){

		InputStreamReader in = null;
		OutputStreamWriter out = null;
		try{
			
			in = new InputStreamReader(client.getInputStream());
			out = new OutputStreamWriter(client.getOutputStream());
			char buffer[] = new char[20480];
		
		

			print("Downlink started");
			long start = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			int packets = 0;
			int batch = 0;

			while(end - start < 20000){


				out.write(generateRandom());
				packets++;
				out.flush();
				batch++;
				if(batch % 50 == 0){
					end = System.currentTimeMillis();
				}
			}
			print("Downlink ended, packets sent: " + packets);

			in.close();
			out.close();
			client.close();

		} catch (IOException e) {
			e.printStackTrace();
			try{
				in.close();
				out.close();
			}
			catch(IOException e3)
			{

			}
			print("error");

		}
	}

	public void print(String s){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		System.out.println(dateFormat.format(date) + "\t" + this.ip_address + "\t:\t" + s);
	}

}

