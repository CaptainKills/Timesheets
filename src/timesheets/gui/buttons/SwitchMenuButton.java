package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;
import timesheets.logging.Logger;

public class SwitchMenuButton extends JButton{
	private static final long serialVersionUID = 4691645706084905079L;
	private static final Logger logger = new Logger(SwitchMenuButton.class.toString());

	public SwitchMenuButton() {
		super(">>");
		setPreferredSize(DimensionList.buttonSize_small);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (getText().equals(">>")) {
					PanelList.numpadPanel.setVisible(false);
					PanelList.editPanel.setVisible(true);
					
					displayTimeButtons(false);
					displayAdminButtons(true);
					
					setText("<<");
				} else if (getText().equals("<<")) {
					PanelList.numpadPanel.setVisible(true);
					
					displayTimeButtons(true);
					displayAdminButtons(false);
					displayAdminPanels(false);
					setText(">>");
				}
			}
		});
		
		logger.debug("SwitchMenuButton initialised.");
	}
	
	private void displayTimeButtons(boolean bool) {
		TextAreaList.loginTextArea.setVisible(bool);
		ButtonList.startShiftButton.setVisible(bool);
		ButtonList.endShiftButton.setVisible(bool);
		ButtonList.startBreakButton.setVisible(bool);
		ButtonList.endBreakButton.setVisible(bool);
	}

	private void displayAdminButtons(boolean bool) {
		ButtonList.addEmployeeButton.setVisible(bool);
		ButtonList.removeEmployeeButton.setVisible(bool);
		ButtonList.editEmployeeButton.setVisible(bool);
		ButtonList.printSheetsButton.setVisible(bool);
		ButtonList.editSheetsButton.setVisible(bool);
		ButtonList.settingsButton.setVisible(bool);
		ButtonList.exitButton.setVisible(bool);
	}
	
	private void displayAdminPanels(boolean bool) {
		PanelList.editPanel.setVisible(bool);
		PanelList.timesheetPanel.setVisible(bool);
		PanelList.settingsPanel.setVisible(bool);
	}

}
