package timesheets.gui;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.PanelList;

public class ExtendedHandler {

	public static void enableShiftButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
		ButtonList.startShiftButton.setEnabled(start);
		ButtonList.endShiftButton.setEnabled(end);
		ButtonList.startBreakButton.setEnabled(startbreak);
		ButtonList.endBreakButton.setEnabled(endbreak);
	}

	public static void displayPanels(boolean numpad, boolean menu, boolean admin, boolean edit, boolean timesheet, boolean settings) {
		PanelList.numpadPanel.setVisible(numpad);
		PanelList.menuPanel.setVisible(menu);
		PanelList.adminPanel.setVisible(admin);
		PanelList.editPanel.setVisible(edit);
		PanelList.reportPanel.setVisible(timesheet);
		PanelList.settingsPanel.setVisible(settings);
	}

}
