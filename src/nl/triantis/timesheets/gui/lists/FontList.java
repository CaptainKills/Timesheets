package nl.triantis.timesheets.gui.lists;

import java.awt.Font;

import nl.triantis.timesheets.Settings;

public class FontList {
	private static int fontSize = Integer.parseInt(Settings.settings.get("fontsize"));

	public static Font textfieldFont = new Font("Arial", Font.PLAIN, 35 + fontSize);
	public static Font buttonFont = new Font("Arial", Font.PLAIN, 10 + fontSize);
	public static Font infolabelFont = new Font("Arial", Font.PLAIN, 10 + fontSize);
	public static Font normalFont = new Font("Arial", Font.PLAIN, 6 + fontSize);
	public static Font DisplayFont = new Font("Arial", Font.PLAIN, 5 + fontSize);
	public static Font labelFont = new Font("Arial", Font.PLAIN, 10 + fontSize);
	public static Font textDisplayFont = new Font("Arial", Font.PLAIN, 8 + fontSize);

}
