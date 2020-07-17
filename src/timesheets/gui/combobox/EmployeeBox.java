package timesheets.gui.combobox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JComboBox;

import timesheets.DataHandler;
import timesheets.Employee;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class EmployeeBox<T> extends JComboBox<T>{
	private static final long serialVersionUID = -8247944843862213418L;
	private static final Logger logger = new Logger(EmployeeBox.class.toString());
	
	private Map<Integer, Employee> EmployeeList = DataHandler.EmployeeList;

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
							TextFieldList.idField.setText(Integer.toString(emp.getID()));
							TextFieldList.nameField.setText(emp.getName());
							TextFieldList.ageField.setText(Integer.toString(emp.getAge()));
							TextFieldList.salaryField.setText(Double.toString(emp.getSalary()));
							
							if (emp.getAdmin() == true) {
								ButtonList.adminEnableButton.setSelected(true);
							} else if (emp.getAdmin() == false) {
								ButtonList.adminDisableButton.setSelected(true);
							}
							
							logger.info("Properties succesfully loaded into fields.");
						} else {
							continue;
						}
					}
				} else {
					return;
				}
				//pack();
			}
		});
		
		logger.debug("EmployeeBox initialised.");
	}

}
