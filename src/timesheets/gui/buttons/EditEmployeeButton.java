package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class EditEmployeeButton extends JButton {
	private static final long serialVersionUID = 2496130667584435874L;
	private static final Logger logger = new Logger(EditEmployeeButton.class);
	
	private static String buttonText = LanguageManager.language.get("edit_employee_button");
	private static String submitButtonText = LanguageManager.language.get("submit_button_edit");

	public EditEmployeeButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayAdminPanels(false, true, false, false);

				ButtonList.submitButton.setText(submitButtonText);

				PanelList.editPanel.setupEditPanel(true, true);
				UnusualsList.empBox.loadEmployeesInBox();
			}
		});

		logger.debug("EditEmployeeButton initialised.");
	}

}
