package timesheets.gui.buttons;

import javax.swing.ButtonGroup;

import timesheets.gui.lists.ButtonList;

public class AdminSelectionGroup extends ButtonGroup{
	private static final long serialVersionUID = -7701029009510558498L;

	public AdminSelectionGroup() {
		add(ButtonList.adminEnableButton);
		add(ButtonList.adminDisableButton);
	}

}
