package client;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
/**
 * @author Christoffer Rova
 * @version 1.0
 * date 2020-10-09
 * This class contains all the code needed for the GUI.
 */
public class GUI implements Observer, ActionListener {
	JFrame frame;
	JPanel panel;
	SpringLayout layout;
	Container contentPane;
	JTextField commandField;
	JButton sendButton;
	
	JLabel changeInfo;
	JLabel resetInfo;
	JLabel colorInfo;
	
	Client client;
	/**
	 * GUI is the constructor that takes a reference to the client object as parameter
	 * and setups all the objects needed to display a GUI.
	 * @param client The reference to client object.
	 */
	public GUI(Client client) {
		this.client = client;
		frame = new JFrame("Color changer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(310, 400);
		layout = new SpringLayout();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		contentPane.setBackground(Color.WHITE);
		
		this.sendButton = new JButton("Send");
		this.commandField = new JTextField("", 20);
		
		this.contentPane.add(sendButton);
		this.contentPane.add(commandField);
		
		this.sendButton.setVisible(true);
		this.commandField.setVisible(true);
		this.sendButton.addActionListener(this);
		
		this.layout.putConstraint(SpringLayout.NORTH, commandField, 1, SpringLayout.NORTH, contentPane);
		this.layout.putConstraint(SpringLayout.WEST, commandField, 1, SpringLayout.WEST, contentPane);
		
		this.layout.putConstraint(SpringLayout.NORTH, sendButton, 1, SpringLayout.NORTH, contentPane);
		this.layout.putConstraint(SpringLayout.WEST, sendButton, 5, SpringLayout.EAST, commandField);
		
		this.changeInfo = new JLabel("Change: 0 x y color");
		this.resetInfo = new JLabel("Reset: 1");
		this.colorInfo = new JLabel("<html>Colors:<br>"
				+ "0 = Nothing<br>" + 
				"1 = Black<br>" + 
				"2 = Red<br>" + 
				"3 = Green<br>" + 
				"4 = Blue<br>" + 
				"5 = Yellow<br>" + 
				"6 = Pink<br>" + 
				"7 = Magenta<br>" + 
				"8 = Cyan</html>");
		
		this.contentPane.add(changeInfo);
		this.contentPane.add(resetInfo);
		this.contentPane.add(colorInfo);
		
		this.layout.putConstraint(SpringLayout.NORTH, changeInfo, 2, SpringLayout.SOUTH, commandField);
		this.layout.putConstraint(SpringLayout.WEST, changeInfo, 1, SpringLayout.WEST, contentPane);
		
		this.layout.putConstraint(SpringLayout.NORTH, resetInfo, 2, SpringLayout.SOUTH, changeInfo);
		this.layout.putConstraint(SpringLayout.WEST, resetInfo, 1, SpringLayout.WEST, contentPane);
		
		this.layout.putConstraint(SpringLayout.NORTH, colorInfo, 2, SpringLayout.SOUTH, resetInfo);
		this.layout.putConstraint(SpringLayout.WEST, colorInfo, 1, SpringLayout.WEST, contentPane);
		
		this.changeInfo.setVisible(true);
		this.resetInfo.setVisible(true);
		this.colorInfo.setVisible(true);
		
		frame.setVisible(true);
	}
	/**
	 * This method manages the input generated when clicking the send button.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String op = arg0.getActionCommand();
		if(op == "Send") {
			String[] split = commandField.getText().split(" ");
			try {
				if(split[0].equals("0")) {
					byte data[] = {Byte.valueOf(split[0]), Byte.valueOf(split[1]),
							Byte.valueOf(split[2]), Byte.valueOf(split[3])};
					client.sendUDP(data);
				} else if(split[0].equals("1")) {
					byte data[] = {1};
					client.sendUDP(data);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * This method repaints the gui.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		frame.repaint();
	}
}
