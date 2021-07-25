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
			+ "language=en\n" 
			+ "exit_on_close=true\n"
			+ "round_off_time=true\n"
			+ "";
	// @formatter:on

	public static void load() {
		logger.info("Loading Settings File.");
		try (BufferedReader reader = new BufferedReader(new FileReader(settings_file))) {
			String newline;
			
			while ((newline = reader.readLine()) != null) {
				String[] segments = newline.split("=");
				String setting = segments[0];
				String value = segments[1];

				settings.put(setting, value);
				logger.debug("Added Setting: " + setting + ", Value: " + value);
			}
		} catch (IOException e) {
			logger.error("COULD NOT LOAD SETTINGS!", e);
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
	
	public static void writeDefaultsToFile(File file) throws IOException {
		PrintWriter writer = new PrintWriter(file);
		writer.print(defaultSettings);
		writer.close();
	}
}
