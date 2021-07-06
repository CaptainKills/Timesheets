package timesheets.exceptions;

import javax.swing.JOptionPane;

import timesheets.gui.optionpanes.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class CrashExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Logger logger = new Logger(CrashExceptionHandler.class);

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("AN UNEXPECTED ERROR OCCURED!", e);
		String msgErrorTitle = LanguageManager.language.get("jop_ceh_title");
		
		CustomOptionPane cop = new CustomOptionPane("CrashExceptionHandler");
		cop.setText(msgErrorTitle, e.getMessage());
		cop.setConfig(JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		cop.showDialog();
	}

}
