package timesheets.gui;

import java.awt.Container;

import javax.swing.JOptionPane;

import timesheets.Employee;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class ExtendedHandler {
	
	private static final Logger logger = new Logger(ExtendedHandler.class.toString());
	private static Database database = new Database();
	
	public static void exitApplication(Container rootPane) {
		if (JOptionPane.showConfirmDialog(rootPane, "Are you sure?") == JOptionPane.YES_OPTION) {
			logger.info("Closing Application...");
			database.backupDatabase();
			logger.info("Application Closed.\n");
			System.exit(0);
		}
	}
	
	public static void enableShiftButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
		ButtonList.startShiftButton.setEnabled(start);
		ButtonList.endShiftButton.setEnabled(end);
		ButtonList.startBreakButton.setEnabled(startbreak);
		ButtonList.endBreakButton.setEnabled(endbreak);
	}
	
	public static void setupEditPanel(boolean fields, boolean combo) {
		TextFieldList.idField.setEnabled(fields);
		ButtonList.idButton.setEnabled(fields);
		TextFieldList.nameField.setEnabled(fields);
		TextFieldList.ageField.setEnabled(fields);
		TextFieldList.salaryField.setEnabled(fields);
		
		ButtonList.adminEnableButton.setEnabled(fields);
		ButtonList.adminDisableButton.setEnabled(fields);
		UnusualsList.empBox.setEnabled(combo);
		
		clearInputs();
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
		for (Employee emp : Database.EmployeeList.values()) {
			UnusualsList.empBox.addItem(emp.getName());
		}

	}
	
	public static void displayTimeButtons(boolean bool) {
		TextAreaList.loginTextArea.setVisible(bool);
		ButtonList.startShiftButton.setVisible(bool);
		ButtonList.endShiftButton.setVisible(bool);
		ButtonList.startBreakButton.setVisible(bool);
		ButtonList.endBreakButton.setVisible(bool);
	}
	
	public static void displayAdminButtons(boolean bool) {
		ButtonList.addEmployeeButton.setVisible(bool);
		ButtonList.removeEmployeeButton.setVisible(bool);
		ButtonList.editEmployeeButton.setVisible(bool);
		ButtonList.printSheetsButton.setVisible(bool);
		ButtonList.editSheetsButton.setVisible(bool);
		ButtonList.settingsButton.setVisible(bool);
		ButtonList.exitButton.setVisible(bool);
	}
	
	public static void displayAdminPanels(boolean numpad, boolean edit, boolean timesheet, boolean settings) {
		PanelList.numpadPanel.setVisible(numpad);
		PanelList.editPanel.setVisible(edit);
		PanelList.timesheetPanel.setVisible(timesheet);
		PanelList.settingsPanel.setVisible(settings);
	}

}
