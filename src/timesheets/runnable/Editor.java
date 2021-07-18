package timesheets.runnable;

import timesheets.logging.Logger;

public class Editor implements Runnable {
	
	private static final Logger logger = new Logger(Editor.class);

	@Override
	public void run() {
		logger.info("Initialising Timesheets Editor.");
		System.out.println("Timesheets Editor!");
	}

}
