package server;

import java.io.IOException;

import java.net.ServerSocket;

//import servers.CommandWorker;



public class BaseServer{


	public ServerSocket server;
	
	
	public void listen(int port){
		
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


			switch(port){
			case (Values.DOWNLINKPORT):
				try{	
					DownlinkServer c = new DownlinkServer(port);
					System.out.println("incoming connection to downlink");
					c.setSocket(server.accept());
					c.start();
					break;


				}

			catch (IOException e1) {
				System.out.println("Server failed: port <" + port + ">");
				return;
			}
			case (Values.UPLINKPORT):
				try{

					UplinkServer s = new UplinkServer(port);
					System.out.println("incoming connection to uplink");
					s.setSocket(server.accept());
					s.start();
					break;
				}
			catch (IOException e2) {
				System.out.println("Server failed: port <" + port + ">");
				return;
			}
			}
		}
	}
}



