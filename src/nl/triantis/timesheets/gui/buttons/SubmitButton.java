package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.PanelList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.gui.lists.UnusualsList;
import nl.triantis.timesheets.gui.other.CustomOptionPane;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.sql.Database;

public class SubmitButton extends JButton {
	private static final long serialVersionUID = -2944959431303812446L;
	private static final Logger logger = new Logger(SubmitButton.class);

	private int menuChoice;
	private JTextField idField = TextFieldList.idField;
	private JTextField firstNameField = TextFieldList.firstNameField;
	private JTextField lastNameField = TextFieldList.lastNameField;
	private JSpinner ageField = TextFieldList.ageField;
	private JSpinner salaryField = TextFieldList.salaryField;
	private JComboBox<String> empBox = UnusualsList.empBox;

	private Database database = new Database();
	private Map<Integer, Employee> EmployeeList = Database.EmployeeList;
	private Employee transferEmployee;
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String buttonText = lang.get("submit_button_add");
	
	private static String dialogTitleAddSuccess = lang.get("jop_sb_title_add_success");
	private static String dialogMsgAddSuccess = lang.get("jop_sb_msg_add_success");
	private static String dialogTitleAddFail = lang.get("jop_sb_title_add_fail");
	private static String dialogMsgAddFail = lang.get("jop_sb_msg_add_fail");
	
	private static String dialogTitleRemoveSuccess = lang.get("jop_sb_title_remove_success");
	private static String dialogMsgRemoveSuccess = lang.get("jop_sb_msg_remove_success");
	private static String dialogTitleRemoveConfirm = lang.get("jop_sb_title_remove_confirm");
	private static String dialogMsgRemoveConfirm = lang.get("jop_sb_msg_remove_confirm");
	
	private static String dialogTitleEditSuccess = lang.get("jop_sb_title_edit_success");
	private static String dialogMsgEditSuccess = lang.get("jop_sb_msg_edit_success");
	private static String dialogTitleEditConfirm = lang.get("jop_sb_title_edit_confirm");
	private static String dialogMsgEditConfirm = lang.get("jop_sb_msg_edit_confirm");
	private static String dialogTitleEditFail = lang.get("jop_sb_title_edit_fail");
	private static String dialogMsgEditFail = lang.get("jop_sb_msg_edit_fail");

	public SubmitButton() {
		super(buttonText);
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {				
				if (getText().equals(lang.get("submit_button_add"))) {
					addEmployee();
				} else if (getText().equals(lang.get("submit_button_remove"))) {
					removeEmployee();
				} else if (getText().equals(lang.get("submit_button_edit"))) {
					saveEmployee();
				}
			}
		});

		logger.debug("SubmitButton initialised.");
	}

	private void addEmployee() {
		if (idField.getText().equals("") || firstNameField.getText().equals("") || lastNameField.getText().equals("")) {
			CustomOptionPane cop = new CustomOptionPane("SubmitButton (Add, Fail)");
			cop.setText(dialogTitleAddFail, dialogMsgAddFail);
			cop.setConfig(JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			cop.showDialog();
			
			return;
		}
		
		int id = Integer.parseInt(idField.getText());
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		int age = Integer.parseInt(ageField.getValue().toString());
		double salary = Double.parseDouble(salaryField.getValue().toString());
		
		boolean admin = false;
		if(ButtonList.adminEnableButton.isSelected()) {
			admin = true;
		}
		
		transferEmployee = new Employee(id, firstName, lastName, age, salary, admin, new TreeMap<LocalDate, LocalTime[]>());

		EmployeeList.put(transferEmployee.getID(), transferEmployee);
		database.insertEmployee(transferEmployee);
		logger.info("New Employee has been added: " + transferEmployee.getID_String());
		
		CustomOptionPane cop = new CustomOptionPane("SubmitButton (Add, Success)");
		cop.setText(dialogTitleAddSuccess, dialogMsgAddSuccess);
		cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		cop.showDialog();

		PanelList.editPanel.clearInputs();
	}

	private void removeEmployee() {
		CustomOptionPane cop = new CustomOptionPane("SubmitButton (Remove, Confirm)");
		cop.setText(dialogTitleRemoveConfirm, dialogMsgRemoveConfirm);
		cop.setConfig(JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		

		menuChoice = cop.showDialog();
		if (UnusualsList.empBox.getSelectedItem() == null || menuChoice != JOptionPane.YES_OPTION) {
			return;
		}
		
		for (Employee emp : EmployeeList.values()) {
			if (empBox.getSelectedItem().toString().equals(emp.getName())) {
				transferEmployee = emp;
			} else {
				continue;
			}
		}
		empBox.removeItem(empBox.getSelectedItem());
		EmployeeList.remove(transferEmployee.getID());
		database.deleteEmployee(transferEmployee.getID());
		logger.info("Employee " + transferEmployee.getID_String() + " has been removed");

		
		cop = new CustomOptionPane("SubmitButton (Remove, Success)");
		cop.setText(dialogTitleRemoveSuccess, dialogMsgRemoveSuccess);
		cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		cop.showDialog();
	}

	private void saveEmployee() {
		if (idField.getText().equals("") || firstNameField.getText().equals("") || lastNameField.getText().equals("")) {
			CustomOptionPane cop = new CustomOptionPane("SubmitButton (Edit, Fail)");
			cop.setText(dialogTitleEditFail, dialogMsgEditFail);
			cop.setConfig(JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			cop.showDialog();
			
			return;
		}
		
		CustomOptionPane cop = new CustomOptionPane("SubmitButton (Edit, Confirm)");
		cop.setText(dialogTitleEditConfirm, dialogMsgEditConfirm);
		cop.setConfig(JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		
		menuChoice = cop.showDialog();
		if (empBox.getSelectedItem() == null || menuChoice != JOptionPane.YES_OPTION) {
			return;
		}
		
		
		for (Employee emp : EmployeeList.values()) {
			if (empBox.getSelectedItem().toString().equals(emp.getName())) {
				transferEmployee = emp;
			} else {
				continue;
			}
		}

		int oldID = transferEmployee.getID();
		EmployeeList.remove(oldID);

		transferEmployee.setID(Integer.parseInt(idField.getText()));
		
		transferEmployee.setFirstName(firstNameField.getText());
		transferEmployee.setLastName(lastNameField.getText());
		
		transferEmployee.setAge(Integer.parseInt(ageField.getValue().toString()));
		transferEmployee.setSalary(Double.parseDouble(salaryField.getValue().toString()));

		if (ButtonList.adminEnableButton.isSelected()) {
			transferEmployee.setAdmin(true);
		} else if (ButtonList.adminDisableButton.isSelected()) {
			transferEmployee.setAdmin(false);
		}

		EmployeeList.put(transferEmployee.getID(), transferEmployee);
		database.updateEmployee(oldID, transferEmployee);
		logger.info("Employee " + transferEmployee.getID_String() + "'s data has been changed and saved.");

		
		cop = new CustomOptionPane("SubmitButton (Edit, Success)");
		cop.setText(dialogTitleEditSuccess, dialogMsgEditSuccess);
		cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		cop.showDialog();

		PanelList.editPanel.clearInputs();
	}
}
