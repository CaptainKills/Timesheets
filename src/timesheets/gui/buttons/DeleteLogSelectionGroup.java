package timesheets.gui.buttons;

import javax.swing.ButtonGroup;

import timesheets.gui.lists.ButtonList;
import timesheets.logging.Logger;

public class DeleteLogSelectionGroup extends ButtonGroup {
	private static final long serialVersionUID = 1511311307594019717L;
	private static final Logger logger = new Logger(DeleteLogSelectionGroup.class);

	public DeleteLogSelectionGroup() {
		add(ButtonList.deleteLogEnabledButton);
		add(ButtonList.deleteLogDisabledButton);

		logger.debug("DeleteLogSelectionGroup initialised.");
	}

}
