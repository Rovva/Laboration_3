package client;

import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 * @author Christoffer Rova
 * @version 1.0
 * date 2020-10-09
 * The class needed to start the client GUI.
 */
public class main {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		// Create the needed objects for GUI.
		DatagramSocket socketUDP = new DatagramSocket();
		Client client = new Client(socketUDP);
		// Create the GUI object and send a reference to client in the constructor.
		GUI gui = new GUI(client);
	}

}
