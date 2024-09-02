package com.ChatApp;

import com.ChatApp.Server;
import java.io.IOException;
import com.ChatApp.Client;

/*This is a basic chat app to explore programming parts of an app such as networking/ sockets, 
 * having server side vs client side code, and performing a basic user authentication.
 * 
 * I can reuse code from the to-do project for the server side code
*/ 


public class Main {
	
	public static void main(String[] Args) throws IOException  {
		Client client = new Client();
		Server server = new Server();
		int port = 6669;
		
		Thread serverThread = new Thread(() -> {
			try {
				server.start(port);
				server.stop();
				System.out.println("serverThread complete");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		Thread clientThread = new Thread(()-> {
			try {
				Thread.sleep(1000);
				client.startConnection("127.0.0.1", port);
				client.sendMessage();
				client.stopConnection();
				System.out.println("client thread complete");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		serverThread.start();
		clientThread.start();
		
	}
}
