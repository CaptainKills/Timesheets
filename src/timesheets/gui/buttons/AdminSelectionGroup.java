package timesheets.gui.buttons;

import javax.swing.ButtonGroup;

import timesheets.gui.lists.ButtonList;
import timesheets.logging.Logger;

public class AdminSelectionGroup extends ButtonGroup {
	private static final long serialVersionUID = -7701029009510558498L;
	private static final Logger logger = new Logger(AdminSelectionGroup.class);

	public AdminSelectionGroup() {
		add(ButtonList.adminEnableButton);
		add(ButtonList.adminDisableButton);

		logger.debug("AdminSelectionGroup initialised.");
	}

}
