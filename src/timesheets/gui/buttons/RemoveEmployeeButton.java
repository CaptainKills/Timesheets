package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class RemoveEmployeeButton extends JButton{
	private static final long serialVersionUID = 4105654947299367234L;
	private static final Logger logger = new Logger(RemoveEmployeeButton.class.toString());

	public RemoveEmployeeButton() {
		super("Remove Employee");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ExtendedHandler.displayAdminPanels(false, true, false, false);

				ButtonList.submitButton.setText("Remove Employee");
				
				ExtendedHandler.setupEditPanel(false, true);
				UnusualsList.empBox.loadEmployeesInBox();
			}
		});
		
		logger.debug("RemoveEmployeeButton initialised.");
	}

}
