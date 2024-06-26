package nl.triantis.timesheets.gui.buttons.groups;

import javax.swing.ButtonGroup;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.logging.Logger;

public class DateSelectionGroup extends ButtonGroup {

	private static final long serialVersionUID = 7406226196170860238L;
	private static final Logger logger = new Logger(DateSelectionGroup.class);

	public DateSelectionGroup() {
		add(ButtonList.dateTodayButton);
		add(ButtonList.dateWeekButton);
		add(ButtonList.dateMonthButton);
		add(ButtonList.dateSpecificButton);

		logger.debug("DateSelectionGroup initialised.");
	}

}
