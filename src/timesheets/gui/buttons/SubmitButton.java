package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import timesheets.Employee;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class SubmitButton extends JButton {
	private static final long serialVersionUID = -2944959431303812446L;
	private static final Logger logger = new Logger(SubmitButton.class);
	
	private int menuChoice;
	private JTextField idField = TextFieldList.idField;
	private JTextField nameField = TextFieldList.nameField;
	private JTextField ageField = TextFieldList.ageField;
	private JTextField salaryField = TextFieldList.salaryField;
	private JComboBox<String> empBox = UnusualsList.empBox;
	
	private Database database = new Database();
	private Map<Integer, Employee> EmployeeList = Database.EmployeeList;
	private Employee transferEmployee;

	public SubmitButton() {
		super("Submit");
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (getText().equals("Add Employee")) {
					addEmployee();
				} else if (getText().equals("Remove Employee")) {
					removeEmployee();
				} else if (getText().equals("Save Employee")) {
					saveEmployee();
				}
			}
		});
		
		logger.debug("SubmitButton initialised.");
	}

	private void addEmployee() {
		if (idField.getText().equals("") || nameField.getText().equals("") || ageField.getText().equals("")
				|| salaryField.getText().equals("")) {
			JOptionPane.showMessageDialog(PanelList.mainPanel, "Please fill in all fields before submitting!",
					"Empty Field!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (ButtonList.adminEnableButton.isSelected()) {
				transferEmployee = new Employee(Integer.parseInt(idField.getText()), nameField.getText(),
				Integer.parseInt(ageField.getText()),
				Double.parseDouble(salaryField.getText().replace(",", ".")), true, new TreeMap<LocalDate, LocalTime[]>());
			} else if (ButtonList.adminDisableButton.isSelected()) {
				transferEmployee = new Employee(Integer.parseInt(idField.getText()), nameField.getText(),
				Integer.parseInt(ageField.getText()),
				Double.parseDouble(salaryField.getText().replace(",", ".")), false, new TreeMap<LocalDate, LocalTime[]>());
			}
			
			EmployeeList.put(transferEmployee.getID(), transferEmployee);
			database.insertEmployee(transferEmployee);
			logger.info("New Employee has been added: " + transferEmployee.getID_String());
			
			JOptionPane.showMessageDialog(PanelList.mainPanel, "Employee has succesfully been created!",
					"Successful Creation!", JOptionPane.INFORMATION_MESSAGE);

			PanelList.editPanel.clearInputs();
		}
	}

	private void removeEmployee() {
		
		menuChoice = JOptionPane.showConfirmDialog(PanelList.mainPanel, "Are you sure you want to remove this employee?",
				"Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);
		if (UnusualsList.empBox.getSelectedItem() != null && menuChoice == JOptionPane.YES_OPTION) {
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
			
			JOptionPane.showMessageDialog(PanelList.mainPanel, "Employee has succesfully been removed!", "Successfull Removal!",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (menuChoice == JOptionPane.CANCEL_OPTION) {
			return;
		}
	}

	private void saveEmployee() {
		
		if (idField.getText().equals("") || nameField.getText().equals("") || ageField.getText().equals("")
				|| salaryField.getText().equals("")) {
			JOptionPane.showMessageDialog(PanelList.mainPanel, "Please fill in all fields before submitting!", "Empty Field!",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			menuChoice = JOptionPane.showConfirmDialog(PanelList.mainPanel,
					"Are you sure you want to save this employee?", "Are you sure?",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (empBox.getSelectedItem() != null && menuChoice == JOptionPane.YES_OPTION) {
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
				transferEmployee.setName(nameField.getText());
				transferEmployee.setAge(Integer.parseInt(ageField.getText()));
				transferEmployee.setSalary(Double.parseDouble(salaryField.getText()));

				if (ButtonList.adminEnableButton.isSelected()) {
					transferEmployee.setAdmin(true);
				} else if (ButtonList.adminDisableButton.isSelected()) {
					transferEmployee.setAdmin(false);
				}
				
				EmployeeList.put(transferEmployee.getID(), transferEmployee);
				database.updateEmployee(oldID, transferEmployee);
				logger.info("Employee " + transferEmployee.getID_String() + "'s data has been changed and saved.");
				
				JOptionPane.showMessageDialog(PanelList.mainPanel, "Employee has succesfully been saved!",
						"Successful Edit!", JOptionPane.INFORMATION_MESSAGE);

				PanelList.editPanel.clearInputs();
			} else if (menuChoice == JOptionPane.CANCEL_OPTION) {
				return;
			}
		}
	}
}
