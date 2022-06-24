package main;

import java.net.InetAddress;

public class Player {
	
	InetAddress inetAdress;
	int port;
	
	public Player() {
		
	}
	
	public Player(InetAddress inetadress, int port) {
		this.inetAdress = inetadress;
		this.port = port;
	}
	
	
	public InetAddress getInetAdress() {
		return inetAdress;
	}
	public void setInetAdress(InetAddress inetAdress) {
		this.inetAdress = inetAdress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	

}
