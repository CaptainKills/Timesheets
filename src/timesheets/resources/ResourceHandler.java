package timesheets.resources;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import timesheets.logging.Logger;

public class ResourceHandler {
	private static final Logger logger = new Logger(ResourceHandler.class.toString());

	private static final String images_path = "timesheets/resources/images/";
	private static ClassLoader loader = ResourceHandler.class.getClassLoader();
	
	private static final ImageIcon img16 = new ImageIcon(loader.getResource(images_path + "atom16.png"));
	private static final ImageIcon img32 = new ImageIcon(loader.getResource(images_path + "atom32.png"));
	private static final ImageIcon img128 = new ImageIcon(loader.getResource(images_path + "atom128.png"));
	private static final ImageIcon img256 = new ImageIcon(loader.getResource(images_path + "atom256.png"));
	private static final ImageIcon img512 = new ImageIcon(loader.getResource(images_path + "atom512.png"));
	
	public static LinkedList<Image> getIcons(){
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
