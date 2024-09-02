package com.ChatApp;

import java.io.*;
import java.net.*;

public class Client {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public void startConnection(String ip, int port) throws IOException {
		clientSocket = new Socket(ip, port);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Client socket created");
	}
	
	public String sendMessage() throws IOException {
		System.out.println("Client sending messge");
		out.println("Hello server world.");
		out.flush();
		String response = in.readLine();
		return response;
	}
	
	public void stopConnection() throws IOException {
		clientSocket.close();
		in.close();
		out.close();
		System.out.println("Client socket closed");
	}
}
