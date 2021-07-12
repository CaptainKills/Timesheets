package timesheets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;

public class Settings {
	private static final Logger logger = new Logger(Settings.class);

	private static File settings_file = ResourceHandler.settings_path.toFile();
	public static Map<String, String> settings = new LinkedHashMap<String, String>();

	// @formatter:off
		private final static String defaultSettings = "" 
			+ "fontsize=10\n" 
			+ "width=1000\n" 
			+ "height=560\n" 
			+ "delete_logs=false\n"
			+ "number_of_logs=20\n"
			+ "number_of_backups=5\n" 
			+ "language=en" 
			+ ""
			+ "";
	// @formatter:on

	public static void load() {
		logger.info("Loading Settings File.");
		checkFile();

		try (BufferedReader reader = new BufferedReader(new FileReader(settings_file))) {
			String newline;
			
			while ((newline = reader.readLine()) != null) {
				String[] segments = newline.split("=");
				String setting = segments[0];
				String value = segments[1];

				settings.put(setting, value);
				logger.debug("Added Setting " + setting + ", Value: " + value);
			}
		} catch (IOException e) {
			logger.error("COULD NOT LOAD SETTINGS!", e);
		}
	}

	private static void checkFile() {
		if (!settings_file.exists()) {
			logger.info("Settings file does not exist.");
			createFile(settings_file);
		} else if (settings_file.length() == 0) {
			logger.debug("Settings File is empty: Length=" + settings_file.length());
			writeDefaultsToFile(settings_file);
		} else {
			logger.debug("Settings file exists: Length=" + settings_file.length());
		}
	}

	private static void createFile(File file) {
		try {
			if (!file.getParentFile().exists()) {
				logger.info("Parent Directory of files does not exist.");
				file.getParentFile().mkdirs();
				logger.debug("Parent Directory of files created.");
			}
		} catch (Exception e) {
			logger.error("COULD NOT CREATE PARENT DIRECTORY!", e);
		}

		try (PrintWriter writer = new PrintWriter(file)) {
			file.createNewFile();
			logger.debug("New File created at " + file.toString());
			writeDefaultsToFile(file);
			logger.debug("File creation succesfully completed.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE FILE!", e);
		}
	}

	private static void writeDefaultsToFile(File file) {
		try (PrintWriter writer = new PrintWriter(file)) {
			logger.debug("Writing default settings to Settings file.");
			writer.print(defaultSettings);
		} catch (IOException e) {
			logger.error("COULD NOT WRITE DEFAULTS TO FILE!", e);
		}
	}

	public static void save() {
		logger.info("Saving settings to file.");
		try (PrintWriter writer = new PrintWriter(settings_file)) {
			for (Map.Entry<String, String> entry : settings.entrySet()) {
				writer.println(entry.getKey() + "=" + entry.getValue());
			}
		} catch (IOException e) {
			logger.error("COULD NOT SAVE SETTINGS TO FILE!", e);
		}
	}
}
