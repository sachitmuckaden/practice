package server;


import java.io.*;
import java.net.*;

public class UplinkStarter extends BaseServer{
	
	public static void main(String[] args)
	{
		
		int port  = Values.UPLINKPORT;
		while(true)
			{
				System.out.println("UPLINK SERVER starting on port " + Values.UPLINKPORT);
				UplinkStarter upstart=new UplinkStarter();
				upstart.listen(port);
						
			}
	
	
	}
	

}