package timesheets.runnable;

import java.awt.Color;

import javax.swing.UIManager;

import timesheets.gui.MainFrame;
import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;

public class Timesheets implements Runnable {
	
	private static final Logger logger = new Logger(Timesheets.class);

	@Override
	public void run() {
		logger.info("Initialising Mainframe.");
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.getLookAndFeelDefaults().put("Panel.background", Color.WHITE);
			logger.info("Set Look And Feel.");
		} catch(Exception e) {
			logger.error("COULD NOT SET LOOK AND FEEL!", e);
		}
		
		MainFrame frame = new MainFrame();

		logger.info("Setting Frame Parameters.");
		frame.setResizable(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setIconImages(ResourceHandler.getIcons());
		frame.setVisible(true);
		logger.info("Initialisation of SwingUtilities Complete.");
	}

}
