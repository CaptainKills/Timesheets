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

public class RemoveEmployeeButton extends JButton{
	private static final long serialVersionUID = 4105654947299367234L;

	public RemoveEmployeeButton() {
		super("Remove Employee");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				PanelList.editPanel.setVisible(true);
				//mainEmpLabelPanel.setVisible(true);
				//mainEmpControlPanel.setVisible(true);
				//dateControlPanel.setVisible(false);
				//dateDisplayPanel.setVisible(false);

				ButtonList.submitButton.setText("Remove Employee");
				
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
				//pack();
			}
		});
	}

}
