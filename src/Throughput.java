import java.io.*;
import java.net.*;
import java.util.Random;

class Throughput
{
	
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
	
	public static void uplinkmeasurement() throws UnknownHostException, IOException
	{
		String serveraddress="128.61.119.87";
		SocketAddress serversocket = new InetSocketAddress(serveraddress,9901);
		Socket uplinkclient=new Socket();
		uplinkclient.connect(serversocket);
		
		DataInputStream in = new DataInputStream(uplinkclient.getInputStream());
		DataOutputStream out = new DataOutputStream(uplinkclient.getOutputStream());
		
		
		String buf= generateRandom();
		byte[] message = buf.getBytes();
		int throughput=0;
		int tensecthroughput=0;
		long start = System.currentTimeMillis();
		System.out.println(start);
		long end = System.currentTimeMillis();
		long intermediate = System.currentTimeMillis();
		int count=0;
		int tenseccount=0;
		int flag=0;
		do
		{
			out.write(message);
			end = System.currentTimeMillis();
			if(end-start>=15000){
				if(flag==0){intermediate= System.currentTimeMillis();
				flag=1;
				}
				tenseccount++;
			}
				
			count++;
		}while(end-start<=20000);
		throughput=count*(message.length+(54*3))/(int)(end-start)*8;
		tensecthroughput = tenseccount*message.length/(int)(end-intermediate)*8;
		try{
			Thread.sleep(2000);
		}
		catch(InterruptedException e1)
		{
			e1.printStackTrace();
		}
		//int length=0;
		//length=in.read();
		System.out.println("No of packets sent: "+count);
		System.out.println(throughput + "kbps");
		System.out.println(message.length);
		
		//throughput=count*length/(int)(end-start)*8;
		//System.out.println(throughput + "kbps");
		//System.out.println("Last 10 seconds: "+tensecthroughput+ " kbps");
		//System.out.println(end);
		out.close();
		in.close();
	}
	
	public static void downlinkmeasurement() throws IOException
	{
		String serveraddress="128.61.119.87";
		SocketAddress serversocket = new InetSocketAddress(serveraddress,9704);
		Socket downlinkclient=new Socket();
		downlinkclient.connect(serversocket);
		
		DataInputStream in = new DataInputStream(downlinkclient.getInputStream());
		DataOutputStream out = new DataOutputStream(downlinkclient.getOutputStream());
		String initiate="start";
		
		out.writeBytes(initiate);
		out.flush();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int messagebytes=0;
		int totalbytes=0;
		int count=0;
		long start=System.currentTimeMillis();
		long end=System.currentTimeMillis();
		byte[] buffer=new byte[12000];
		do
		{
			messagebytes=in.read(buffer, 0, 12000);
			count++;
			if(messagebytes<=0)
				break;
			System.out.println(messagebytes);
			totalbytes+=messagebytes;
			end=System.currentTimeMillis();
		}while(true);
		
		if(end-start>0) System.out.println(totalbytes*8/(int)(end-start)+ " kbps");
		out.close();
		in.close();
		downlinkclient.close();
	}
	
	
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		uplinkmeasurement();
	}

}