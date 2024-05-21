package nl.triantis.timesheets.logging;

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

import nl.triantis.timesheets.Settings;
import nl.triantis.timesheets.resources.ResourceHandler;

public class LogManager {
	
	private static final Logger logger = new Logger(LogManager.class);
	private static String logName = "Timesheets Log " + LocalDate.now() + ".txt";

	private static final Path log_directory = ResourceHandler.log_directory_path;
	private static final Path log_path = Paths.get(log_directory + File.separator + logName).toAbsolutePath();
	private static final File log_file = log_path.toFile();

	private static boolean debugMode = false;

	public static void openLog() {
		try {
			log_file.createNewFile();
			writeLog("---Timesheets Log created [" + LocalDate.now() + " " + LocalTime.now() + "]---\n");
			logger.info("Log opened.");
		} catch (IOException e) {
			System.err.println("LOG FILE COULD NOT BE CREATED!" + e);
			e.printStackTrace();
		}
	}

	public static void writeLog(String log) {
		try (FileWriter fw = new FileWriter(log_file, true);
				PrintWriter pw = new PrintWriter(fw)) {
			pw.println(log);

			if (debugMode) {
				System.out.println(log);
			}
		} catch (IOException e) {
			System.err.println("COULD NOT WRITE TO LOG FILE!" + e);
			e.printStackTrace();
		}
	}

	public static void archiveLogs() {
		logger.info("Archiving Log Files.");
		String[] directoryFiles = log_directory.toFile().list();
		
		for (String log : directoryFiles) {
			if (log.contains(".zip") || log.equals(logName)) {
				continue;
			} else {
				logger.info("Archiving " + log);
				createZip(log);
				File f = new File(log_directory + File.separator + log);
				f.delete();
			}
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
		String[] directoryFiles = log_directory.toFile().list();

		logger.info("Log files in Directory: " + directoryFiles.length + ", # of Files allowed: " + number_of_logs);

		if (delete_logs == true && number_of_logs > 1) {
			int difference = directoryFiles.length - number_of_logs;
			logger.info("Log deletion is enabeled. There are currently " + difference + "  logs too many.");

			for (int i = 0; i < difference; i++) {
				logger.info("Removing " + directoryFiles[i]);
				File f = new File(log_directory + File.separator + directoryFiles[i]);
				f.delete();
			}
			
			logger.info("Log Directory Clean finished.");
		} else {
			logger.info("No logs will be deleted.");
		}
	}

	public static void setDebugMode(boolean mode) {
		debugMode = mode;
	}
}
