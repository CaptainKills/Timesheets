package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.*;

public class LogoutButton extends JButton{

	public LogoutButton() {
		super("Logout");
		setPreferredSize(DimensionList.buttonSize_medium);
		setFont(FontList.buttonFont);
		setEnabled(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
				TextAreaList.loginTextArea.updateInfoText("Please enter ID to log in...");
				ButtonList.switchMenuButton.setText(">>");
				ButtonList.switchMenuButton.setEnabled(false);

				ButtonList.loginButton.setEnabled(true);
				ExtendedHandler.enableShiftButtons(false, false, false, false);
				setEnabled(false);

				PanelList.numpadPanel.setVisible(true);
				//PanelList.getAdminPanel().setVisible(false);
				//dateControlPanel.setVisible(false);
				//dateDisplayPanel.setVisible(false);
				//pack();
			}
		});
	}

}
