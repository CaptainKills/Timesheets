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

public class AddEmployeeButton extends JButton{
	private static final long serialVersionUID = -3510275592566235838L;
	private static final Logger logger = new Logger(AddEmployeeButton.class.toString());

	public AddEmployeeButton() {
		super("Add Employee");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayAdminPanels(false, true, false, false);

				ButtonList.submitButton.setText("Add Employee");
				PanelList.editPanel.setupEditPanel(true, false);
				UnusualsList.empBox.setSelectedIndex(-1);
			}
		});
		logger.debug("AddEmployeeButton initialised.");
	}

}
