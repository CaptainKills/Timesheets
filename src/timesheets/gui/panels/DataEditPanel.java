package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class DataEditPanel extends JPanel {
	private static final long serialVersionUID = 5115383729678262568L;
	private static final Logger logger = new Logger(DataEditPanel.class);

	public DataEditPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);
		setBackground(Color.WHITE);
		setVisible(false);

		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(LabelList.empLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							.addComponent(LabelList.idLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							.addComponent(LabelList.nameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							.addComponent(LabelList.ageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							.addComponent(LabelList.adminLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							)
					.addGroup(layout.createParallelGroup()
							.addComponent(LabelList.sep1Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(LabelList.sep2Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(LabelList.sep3Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(LabelList.sep4Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(LabelList.sep5Label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							)
					.addGroup(layout.createParallelGroup()
							.addComponent(UnusualsList.empBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							.addGroup(layout.createSequentialGroup()
									.addComponent(ButtonList.idButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addComponent(TextFieldList.idField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									)
							.addComponent(TextFieldList.nameField)
							.addGroup(layout.createSequentialGroup()
									.addComponent(TextFieldList.ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addComponent(TextFieldList.salaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									)
							.addGroup(layout.createSequentialGroup()
									.addComponent(ButtonList.adminEnableButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addComponent(ButtonList.adminDisableButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									)
							.addComponent(ButtonList.submitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							)
				);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.empLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.sep1Label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(UnusualsList.empBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.idLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.sep2Label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.idButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.idField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.nameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.sep3Label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.ageLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.sep4Label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.ageField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.salaryField, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.adminLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.sep5Label, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.adminEnableButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.adminDisableButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.submitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				);
		setLayout(layout);
		
		logger.debug("DataEditPanel initialised.");
	}
	
	public void setupEditPanel(boolean fields, boolean combo) {
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

	public void clearInputs() {
		TextFieldList.idField.setText("");
		TextFieldList.nameField.setText("");
		TextFieldList.ageField.setText("");
		TextFieldList.salaryField.setText("");
		ButtonList.adminDisableButton.setSelected(true);
	}
}
