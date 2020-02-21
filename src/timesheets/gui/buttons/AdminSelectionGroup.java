package timesheets.gui.buttons;

import javax.swing.ButtonGroup;

import timesheets.gui.lists.ButtonList;

public class AdminSelectionGroup extends ButtonGroup{

	public AdminSelectionGroup() {
		add(ButtonList.adminEnableButton);
		add(ButtonList.adminDisableButton);
	}

}
