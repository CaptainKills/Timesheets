package nl.triantis.timesheets;

import javax.swing.SwingUtilities;

import nl.triantis.timesheets.exceptions.CrashExceptionHandler;
import nl.triantis.timesheets.logging.LogManager;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.resources.ResourceHandler;
import nl.triantis.timesheets.runnable.Editor;
import nl.triantis.timesheets.runnable.SelectedRunnable;
import nl.triantis.timesheets.runnable.Timesheets;
import nl.triantis.timesheets.sql.Database;
import nl.triantis.timesheets.update.Update;

public class Main {
	private static final Logger logger = new Logger(Main.class);
	private static Database database = new Database();
	
	private static SelectedRunnable runnable = SelectedRunnable.TIMESHEETS;

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler());
		
		setupCommandLineParameters(args);
		ResourceHandler.generateFileStructure();
		
		LogManager.openLog();
		Settings.load();
		logger.info("Initialising Program.");
		
		LanguageManager.initialise();
		
		LogManager.archiveLogs();
		LogManager.cleanDirectory();
		
		database.cleanDirectory();
		database.setup();
		database.load();

		logger.info("Opening Runnable: " + runnable);
		switch(runnable) {
		case TIMESHEETS:
			SwingUtilities.invokeLater(new Timesheets());
			break;
		case EDITOR:
			new Thread(new Editor()).start();
			break;
		default:
			SwingUtilities.invokeLater(new Timesheets());
			break;	
		}

		new Thread(new Runnable() {
			public void run() {
				Update.checkForUpdates(false);
			}
		}).start();
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
				System.out.println("\nTimesheets Version Full Release: " + Update.getCurrentVersion());
				System.out.println("For updates, visit https:/github.com/CaptainKills/Timesheets/releases\n");
				System.exit(0);
			} else if (arg.equals("-debug") || arg.equals("-d")) {
				LogManager.setDebugMode(true);
			} else if(arg.equals("-edit") || arg.equals("-e")){ 
				runnable = SelectedRunnable.EDITOR;
			} else {
				System.out.println("Unknown Argument: " + arg + ".");
				System.out.println("For help, type: java -jar Timesheets.jar --help");
				System.exit(0);
			}
		}
	}
}