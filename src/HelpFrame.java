
/**
 * David Monahan 25/11/2016
 * Software Engineering GUI Assignment
 * This uses some code repurposed from the AddressBook assignment in Client Server program to create a Desktop Gui 
 * with each frame used to represent a simple game using a grid of buttons
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HelpFrame extends JInternalFrame implements ActionListener {
	private static final String HELP_PATH = "src/txt/help.txt";
	private JPanel mainPanel;

	// static integers used to determine new window positions
	// for cascading windows
	private static int xOffset = 0, yOffset = 0;

	public HelpFrame() {
		super("Help", true, true);
		// Set the rows and columns for GridLayout
		int rowCount = 1;
		int colCount = 1;

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(rowCount, colCount, 5, 5));
		JTextArea display = new JTextArea();
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(HELP_PATH));
			String line = "";
			while((line = br.readLine()) != null){
				display.append(line + "\n");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mainPanel.add(display);
		
		Container container = getContentPane();
		container.add(mainPanel, BorderLayout.WEST);
		

		setBounds(xOffset, yOffset, 470, 540);
		xOffset = (xOffset + 30) % 470;
		yOffset = (yOffset + 30) % 540;
	}
	@Override
	/**
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {

	}

}
