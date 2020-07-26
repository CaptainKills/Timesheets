package timesheets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import timesheets.logging.Logger;

public class Settings {
	private static final Logger logger = new Logger(Settings.class.toString());

	private final Path path_Settings = Paths.get("data" + File.separator + "Settings.dat").toAbsolutePath();
	private File file_Settings = path_Settings.toFile();

	public static Map<String, String> settings = new LinkedHashMap<String, String>();
	private final String defaultSettings = "" + "fontsize=10\n" + "width=1000\n" + "height=360\n" + "" + "" + "" + ""
			+ "";

	private void checkFile() {
		if (!file_Settings.exists()) {
			logger.info("Settings file does not exist.");
			createFile(file_Settings);
		} else if (file_Settings.length() == 0) {
			logger.info("Settings File is empty: Length=" + file_Settings.length());
			writeDefaultsToFile(file_Settings);
		} else {
			logger.info("Settings file exists.");
		}
	}

	private void createFile(File file) {
		try {
			if (!file.getParentFile().exists()) {
				logger.info("Parent Directory of files does not exist.");
				file.getParentFile().mkdirs();
				logger.info("Parent Directory of files created.");
			}			
		} catch (Exception e) {
			logger.error("COULD NOT CREATE PARENT DIRECTORY: " + e);
		}
		
		try (PrintWriter writer = new PrintWriter(file)) {
			file.createNewFile();
			logger.info("New File created at " + file.toString());
			writeDefaultsToFile(file);
			logger.info("File creation succesfully completed.");
		} catch (IOException e) {
			logger.error("COULD NOT CREATE FILE: " + e);
		}
	}
	
	private void writeDefaultsToFile(File file) {
		try(PrintWriter writer = new PrintWriter(file)){
			logger.debug("Writing default settings to Settings file.");
			writer.print(defaultSettings);
		} catch (IOException e){
			logger.error("COULD NOT WRITE DEFAULTS TO FILE: " + e);
		}
	}

	public void loadSettings() {
		checkFile();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file_Settings))) {
			String newline;
			while ((newline = reader.readLine()) != null) {
				String[] segments = newline.split("=");
				String setting = segments[0];
				String value = segments[1];

				settings.put(setting, value);
				logger.info("Added Setting " + setting + ", Value: " + value);
			}
		} catch (IOException e) {
			logger.error("COULD NOT LOAD SETTINGS: " + e);
		}
	}
}
