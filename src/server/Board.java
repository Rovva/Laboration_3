package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Board extends JPanel implements Observer {

		Square[][] map;
		int boardXSize, boardYSize, squareSize;
		Server server;
		
		public Board(Server server, int x, int y, int squareSize) {
			this.server = server;
			server.addObserver(this);
			map = new Square[x][y];
			for(int i = 0; i < x; i++) {
				for(int j = 0; j < y; j++) {
					map[i][j] = new Square();
				}
			}
			this.squareSize = squareSize;
			this.boardXSize = x;
			this.boardYSize = y;
			Dimension d = new Dimension(x*squareSize, y*squareSize);
			this.setMinimumSize(d);
			this.setPreferredSize(d);
			this.setMaximumSize(d);
			this.setBackground(Color.BLACK);
			this.setVisible(true);
		}
		
		public int getSquare(int x, int y) {
			return map[x][y].getColor();
		}
		
		public void setSquare(int x, int y, int color) {
			map[x][y].setColor(color);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			this.repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			paintSquares(g);
		}
		
		public void paintSquares(Graphics g) {
			//g.fillOval(10, 10, 20, 20);
			System.out.println(this.boardXSize);
			for(int i = 0; i < this.boardXSize; i++) {
				for(int j = 0; j < this.boardYSize; j++) {
					g.setColor(Color.red);
					g.fillOval((this.squareSize)*i,
							(this.squareSize)*j,
							(this.squareSize),
							(this.squareSize));
				}
			}
		}
}
