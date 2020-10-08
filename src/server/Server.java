package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;

import shared.Messages;

public class Server extends Observable {

	Board board;
	GUI gui;
	DatagramSocket socketUDP;
	packetReader reader;
	Thread thread;
	
	public Server(int port) throws SocketException, UnknownHostException {
		this.socketUDP = new DatagramSocket(port, Inet6Address.getByAddress(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}));
		this.board = new Board(this, 201, 201, 5);
		this.gui = new GUI(this, this.board);
		reader = new packetReader(socketUDP, board);
		thread = new Thread(reader);
		thread.start();
	}
	
	public class packetReader implements Runnable {
		
		boolean runThread;
		Board board;
		DatagramSocket socketUDP;
		DatagramPacket packetUDP;
		byte[] dataUDP = new byte[4];
		
		public packetReader(DatagramSocket socketUDP, Board board) {
			this.socketUDP = socketUDP;
			this.packetUDP = new DatagramPacket(dataUDP, dataUDP.length);
			this.runThread = true;
			this.board = board;
		}
		
		private void packetProcessor(byte[] data) {
			if(data[0] == Messages.CHANGE.ordinal()) {
				System.out.println("Changing color...");
				board.setSquareColor((int) data[1], (int) data[2], (int) data[3]);
				setChanged();
				notifyObservers();
			} else if(data[0] == Messages.RESET.ordinal()) {
				System.out.println("Resetting colors...");
				board.resetSquares();
				setChanged();
				notifyObservers();
			}
		}
		
		public void stopThread() {
			this.runThread = false;
		}
		
		@Override
		public void run() {
			while(runThread) {
	            try {
					socketUDP.receive(packetUDP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            dataUDP = packetUDP.getData();
		            System.out.println(dataUDP[0] + " " + dataUDP[1] + " " + dataUDP[2] + " " + dataUDP[3]);
		            this.packetProcessor(dataUDP);
					setChanged();
					notifyObservers();
			}
			// TODO Auto-generated method stub
			
		}
		
	}
}
