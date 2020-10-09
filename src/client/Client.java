package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.util.Observable;

/**
 * @author Christoffer Rova
 * @version 1.0
 * @date 2020-10-09
 * This is the class that handles communication to the server.
 */
public class Client extends Observable {

	DatagramSocket socketUDP;
	DatagramPacket packetUDP;
	byte[] dataUDP = new byte[4];
	/**
	 * Client is the constructor.
	 * @param socketUDP The datagram socket needed for communication.
	 */
	public Client(DatagramSocket socketUDP) {
		this.socketUDP = socketUDP;
	}
	/**
	 * sendUDP is the method for sending data to the server.
	 * @param data The byte array that will be sent to server.
	 * @throws IOException
	 */
	public void sendUDP(byte[] data) throws IOException {
		System.out.println(Inet6Address.getByAddress(null, new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 4444).toString());
		packetUDP = new DatagramPacket(dataUDP, dataUDP.length, Inet6Address.getByAddress(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}), 4444);
		packetUDP.setData(data);
		socketUDP.send(packetUDP);
		
	}
	
}
