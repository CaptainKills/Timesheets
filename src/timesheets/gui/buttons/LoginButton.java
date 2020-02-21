package timesheets.gui.buttons;

import java.awt.event.*;
import timesheets.gui.lists.*;
import timesheets.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class LoginButton extends JButton{
	private DataHandler data = new DataHandler();
	private int id = 0;

	public LoginButton() {
		super("Login");
		setPreferredSize(DimensionList.buttonSize_large);
		setFont(FontList.buttonFont);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(TextFieldList.inputField.getText().equals("")) {
					return;
				}
				id = Integer.parseInt(TextFieldList.inputField.getText());
				
				if (data.isIdUsed(id)) {
					Employee activeEmployee = DataHandler.EmployeeList.get(id);
					
					TextAreaList.loginTextArea.updateInfoText("Welcome " + activeEmployee.getName());
					setEnabled(false);

					if (activeEmployee.getTimeStarted() == null) {
						ButtonList.startShiftButton.setEnabled(true);
					}
					
					if (activeEmployee.getTimeStarted() != null && activeEmployee.getTimePaused() != null) {
						ButtonList.endShiftButton.setEnabled(true);
					} else if (activeEmployee.getTimeStarted() != null && (activeEmployee.getTimePaused() == null
							&& activeEmployee.getBreakStarted() == null)) {
						ButtonList.endShiftButton.setEnabled(true);
					}

					if (activeEmployee.getBreakStarted() == null && activeEmployee.getTimeStarted() != null) {
						ButtonList.startBreakButton.setEnabled(true);
					}

					if (activeEmployee.getBreakEnded() == null && activeEmployee.getBreakStarted() != null) {
						ButtonList.endBreakButton.setEnabled(true);
					}

					if (activeEmployee.getAdmin() == true) {
						ButtonList.switchMenuButton.setEnabled(true);
					}

					ButtonList.logoutButton.setEnabled(true);
					//pack();
				} else {
					JOptionPane.showMessageDialog(PanelList.mainPanel, "Please enter correct ID!", "Incorrect ID",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
