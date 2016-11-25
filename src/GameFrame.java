
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

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JInternalFrame implements ActionListener {
	private static final String IMG_PATH = "src/images/borat.jpg";
	private JPanel mainPanel;

	private ArrayList<JButton> buttons;

	// static integers used to determine new window positions
	// for cascading windows
	private static int xOffset = 0, yOffset = 0;

	private int rowCount = 5;
	private int colCount = 5;

	public GameFrame() {
		super("Active Game", true, true);

		buttons = new ArrayList<JButton>();
		Integer[] startingLights = randomNumbers();

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(rowCount, colCount, 5, 5));
		for (int i = 0; i < (rowCount * colCount); i++) {
			if (startingLights.toString().contains(i + "")) {
				createButton(i + "", true);
			} else {
				createButton(i + "", false);
			}

		}

		for (JButton button : buttons) {
			mainPanel.add(button);
		}
		Container container = getContentPane();
		container.add(mainPanel, BorderLayout.CENTER);

		setBounds(xOffset, yOffset, 500, 500);
		xOffset = (xOffset + 30) % 500;
		yOffset = (yOffset + 30) % 500;
	}

	// utility method used by constructor to create one row in
	// GUI containing JLabel and JTextField
	private void createButton(String name, Boolean startColour) {

		JButton button = new JButton();
		button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		button.setActionCommand(name);
		if (startColour) {
			button.setBackground(Color.RED);
		} else {
			button.setBackground(Color.BLACK);
		}
		button.addActionListener(this);
		buttons.add(button);
	}

	@Override
	/**
	 * Action listener designed to respond to all of the buttons when pressed
	 * Synchronised to ensure nothing can happen while the grid is being
	 * updated.
	 */
	public synchronized void actionPerformed(ActionEvent e) {
		int buttonPressed = Integer.parseInt(e.getActionCommand());
		// System.out.println(buttonPressed);
		for (JButton button : buttons) {
			// Toggle the button that was pressed plus the button directly
			// above, below, to the left and to the right of it. Also includes
			// checks to ensure there is no wrap-around at the sides
			if (button.getActionCommand().equals((buttonPressed + ""))
					|| button.getActionCommand().equals((buttonPressed + 5) + "")
					|| button.getActionCommand().equals((buttonPressed - 5) + "")
					|| (button.getActionCommand().equals((buttonPressed + 1) + "") && (buttonPressed + 1) % 5 != 0)
					|| (button.getActionCommand().equals((buttonPressed - 1) + "") && buttonPressed % 5 != 0)) {
				toggle(button);
			}
		}
		if (gameWinner()) {
			System.out.println("You Win!");

			try {
				BufferedImage img = ImageIO.read(new File(IMG_PATH));
				ImageIcon icon = new ImageIcon(img);
				JLabel label = new JLabel(icon);
				JOptionPane.showMessageDialog(mainPanel, label);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Method to toggle the background colour of a JButton
	 * 
	 * @param button
	 *            JButton to be toggled
	 */
	private void toggle(JButton button) {
		if (button.getBackground() == Color.BLACK) {
			button.setBackground(Color.RED);
		} else {
			button.setBackground(Color.BLACK);
		}
	}

	/**
	 * Method to determine if the game has been won, ie. all lights have been
	 * put out
	 * 
	 * @return true if a win false otherwise
	 */
	private Boolean gameWinner() {
		int i = 0;
		for (JButton button : buttons) {
			if (button.getBackground() == Color.BLACK) {
				i++;
			}
		}
		if (i == 25) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to generate unique random numbers between 0 and 24, this will be
	 * used to define the starting pattern of lights for the game
	 * 
	 * @return Integer[] containing 5 unique random numbers between 0 and 24
	 */
	private Integer[] randomNumbers() {
		Set<Integer> s = new HashSet<>();
		while (s.size() != 5) {
			s.add((int) (Math.random() * 24));
		}
		Integer[] array = s.toArray(new Integer[s.size()]);
		return array;
	}

}
