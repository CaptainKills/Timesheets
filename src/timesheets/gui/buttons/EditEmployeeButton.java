package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class EditEmployeeButton extends JButton{
	private static final long serialVersionUID = 2496130667584435874L;
	private static final Logger logger = new Logger(EditEmployeeButton.class.toString());

	public EditEmployeeButton() {
		super("Edit Employee");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PanelList.editPanel.setVisible(true);
				PanelList.timesheetPanel.setVisible(false);

				ButtonList.submitButton.setText("Save Employee");
				
				TextFieldList.idField.setEnabled(true);
				ButtonList.idButton.setEnabled(true);
				TextFieldList.nameField.setEnabled(true);
				TextFieldList.ageField.setEnabled(true);
				TextFieldList.salaryField.setEnabled(true);
				
				ButtonList.adminEnableButton.setEnabled(true);
				ButtonList.adminDisableButton.setEnabled(true);
				UnusualsList.empBox.setEnabled(true);
				
				ExtendedHandler.loadEmployeesInBox();
				ExtendedHandler.clearInputs();
			}
		});
		
		logger.debug("EditEmployeeButton initialised.");
	}

}
