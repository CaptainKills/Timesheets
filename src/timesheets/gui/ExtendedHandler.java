package timesheets.gui;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;

public class ExtendedHandler {

	// private static final Logger logger = new
	// Logger(ExtendedHandler.class.toString());

	public static void enableShiftButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
		ButtonList.startShiftButton.setEnabled(start);
		ButtonList.endShiftButton.setEnabled(end);
		ButtonList.startBreakButton.setEnabled(startbreak);
		ButtonList.endBreakButton.setEnabled(endbreak);

		// logger.debug("Enabled Shift Buttons: Start-" + start + ", End-" + end + ",
		// Start Break-" + startbreak
		// + ", End Break-" + endbreak + ".");
	}

	public static void displayTimeButtons(boolean bool) {
		TextAreaList.loginTextArea.setVisible(bool);
		ButtonList.startShiftButton.setVisible(bool);
		ButtonList.endShiftButton.setVisible(bool);
		ButtonList.startBreakButton.setVisible(bool);
		ButtonList.endBreakButton.setVisible(bool);

		// logger.debug("Displayed Time Buttons: " + bool + ".");
	}

	public static void displayAdminButtons(boolean bool) {
		ButtonList.addEmployeeButton.setVisible(bool);
		ButtonList.removeEmployeeButton.setVisible(bool);
		ButtonList.editEmployeeButton.setVisible(bool);
		ButtonList.printSheetsButton.setVisible(bool);
		ButtonList.editSheetsButton.setVisible(bool);
		ButtonList.settingsButton.setVisible(bool);
		ButtonList.exitButton.setVisible(bool);

		// logger.debug("Displayed Admin Buttons: " + bool + ".");
	}

	public static void displayAdminPanels(boolean numpad, boolean edit, boolean timesheet, boolean settings) {
		PanelList.numpadPanel.setVisible(numpad);
		PanelList.editPanel.setVisible(edit);
		PanelList.timesheetPanel.setVisible(timesheet);
		PanelList.settingsPanel.setVisible(settings);

		// logger.debug("Display Admin Panels: Numpad-" + numpad + ", Edit-" + edit + ",
		// Timesheet-" + timesheet
		// + ", Settings-" + settings + ".");
	}

}
