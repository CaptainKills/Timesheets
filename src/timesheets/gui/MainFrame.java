package timesheets.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import timesheets.Settings;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.UnusualsList;
import timesheets.gui.panels.MainPanel;
import timesheets.logging.Logger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 01L;
	private static final Logger logger = new Logger(MainFrame.class);

	private MainPanel mainPanel = PanelList.mainPanel;

	public MainFrame() {
		super("Timesheets");
		logger.info("Start MainFrame Setup.");

		mainPanel.setupPanel();
		getContentPane().add(mainPanel);
		UnusualsList.updateTimer.start();
		UnusualsList.backupTimer.start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				boolean exitOnClose = Boolean.parseBoolean(Settings.settings.get("exit_on_close"));
				
				if(exitOnClose) {
					ButtonList.exitButton.exitApplication();
				}
			}
		});

		logger.info("MainFrame Setup Complete.");
	}

}