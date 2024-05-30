package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.triantis.timesheets.gui.ExtendedHandler;
import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.PanelList;
import nl.triantis.timesheets.gui.lists.UnusualsList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class EditEmployeeButton extends JButton {
	private static final long serialVersionUID = 2496130667584435874L;
	private static final Logger logger = new Logger(EditEmployeeButton.class);
	
	private static String buttonText = LanguageManager.language.get("edit_employee_button");
	private static String submitButtonText = LanguageManager.language.get("submit_button_edit");

	public EditEmployeeButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayPanels(false, false, true, true, false, false);

				ButtonList.submitButton.setText(submitButtonText);

				PanelList.editPanel.setupEditPanel(true, true);
				UnusualsList.empBox.loadEmployeesInBox();
			}
		});

		logger.debug("EditEmployeeButton initialised.");
	}

}
