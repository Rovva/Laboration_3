package server;
/**
 * @author Christoffer Rova
 * @version 1.0
 * @date 2020-10-09
 * This class is used for creating Square objects which is used to store the
 * color that a single square on the board might have.
 */
public class Square {
	
	private int color;
	// Constructor that sets its color to "nothing" or white, depending on how you see it.
	public Square() {
		this.color = 0;
	}
	
	/**
	 * Returns a integer representing a color.
	 * @return
	 */
	public int getColor() {
		return this.color;
	}
	
	/**
	 * Sets the integer color with the integer parameter color.
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}
}
