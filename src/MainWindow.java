
/**
 * David Monahan 25/11/2016
 * Software Engineering GUI Assignment
 * This uses some code repurposed from the AddressBook assignment in Client Server program to create a Desktop Gui 
 * with each frame used to represent a simple game using a grid of buttons
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;


public class MainWindow extends JFrame {
	// reference for manipulating multiple document interface
	private JDesktopPane desktop;

	private TreeMap<Integer, String> highScores;
	Action newGameAction, exitAction, highScoreAction;
	
	public MainWindow() {
		super("Lights Out Game");
		

		/**
		 * Code added to change the look and feel of the application. Nimbus is
		 * the selected GUI. Find out more here:
		 * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
		 */
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		
		// Create GUI
		JToolBar toolBar = new JToolBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		
		newGameAction = new NewGameAction();
		highScoreAction = new HighScoresAction();
		exitAction = new ExitAction();
		
		toolBar.add(newGameAction);
		toolBar.add(highScoreAction);
		
		fileMenu.add(newGameAction);
		fileMenu.add(highScoreAction);
		fileMenu.add(exitAction);
		

		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		// set up desktop
		desktop = new JDesktopPane();
		
		// get the content pane to set up GUI
		Container c = getContentPane();
		c.add(toolBar, BorderLayout.NORTH);
		c.add(desktop, BorderLayout.CENTER);
		
		// register for windowClosing event in case user
		// does not select Exit from File menu to terminate
		// application
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				shutDown();
			}
		});
		
		// set window size and display window
		Toolkit toolkit = getToolkit();
		Dimension dimension = toolkit.getScreenSize();

		// center window on screen
		setBounds(100, 100, dimension.width - 200, dimension.height - 100);

		setVisible(true);
		
	} // end AddressBook constructor

	// close database connection and terminate program
	private void shutDown() {
		System.exit(0); // terminate program
	}
	

	// method to launch program execution
	public static void main(String args[]) {
		new MainWindow();
	}
	
	private class NewGameAction extends AbstractAction {

		// set up action's name, icon, descriptions and mnemonic
		public NewGameAction() {
			putValue(NAME, "New");
			putValue(SMALL_ICON, new ImageIcon(getClass().getResource("images/New24.png")));
			putValue(SHORT_DESCRIPTION, "New");
			putValue(LONG_DESCRIPTION, "Add a new address book entry");
			putValue(MNEMONIC_KEY, new Integer('N'));
		}

		// display window in which user can input entry
		public void actionPerformed(ActionEvent e) {
			// create new internal window
			
			GameFrame frame = new GameFrame();
			desktop.add(frame);
			frame.setVisible(true);
		}

	} // end inner class NewAction
	
	private class HighScoresAction extends AbstractAction {

		// set up action's name, icon, descriptions and mnemonic
		public HighScoresAction() {
			putValue(NAME, "High Scores");
			putValue(SMALL_ICON, new ImageIcon(getClass().getResource("images/New24.png")));
			putValue(SHORT_DESCRIPTION, "High Scores");
			putValue(LONG_DESCRIPTION, "Show all the High Scores");
			putValue(MNEMONIC_KEY, new Integer('H'));
		}

		// display window in which user can input entry
		public void actionPerformed(ActionEvent e) {
			// create new internal window
			highScores = new TreeMap<Integer, String>();
			for(int i=20; i >0; i--) {
				highScores.put(i,"THIS"+i+"NAME");
			}
			InfoFrame frame = new InfoFrame("High Scores", highScores);
			desktop.add(frame);
			frame.setVisible(true);
		}

	} // end inner class NewAction
	
	// inner class defines action that closes connection to
	// database and terminates program
	private class ExitAction extends AbstractAction {

		// set up action's name, descriptions and mnemonic
		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit");
			putValue(LONG_DESCRIPTION, "Terminate the program");
			putValue(MNEMONIC_KEY, new Integer('x'));
		}

		// terminate program
		public void actionPerformed(ActionEvent e) {
			shutDown(); // close database connection and terminate
		}

	} // end inner class ExitAction
}
