package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class Serverdownlink extends Thread{


	public Socket client;
	public int port;
	public String ip_address;
	public void setSocket(Socket client){
		this.client = client;
		ip_address = client.getInetAddress().toString().substring(1) + ":" + client.getPort();
	}
	Serverdownlink(int port){
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
		
		try{
			//client.setSoTimeout(Definition.RECV_TIMEOUT);

			//client=new Socket();
			InputStreamReader in = new InputStreamReader(client.getInputStream());
			OutputStreamWriter out = new OutputStreamWriter(client.getOutputStream());
			char buffer[] = new char[20480];
			
			StringBuilder prefix_sb = new StringBuilder("");
			int bytes_read = in.read(buffer);
			StringBuilder startmessage = new StringBuilder("");
			startmessage.append(buffer, 0, bytes_read);
			String initiate = startmessage.toString();
			
			
			if (initiate.equals("start")){	
				print("starting downlink:");
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
				print("ended, packets sent: " + packets);

			}
			else{
				print("initiate failed! message: " + initiate);
			}
			in.close();
			out.close();
			client.close();

			//System.out.println("Downlink worker <" + id + "> Thread ends");

		} catch (IOException e) {
			e.printStackTrace();
			print("error");


		}
	}
	
	public void print(String s){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		System.out.println(dateFormat.format(date) + "\t" + this.ip_address + "\t:\t" + s);
	}

}

