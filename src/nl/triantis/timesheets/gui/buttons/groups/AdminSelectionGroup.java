package nl.triantis.timesheets.gui.buttons.groups;

import javax.swing.ButtonGroup;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.logging.Logger;

public class AdminSelectionGroup extends ButtonGroup {
	private static final long serialVersionUID = -7701029009510558498L;
	private static final Logger logger = new Logger(AdminSelectionGroup.class);

	public AdminSelectionGroup() {
		add(ButtonList.adminEnableButton);
		add(ButtonList.adminDisableButton);

		logger.debug("AdminSelectionGroup initialised.");
	}

}
