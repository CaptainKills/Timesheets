package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;

public class AddEmployeeButton extends JButton{
	
	private static final long serialVersionUID = -3510275592566235838L;

	public AddEmployeeButton() {
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//mainEmpLabelPanel.setVisible(true);
				//mainEmpControlPanel.setVisible(true);
				//dateControlPanel.setVisible(false);
				//dateDisplayPanel.setVisible(false);

				ButtonList.submitButton.setText("Add Employee");
				
				TextFieldList.idField.setEnabled(true);
				ButtonList.idButton.setEnabled(true);
				TextFieldList.nameField.setEnabled(true);
				TextFieldList.ageField.setEnabled(true);
				TextFieldList.salaryField.setEnabled(true);
				
				ButtonList.adminEnableButton.setEnabled(true);
				ButtonList.adminDisableButton.setEnabled(true);
				UnusualsList.empBox.setSelectedIndex(-1);

				ExtendedHandler.clearInputs();
				//pack();
			}
		});
	}

}
