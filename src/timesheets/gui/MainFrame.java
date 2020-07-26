package timesheets.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import timesheets.Settings;
import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 01L;
	private static final Logger logger = new Logger(MainFrame.class.toString());
	
	private Database database = new Database();
	private Settings settings = new Settings();

	public MainFrame() {
		super("Timesheets");
		logger.info("Start MainFrame Setup.");

		settings.loadSettings();
		database.setupDatabase();
		database.loadDatabase();

		PanelList.mainPanel.setupPanel();
		getContentPane().add(PanelList.mainPanel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure?") == JOptionPane.YES_OPTION) {
					logger.info("Closing Application...");
					database.backupDatabase();
					logger.info("Application Closed.\n");
					System.exit(0);
				}
			}
		});
		
		logger.info("MainFrame Setup Complete.");
	}
}