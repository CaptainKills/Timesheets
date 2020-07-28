package timesheets.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogManager {
	private static LocalTime initial_time = LocalTime.now();
	private static LocalDate initial_date = LocalDate.now();

	private static String filename = "Timesheets Log " + initial_date + ".txt";
	private static String zipname = "Timesheets Log.zip";
	private static String directoryName = "logs";

	
	private static final Path directory_path = Paths.get(directoryName).toAbsolutePath();
	private static final Path log_path = Paths.get(directoryName + File.separator + filename).toAbsolutePath();
	private static File directory_file = directory_path.toFile();
	private static File log_file = log_path.toFile();
	

	private static boolean debugMode = false;

	public static void initialise() {
		try {
			if (!log_file.getParentFile().exists()) {
				log_file.getParentFile().mkdirs();
			}
			
			archiveLogs();
			log_file.createNewFile();
		} catch (IOException e) {
			System.err.println("The file could not be created: " + e);
			e.printStackTrace();
		}

		writeLog("---Timesheets Log created [" + initial_date + " " + initial_time + "]---\n");
	}

	private static void archiveLogs() {
		try (FileOutputStream fos = new FileOutputStream(directoryName + File.separator + zipname, true); ZipOutputStream zos = new ZipOutputStream(fos);) {

			String[] log_files = directory_file.list();
			for (String log : log_files) {
				if(log == log_files[log_files.length-1]) {
					return;
				}
				
				Path p = Paths.get(directoryName + File.separator + log);
				File f = p.toFile();				
				zos.putNextEntry(new ZipEntry(f.getName()));

				byte[] bytes = Files.readAllBytes(p);
				zos.write(bytes, 0, bytes.length);
				zos.flush();
				zos.closeEntry();
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find file Error: " + e);
			e.printStackTrace();
        } catch (IOException e) {
        	System.err.println("I/O Error: " + e);
        	e.printStackTrace();
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
