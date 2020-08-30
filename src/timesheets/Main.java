package timesheets;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import timesheets.gui.MainFrame;
import timesheets.logging.LogManager;
import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;
import timesheets.sql.Database;

public class Main {
	private static final Logger logger = new Logger(Main.class);

	public static void main(String[] args) {
		setupCommandLineParameters(args);
		LogManager.initialise();
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

	private static void setupCommandLineParameters(String[] args) {
		for (String arg : args) {
			if (arg.equals("--help") || arg.equals("--h")) {
				System.out.println("usage: java -jar Timesheets.jar [<options>]...\n");
				System.out.println("\t--version\tPrints program version");
				System.out.println("\t-debug, -d\tEnables debugging mode (prints logs to command line, etc)");
				System.out.println("\n\n");

				System.out.println("\tFor more info, visit https:/github.com/CaptainKills/Timesheets");
				System.exit(0);
			} else if (arg.equals("--version")) {
				System.out.println("\nTimesheets Version: Beta v0.5");
				System.out.println("For updates, visit https:/github.com/CaptainKills/Timesheets/releases\n");
				System.exit(0);
			} else if (arg.equals("-debug") || arg.equals("-d")) {
				LogManager.setDebugMode(true);
			} else {
				System.out.println("Unknown Argument: " + arg + ".");
				System.out.println("For help, type: java -jar Timesheets.jar --help");
				System.exit(0);
			}
		}
	}
}