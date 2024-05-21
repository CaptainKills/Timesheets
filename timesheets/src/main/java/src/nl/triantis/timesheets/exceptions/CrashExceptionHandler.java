package nl.triantis.timesheets.exceptions;

import javax.swing.JOptionPane;

import nl.triantis.timesheets.gui.other.CustomOptionPane;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

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
