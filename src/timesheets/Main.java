package timesheets;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import timesheets.exceptions.CrashExceptionHandler;
import timesheets.gui.MainFrame;
import timesheets.logging.LogManager;
import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;
import timesheets.sql.Database;

public class Main {
	private static final Logger logger = new Logger(Main.class);

	public static void main(String[] args) {
		LogManager.initialise();
		Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler());
		logger.info("Initialising Program.");

		Settings.loadSettings();
		LogManager.cleanDirectory();
		Database.cleanDirectory();

		logger.info("Initialising SwingUtilities.");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.info("Initialising Mainframe.");
				MainFrame frame = new MainFrame();

				logger.info("Setting Frame Parameters.");
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setResizable(true);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setIconImages(ResourceHandler.getIcons());
				logger.info("Initialisation of SwingUtilities Complete.");
			}
		});
	}
}