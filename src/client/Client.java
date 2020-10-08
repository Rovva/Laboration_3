package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.util.Observable;

public class Client extends Observable {

	DatagramSocket socketUDP;
	DatagramPacket packetUDP;
	byte[] dataUDP = new byte[4];
	
	public Client(DatagramSocket socketUDP) {
		this.socketUDP = socketUDP;
	}
	
	public void sendUDP(byte[] data) throws IOException {
		System.out.println(Inet6Address.getByAddress(null, new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 4444).toString());
		packetUDP = new DatagramPacket(dataUDP, dataUDP.length, Inet6Address.getByAddress(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), 4444);
		packetUDP.setData(data);
		socketUDP.send(packetUDP);
		
	}
	
}
