package timesheets;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import timesheets.gui.MainFrame;
import timesheets.logging.LogManager;
import timesheets.logging.Logger;

public class Main {
	private static final Logger logger = new Logger(Main.class.toString());
	
	private static final String resource_path = "timesheets/images/";
	private static ClassLoader loader = Main.class.getClassLoader();
	
	private static final ImageIcon img16 = new ImageIcon(loader.getResource(resource_path + "atom16.png"));
	private static final ImageIcon img32 = new ImageIcon(loader.getResource(resource_path + "atom32.png"));
	private static final ImageIcon img128 = new ImageIcon(loader.getResource(resource_path + "atom128.png"));
	private static final ImageIcon img256 = new ImageIcon(loader.getResource(resource_path + "atom256.png"));
	private static final ImageIcon img512 = new ImageIcon(loader.getResource(resource_path + "atom512.png"));
	
	public static void main(String[] args) {
		LogManager.initialise();
		logger.info("Initialising Program.");
		
		Settings.loadSettings();
		LogManager.checkLogs();
		
		logger.info("Initialising SwingUtilities.");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				logger.info("Initialising Mainframe.");
				MainFrame frame = new MainFrame();
				
				logger.info("Setting Frame Parameters.");
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setResizable(true);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setIconImages(getIcons());
				logger.info("Initialisation of SwingUtilities Complete.");
			}
		});
	}
	
	private static LinkedList<Image> getIcons(){
		LinkedList<Image> list = new LinkedList<Image>();
		list.add(img16.getImage());
		list.add(img32.getImage());
		list.add(img128.getImage());
		list.add(img256.getImage());
		list.add(img512.getImage());
		
		return list;
	}
}