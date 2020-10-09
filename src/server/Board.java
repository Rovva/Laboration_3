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
			this.setBackground(Color.WHITE);
			this.setVisible(true);
		}
		
		public int getSquareColor(int x, int y) {
			return this.map[x][y].getColor();
		}
		
		public void setSquareColor(int x, int y, int color) {
			this.map[x][y].setColor(color);
			System.out.println("Color " + color + " set at " + x + " " + y);
		}
		
		public void resetSquares() {
			for(int i = 0; i < this.boardXSize; i++) {
				System.out.println("i: " + i);
				for(int j = 0; j < this.boardYSize; j++) {
					System.out.println("Reseting " + i + " " + j);
					this.setSquareColor(i, j, 0);
				}
			}
		}
		
		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			this.repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.paintSquares(g);
		}
		
		private Color determineColor(int color) {
			if(color == 0) {
				return Color.WHITE;
			} else if(color == 1) {
				return Color.BLACK;
			} else if(color == 2) {
				return Color.RED;
			} else if(color == 3) {
				return Color.GREEN;
			} else if(color == 4) {
				return Color.BLUE;
			} else if(color == 5) {
				return Color.YELLOW;
			} else if(color == 6) {
				return Color.PINK;
			} else if(color == 7) {
				return Color.MAGENTA;
			} else if(color == 8) {
				return Color.CYAN;
			}
			return Color.WHITE;
		}
		
		public void paintSquares(Graphics g) {
			for(int i = 0; i < this.boardXSize; i++) {
				for(int j = 0; j < this.boardYSize; j++) {
					int temp = getSquareColor(i, j);
					Color tempColor = determineColor(temp);
					g.setColor(tempColor);
					g.fillRect((this.squareSize)*i,
							(this.squareSize)*j,
							(this.squareSize),
							(this.squareSize));
				}
			}
		}
}
