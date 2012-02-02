package server;


import java.io.*;
import java.net.*;

public class DownlinkStarter extends BaseServer{
	
	public static void main(String[] args)
	{
		
		int port  = Values.DOWNLINKPORT;
		while(true)
			{
			System.out.println("DOWNLINK SERVER starting on port " + Values.DOWNLINKPORT);
				DownlinkStarter downstart=new DownlinkStarter();
				downstart.listen(port);
						
			}
	
	
	}
	

}