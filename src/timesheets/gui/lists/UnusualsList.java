package timesheets.gui.lists;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import timesheets.gui.combobox.EmployeeBox;

public class UnusualsList {

	public static JComboBox<String> empBox = new EmployeeBox<String>();
	public static JScrollPane scrollPane = new JScrollPane(TextAreaList.timesheetDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

}