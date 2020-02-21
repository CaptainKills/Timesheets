package timesheets.gui;

import timesheets.gui.lists.*;

public class ExtendedHandler {
	
	public static void enableShiftButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
		ButtonList.startShiftButton.setEnabled(start);
		ButtonList.endShiftButton.setEnabled(end);
		ButtonList.startBreakButton.setEnabled(startbreak);
		ButtonList.endBreakButton.setEnabled(endbreak);
	}

}
