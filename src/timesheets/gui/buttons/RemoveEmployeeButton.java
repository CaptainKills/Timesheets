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

public class RemoveEmployeeButton extends JButton {
	private static final long serialVersionUID = 4105654947299367234L;
	private static final Logger logger = new Logger(RemoveEmployeeButton.class);
	
	private static String buttonText = LanguageManager.language.get("remove_employee_button");
	private static String submitButtonText = LanguageManager.language.get("submit_button_remove");

	public RemoveEmployeeButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayPanels(false, false, true, true, false, false);

				ButtonList.submitButton.setText(submitButtonText);

				PanelList.editPanel.setupEditPanel(false, true);
				UnusualsList.empBox.loadEmployeesInBox();
			}
		});

		logger.debug("RemoveEmployeeButton initialised.");
	}

}
