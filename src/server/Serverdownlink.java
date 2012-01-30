package server;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Random;
public class Serverdownlink extends Thread{


	public Socket client;
	public int port;
	public void setSocket(Socket client){
		this.client = client;
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
			//System.out.println("prefix:" + prefix);
			//String prefix = "<iPhone><device_id><run_id>";
			//ystem.out.println("Server received prefix ok, start");
			System.out.println("initiate: " + initiate.equals("start"));
			if (initiate.equals("start")){	
				long start = System.currentTimeMillis();
				long end = System.currentTimeMillis();

				int batch = 0;

				while(end - start < 20000){

					//out.write();
					out.write(generateRandom()); //2600 larger than MTU
					System.out.println("downsend");
					out.flush();
					batch++;
					if(batch % 50 == 0){
						end = System.currentTimeMillis();
					}
				}


			}
			else{
				System.out.println("confused");
			}
			in.close();
			out.close();
			client.close();

			//System.out.println("Downlink worker <" + id + "> Thread ends");

		} catch (IOException e) {
			e.printStackTrace();


		}
	}

}

