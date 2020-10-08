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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class GUI implements Observer, ActionListener {
	JFrame frame;
	JPanel panel;
	SpringLayout layout;
	Container contentPane;
	JTextField commandField;
	JButton send;
	
	Client client;
	
	public GUI(Client client) {
		this.client = client;
		frame = new JFrame("Color changer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		layout = new SpringLayout();
		contentPane = frame.getContentPane();
		contentPane.setLayout(layout);
		contentPane.setBackground(Color.WHITE);
		
		this.send = new JButton("Send");
		this.commandField = new JTextField("", 20);
		
		this.contentPane.add(send);
		this.contentPane.add(commandField);
		
		this.send.setVisible(true);
		this.commandField.setVisible(true);
		this.send.addActionListener(this);
		
		this.layout.putConstraint(SpringLayout.NORTH, commandField, 1, SpringLayout.NORTH, contentPane);
		this.layout.putConstraint(SpringLayout.WEST, commandField, 1, SpringLayout.WEST, contentPane);
		
		this.layout.putConstraint(SpringLayout.NORTH, send, 1, SpringLayout.NORTH, contentPane);
		this.layout.putConstraint(SpringLayout.WEST, send, 5, SpringLayout.EAST, commandField);

		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		String op = arg0.getActionCommand();
		if(op == "Send") {
			String temp = commandField.getText();
			String[] split = temp.split(" ");
			byte[] data = new byte[4];
			int tempint = 0;
			if(split[0].equals("0")) {
				tempint = Integer.valueOf(split[0]);
				System.out.println("tempint: " + tempint);
				data[0] = (byte) tempint;
				tempint = Integer.valueOf(split[1]);
				data[1] = (byte) tempint;
				tempint = Integer.valueOf(split[2]);
				data[2] = (byte) tempint;
				tempint = Integer.valueOf(split[3]);
				data[3] = (byte) tempint;
			} else if(split[0].equals("1")) {
				data[0] = 1;
			}
			try {
				client.sendUDP(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		frame.repaint();
	}
}
