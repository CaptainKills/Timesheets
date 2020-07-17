package timesheets;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import timesheets.gui.MainFrame;
import timesheets.logging.LogManager;
import timesheets.logging.Logger;

public class Main {
	private static final Logger logger = new Logger(Main.class.toString());
	
	public static void main(String[] args) {
		LogManager.initialise();
		
		logger.debug("Initialise SwingUtilities.");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.debug("Initialise Mainframe.");
				MainFrame frame = new MainFrame();
				
				logger.debug("Set frame parameters.");
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setSize(frame.frameWidth, frame.frameHeight);
				frame.setResizable(true);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				logger.debug("Initialisation of SwingUtilities Complete.");
			}
		});
	}
}