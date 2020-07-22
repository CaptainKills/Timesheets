package timesheets.gui.lists;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import timesheets.gui.combobox.EmployeeBox;
import timesheets.gui.textareas.scrollpanes.ScrollPane;

public class UnusualsList {

	public static JComboBox<String> empBox = new EmployeeBox<String>();
	public static JScrollPane scrollPane = new ScrollPane();

}