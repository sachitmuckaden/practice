package server;

import java.io.IOException;
import java.net.ServerSocket;

//import servers.CommandWorker;



class BaseServer{
	
	
	public static ServerSocket server;
	
public static void main(String[] args)throws IOException{
		int port=9703;
		try{
			
			server = new ServerSocket(port);
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
		    	
		    	Serverdownlink c = new Serverdownlink(port);
	    		c.setSocket(server.accept());
	    		c.start();
	    		
		    }
	    	
		    catch (IOException e1) {
		    	System.out.println("Server failed: port <" + port + ">");
		    	return;
		    }
		}
	}
}



