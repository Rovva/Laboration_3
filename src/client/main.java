package client;

import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.net.UnknownHostException;

public class main {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		// TODO Auto-generated method stub
		DatagramSocket socketUDP = new DatagramSocket();
		Client client = new Client(socketUDP);
		GUI gui = new GUI(client);
	}

}
