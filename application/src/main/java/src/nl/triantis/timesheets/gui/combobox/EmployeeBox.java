package nl.triantis.timesheets.gui.combobox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JComboBox;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.sql.Database;

public class EmployeeBox<T> extends JComboBox<String> {
	private static final long serialVersionUID = -8247944843862213418L;
	private static final Logger logger = new Logger(EmployeeBox.class);

	private Map<Integer, Employee> EmployeeList = Database.EmployeeList;

	public EmployeeBox() {
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.normalFont);
		setVisible(true);
		setEnabled(false);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (getSelectedItem() != null) {
					for (Employee emp : EmployeeList.values()) {
						if (getSelectedItem().toString().equals(emp.getName())) {
							TextFieldList.idField.setText(emp.getID_String());
							TextFieldList.firstNameField.setText(emp.getFirstName());
							TextFieldList.lastNameField.setText(emp.getLastName());
							TextFieldList.ageField.setValue(emp.getAge());
							TextFieldList.salaryField.setValue(emp.getSalary());

							if (emp.getAdmin() == true) {
								ButtonList.adminEnableButton.setSelected(true);
							} else if (emp.getAdmin() == false) {
								ButtonList.adminDisableButton.setSelected(true);
							}

							logger.info("Employee succesfully loaded into fields.");
						} else {
							continue;
						}
					}
				} else {
					return;
				}
			}
		});

		logger.debug("EmployeeBox initialised.");
	}

	public void loadEmployeesInBox() {
		removeAllItems();
		for (Employee emp : Database.EmployeeList.values()) {
			addItem(emp.getName());
		}

	}

}
