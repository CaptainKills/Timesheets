package timesheets.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 01L;
	private static final Logger logger = new Logger(MainFrame.class.toString());
	
	private Database database = new Database();

	public MainFrame() {
		super("Timesheets");
		logger.info("Start MainFrame Setup.");

		database.setupDatabase();
		database.loadDatabase();

		PanelList.mainPanel.setupPanel();
		getContentPane().add(PanelList.mainPanel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ExtendedHandler.exitApplication(getContentPane());
			}
		});
		
		logger.info("MainFrame Setup Complete.");
	}
}