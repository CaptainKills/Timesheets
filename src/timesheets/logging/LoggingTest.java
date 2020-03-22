package timesheets.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {
	private static final Logger logger = LogManager.getLogger(LoggingTest.class);

	public static void testLog() {
		System.out.println("Hello Logging World!");
		
		logger.info("This is a information message.");
		logger.error("This is an error message!");
		logger.warn("This is a warning message!");
		logger.fatal("This is a fatal message! Program will terminate!");
		logger.debug("This is a debug message.");
		
		System.out.println("Test Completed!");
	}

}
