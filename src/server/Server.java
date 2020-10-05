package server;

import java.util.Observable;

public class Server extends Observable {

	Board board;
	GUI gui;
	
	public Server() {
		board = new Board(this, 201, 201, 5);
		gui = new GUI(this, this.board);
	}
}
