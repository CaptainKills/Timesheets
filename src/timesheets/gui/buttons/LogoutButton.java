package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;
import timesheets.gui.lists.TextFieldList;

public class LogoutButton extends JButton{
	private static final long serialVersionUID = -6786090739017183076L;

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
