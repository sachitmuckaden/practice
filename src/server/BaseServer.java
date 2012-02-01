package server;

import java.io.IOException;

import java.net.ServerSocket;

//import servers.CommandWorker;



class BaseServer{
	
	
	public static ServerSocket uplinkserver;
	public static ServerSocket downlinkserver;
public static void main(String[] args)throws IOException{
		int uplinkport=9904;
		int downlinkport=9708;
		try{
			
			uplinkserver = new ServerSocket(uplinkport);
			downlinkserver = new ServerSocket(downlinkport);
		} catch (IOException e) {
			e.printStackTrace();
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		    System.out.println("Could not listen on port " + port);
		    
			return;*/
		}
		while(true){
			
		    try{
		    	//server.accept returns a client connection
		    		
				    	DownlinkServer c = new DownlinkServer(downlinkport);
			    		c.setSocket(downlinkserver.accept());
			    		c.start();
		    		
			    	
			    		
		    	}
		    
		    catch (IOException e1) {
		    	System.out.println("Server failed: port <" + downlinkport + ">");
		    	return;
		    }
		    
		    try{
		    	
		    	UplinkServer s = new UplinkServer(uplinkport);
	    		s.setSocket(uplinkserver.accept());
	    		s.start();
		
		    }
		    catch (IOException e2) {
		    	System.out.println("Server failed: port <" + uplinkport + ">");
		    	return;
		    }
		}
	}
}



