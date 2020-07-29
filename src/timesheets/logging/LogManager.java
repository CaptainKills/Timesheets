package timesheets.logging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import timesheets.Settings;

public class LogManager {
	private static final Logger logger = new Logger(LogManager.class.toString());

	private static LocalTime initial_time = LocalTime.now();
	private static LocalDate initial_date = LocalDate.now();

	private static String filename = "Timesheets Log " + initial_date + ".txt";
	private static String zipname = "Timesheets Log.zip";
	private static String directoryName = "logs";

	private static final Path directory_path = Paths.get(directoryName).toAbsolutePath();
	private static final Path log_path = Paths.get(directoryName + File.separator + filename).toAbsolutePath();
	private static File directory_file = directory_path.toFile();
	private static File log_file = log_path.toFile();

	private static boolean debugMode = true;

	public static void initialise() {
		try {
			if (!log_file.getParentFile().exists()) {
				log_file.getParentFile().mkdirs();
			}

			log_file.createNewFile();
		} catch (IOException e) {
			System.err.println("The file could not be created: " + e);
			e.printStackTrace();
		}

		writeLog("---Timesheets Log created [" + initial_date + " " + initial_time + "]---\n");
	}

	public static void checkLogs() {
		int amount_of_logs = Integer.parseInt(Settings.settings.get("amount_of_logs"));
		boolean delete_logs = Boolean.parseBoolean(Settings.settings.get("delete_logs"));

		String[] log_files = directory_file.list();

		if (log_files.length > amount_of_logs) {
			logger.info(
					"Log files reached maximum amount: allowed=" + amount_of_logs + ", present=" + log_files.length);

			int difference = log_files.length - amount_of_logs;
			for (int i = 0; i < difference; i++) {

				if (delete_logs == true) {
					logger.info("Removing " + log_files[i]);
					File f = new File(directoryName + File.separator + log_files[i]);
					f.delete();
				} else {
					logger.info("Archiving " + log_files[i]);
					//archiveLog(log_files[i]);
					// File f = new File(directoryName + File.separator + log_files[i]);
					// f.delete();
				}
			}
		}
	}

	private static void archiveLog(String fileName) {
		try (FileOutputStream fos = new FileOutputStream(directoryName + File.separator + zipname, true);
				ZipOutputStream zos = new ZipOutputStream(fos);) {

			Path p = Paths.get(directoryName + File.separator + fileName).toAbsolutePath();
			zos.putNextEntry(new ZipEntry(p.toFile().getName()));

			FileInputStream fis = new FileInputStream(p.toFile());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
			
			zos.closeEntry();
			fis.close();
		} catch (IOException e) {
			logger.error("COULD NOT ARCHIVE FILE: " + e);
		}
	}

	public static void writeLog(String log) {
		try (FileWriter file_writer = new FileWriter(log_file, true);
				PrintWriter log_writer = new PrintWriter(file_writer)) {
			log_writer.println(log);

			if (debugMode) {
				System.out.println(log);
			}
		} catch (IOException e) {
			System.err.println("Could not write to log file: " + e);
			e.printStackTrace();
		}
	}

}
