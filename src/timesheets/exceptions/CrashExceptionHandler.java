package timesheets.exceptions;

import javax.swing.JOptionPane;

import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Logger logger = new Logger(CrashExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("AN UNEXPECTED ERROR OCCURED!", e);
		
		JOptionPane.showMessageDialog(PanelList.mainPanel, e.getMessage(),
				"An Error Occured!", JOptionPane.ERROR_MESSAGE);
	}

}
