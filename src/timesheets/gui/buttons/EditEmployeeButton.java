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

public class EditEmployeeButton extends JButton {
	private static final long serialVersionUID = 2496130667584435874L;
	private static final Logger logger = new Logger(EditEmployeeButton.class);

	public EditEmployeeButton() {
		super("Edit Employee");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayAdminPanels(false, true, false, false);

				ButtonList.submitButton.setText("Save Employee");

				PanelList.editPanel.setupEditPanel(true, true);
				UnusualsList.empBox.loadEmployeesInBox();
			}
		});

		logger.debug("EditEmployeeButton initialised.");
	}

}
