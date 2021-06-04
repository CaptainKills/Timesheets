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
import timesheets.exceptions.CrashExceptionHandler;
import timesheets.resources.ResourceHandler;

public class LogManager {
	private static final Logger logger = new Logger(LogManager.class);

	private static LocalTime initial_time = LocalTime.now();
	private static LocalDate initial_date = LocalDate.now();

	private static String activeLog = "Timesheets Log " + initial_date + ".txt";

	private static final Path log_directory = ResourceHandler.log_directory_path;
	private static final Path log_path = Paths.get(log_directory + File.separator + activeLog).toAbsolutePath();
	private static final File log_file = log_path.toFile();

	private static boolean debugMode = false;
	private static String[] directory_files = log_directory.toFile().list();

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
		logger.info("LogManager Initialised.");
		archiveLogs();

		Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler());
		logger.info("DefaultUncaughtExceptionHandler set: CrashExceptionHandler");
	}

	public static void writeLog(String log) {
		try (FileWriter file_writer = new FileWriter(log_file, true);
				PrintWriter log_writer = new PrintWriter(file_writer)) {
			log_writer.println(log);

			if (debugMode) {
				System.out.println(log);
			}
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO LOG FILE!", e);
		}
	}

	private static void archiveLogs() {
		logger.info("Archiving Log Files.");
		if (directory_files != null) {
			for (String log : directory_files) {
				if (log.contains(".zip") || log.equals(activeLog)) {
					continue;
				} else {
					logger.info("Archiving " + log);
					createZip(log);
					File f = new File(log_directory + File.separator + log);
					f.delete();
				}
			}
		} else {
			logger.info("There are currently no log files present. Archiving will be skipped.");
		}
	}

	private static void createZip(String fileName) {
		String zipName = fileName.substring(0, fileName.length() - 4).concat(".zip");

		try (FileOutputStream fos = new FileOutputStream(log_directory + File.separator + zipName, true);
				ZipOutputStream zos = new ZipOutputStream(fos);) {

			Path p = Paths.get(log_directory + File.separator + fileName).toAbsolutePath();
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
			logger.error("COULD NOT ARCHIVE FILE!", e);
		}
	}

	public static void cleanDirectory() {
		boolean delete_logs = Boolean.parseBoolean(Settings.settings.get("delete_logs"));
		int number_of_logs = Integer.parseInt(Settings.settings.get("number_of_logs"));

		if (directory_files != null) {
			logger.info("Log files in Directory: " + directory_files.length + ", # of Files allowed: " + number_of_logs);

			if (delete_logs == true) {
				int difference = directory_files.length - number_of_logs;
				logger.info("Log deletion is enabeled. There are currently " + difference + "  logs too many.");

				for (int i = 0; i < difference; i++) {
					logger.info("Removing " + directory_files[i]);
					File f = new File(log_directory + File.separator + directory_files[i]);
					f.delete();
				}
				logger.info("Log Directory Clean finished.");
			} else {
				logger.info("Log deletion is disabled. No logs will be deleted.");
				return;
			}
		} else {
			logger.info("Log files in Directory: 0, directory is empty.");
		}
	}

	public static void setDebugMode(boolean mode) {
		debugMode = mode;
	}
}
