package timesheets.gui;

import timesheets.DataHandler;
import timesheets.Employee;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;

public class ExtendedHandler {
	
	public static void enableShiftButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
		ButtonList.startShiftButton.setEnabled(start);
		ButtonList.endShiftButton.setEnabled(end);
		ButtonList.startBreakButton.setEnabled(startbreak);
		ButtonList.endBreakButton.setEnabled(endbreak);
	}
	
	public static void clearInputs() {
		TextFieldList.idField.setText("");
		TextFieldList.nameField.setText("");
		TextFieldList.ageField.setText("");
		TextFieldList.salaryField.setText("");
		ButtonList.adminDisableButton.setSelected(true);
	}
	
	public static void loadEmployeesInBox() {
		UnusualsList.empBox.removeAllItems();
		for (Employee emp : DataHandler.EmployeeList.values()) {
			UnusualsList.empBox.addItem(emp.getName());
		}

	}

}
