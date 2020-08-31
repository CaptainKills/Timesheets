package timesheets.gui.lists;

import java.awt.Dimension;

import timesheets.Settings;

public class DimensionList {
	private static int frameWidth = Integer.parseInt(Settings.settings.get("width"));
	private static int frameHeight = Integer.parseInt(Settings.settings.get("height"));

	public static Dimension defaultPanelSize = new Dimension(frameWidth / 2, frameHeight);

	public static Dimension buttonSize_numbers = new Dimension(150, 50);
	public static Dimension buttonSize_menu = new Dimension(228, 50);
	public static Dimension buttonSize_large = new Dimension(460, 50);
	public static Dimension buttonSize_medium = new Dimension(345, 50);
	public static Dimension buttonSize_small = new Dimension(110, 50);

	public static Dimension labelSize = new Dimension(90, 35);

	public static Dimension fieldSize_large = new Dimension(300, 35);
	public static Dimension fieldSize_medium = new Dimension(145, 35);

	public static Dimension dateDisplaySize_large = new Dimension(50, 25);
	public static Dimension dateDisplaySize_medium = new Dimension(20, 25);
	public static Dimension dateDisplaySize_small = new Dimension(44, 25);
	public static Dimension dateSpinnerSize = new Dimension(90, 25);
}
