package timesheets.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import timesheets.DataHandler;
import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 01L;
	private static final Logger logger = new Logger(MainFrame.class.toString());
	
	private DataHandler data = new DataHandler();

	public MainFrame() {
		super("Timesheets");
		logger.info("Start MainFrame Setup.");
		
		data.loadDataFromFiles();

		PanelList.mainPanel.setupPanel();
		getContentPane().add(PanelList.mainPanel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure?") == JOptionPane.YES_OPTION) {
					logger.info("Closing Application...");
					data.saveDataToFiles();
					logger.info("Application Closed.\n");
					System.exit(0);
				}
			}
		});
		
		logger.info("MainFrame Setup Complete.");
	}
}