package com.ChatApp;

import java.net.*;
import java.io.*;

//this code is from https://www.baeldung.com/a-guide-to-java-sockets
//The goal of this is to learn about sockets and how to make connections in java

public class Server {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port) throws IOException {
		System.out.println("Server start() begins");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		System.out.println("Server socket connection established.");
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Server io created.");
		String greeting = in.readLine();
		if ("Hello server world.".equals(greeting)){
			out.println("hello client");
			System.out.println("Hello client - from server");
		}
		else {
			out.println("unrecognized greeting");
		}
	}
	
	public void stop() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		System.out.println("Server stopped.");
	}
	
	public static void main(String[] Args) throws IOException {
		Server server = new Server();
		server.start(6669);
		server.stop();
	}
}
