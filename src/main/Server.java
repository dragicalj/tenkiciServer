package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.plaf.synth.SynthTableHeaderUI;



public class Server {

	static Player player1 = null;
	static Player player2 = null;
	static DatagramSocket serverSocket = null;
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		
		serverSocket = new DatagramSocket(12000);
		
		byte[] receiveData = new byte[1024];
		
		int counter = 1;
		
		while(true) {
			DatagramPacket packetFromClient = new DatagramPacket(receiveData, receiveData.length);
			
			System.out.println("Waiting for connection");
			serverSocket.receive(packetFromClient);
			
			
			InetAddress address = packetFromClient.getAddress();
			System.out.println(address.toString());
			int portNumber = packetFromClient.getPort();
			
			if(counter==3 && player1 != null && player2 != null) {
				
				String input = new String(packetFromClient.getData()).trim(); //trims extra white space from buffer

				System.out.println(player1.getInetAdress().toString());
				System.out.println(address.equals(player1.getInetAdress()));
				
				if(address.equals(player1.getInetAdress())){
					
					input = "2,"+input;
					
					System.out.println(input);
					
					sendToP1(input);
					
				}
				else {
					input = "1,"+input;
					System.out.println(input);
					sendToP2(input);
				}
			}
			
			if(counter==2&&player2==null) {
				player2 = new Player(address,portNumber);
				

				
				sendToP2("2");
				sendToP1("1");
				
				
				
				counter++;
				
				System.out.println("Inicijalizovan drugi");
			}
			
			if(counter ==1 && player1==null ) {
				player1 = new Player(address,portNumber);
				
				
				
				System.out.println("Inicijalizovan prvi");
				counter++;
				
			}
			
			
			
			
			
			
			
		
			
		}		
	}
	
	
	static void sendToP1(String message) throws IOException {
		
		byte[] sendData = message.getBytes();
		DatagramPacket packetForClient = new DatagramPacket(sendData, sendData.length, player2.getInetAdress(), player2.getPort());
		serverSocket.send(packetForClient);
		
	}
	
	static void sendToP2(String message) throws IOException {
		
		byte[] sendData = message.getBytes();
		DatagramPacket packetForClient = new DatagramPacket(sendData, sendData.length, player1.getInetAdress(), player1.getPort());
		serverSocket.send(packetForClient);
		
	}
	
	
}
