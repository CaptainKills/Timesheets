package timesheets.resources;

import java.awt.Image;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import timesheets.logging.Logger;

public class ResourceHandler {
	private static final Logger logger = new Logger(ResourceHandler.class);

	private static ClassLoader loader = ResourceHandler.class.getClassLoader();

	private static String data_directory = "data";
	private static String log_directory = "logs";
	private static String report_directory = "reports";
	
	public static String settings_postfix = ".properties";
	public static String database_postfix = ".db";
	public static String encrypted_postfix = ".encrypted";

	public static final Path settings_path = Paths.get(data_directory + File.separator + "settings" + settings_postfix).toAbsolutePath();
	public static final Path database_path = Paths.get(data_directory + File.separator + "Timesheets" + database_postfix).toAbsolutePath();
	public static final Path encrypted_path = Paths.get(data_directory + File.separator + "Timesheets" + encrypted_postfix).toAbsolutePath();
	public static final Path report_style_path = Paths.get(report_directory + File.separator + "style.css").toAbsolutePath();

	public static final Path data_directory_path = Paths.get(data_directory).toAbsolutePath();
	public static final Path log_directory_path = Paths.get(log_directory).toAbsolutePath();
	public static final Path report_directory_path = Paths.get(report_directory).toAbsolutePath();

	private static final String images_path = "timesheets/resources/images/";
	private static final ImageIcon img16 = new ImageIcon(loader.getResource(images_path + "atom16.png"));
	private static final ImageIcon img32 = new ImageIcon(loader.getResource(images_path + "atom32.png"));
	private static final ImageIcon img128 = new ImageIcon(loader.getResource(images_path + "atom128.png"));
	private static final ImageIcon img256 = new ImageIcon(loader.getResource(images_path + "atom256.png"));
	private static final ImageIcon img512 = new ImageIcon(loader.getResource(images_path + "atom512.png"));

	public static LinkedList<Image> getIcons() {
		LinkedList<Image> list = new LinkedList<Image>();
		list.add(img16.getImage());
		list.add(img32.getImage());
		list.add(img128.getImage());
		list.add(img256.getImage());
		list.add(img512.getImage());

		logger.info("Loaded Program Icons.");
		return list;
	}
}
