
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HighScoreFrame extends JInternalFrame implements ActionListener {
	private static final String IMG_PATH = "src/images/borat.jpg";
	private JPanel leftPanel, rightPanel;

	// static integers used to determine new window positions
	// for cascading windows
	private static int xOffset = 0, yOffset = 0;

	public HighScoreFrame(String name, TreeMap<Integer, String> highScores) {
		super(name, true, true);
		// Set the rows and columns for GridLayout
		int rowCount = 10;
		int colCount = 1;

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(rowCount, colCount, 5, 5));
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(rowCount, colCount, 5, 5));

		// Returns stored scores in ascending order
		Set<Integer> allScores = highScores.keySet();
		int counter = 0;
		for (Integer score : allScores) {
			createRow(highScores.get(score), score);
			counter++;
			if (counter == 10){
				break;
			}
		}
		Container container = getContentPane();
		container.add(leftPanel, BorderLayout.WEST);
		container.add(rightPanel, BorderLayout.EAST);

		setBounds(xOffset, yOffset, 200, 500);
		xOffset = (xOffset + 30) % 500;
		yOffset = (yOffset + 30) % 500;
	}

	private void createRow(String name, Integer score) {
		JLabel playerName = new JLabel(name, SwingConstants.LEFT);
		playerName.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		leftPanel.add(playerName);

		JLabel playerScore = new JLabel(score.toString(), SwingConstants.RIGHT);
		playerScore.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		rightPanel.add(playerScore);

		}
	@Override
	/**
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e) {

	}

}
