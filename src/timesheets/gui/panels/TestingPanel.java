package timesheets.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.TextAreaList;
import timesheets.logging.Logger;

public class TestingPanel extends JPanel {
	private static final long serialVersionUID = -3152890576497459265L;
	private static final Logger logger = new Logger(TestingPanel.class.toString());

	public TestingPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);

		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addComponent(TextAreaList.timesheetDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)	
				);

		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addComponent(TextAreaList.timesheetDisplay, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				);
		setLayout(layout);
		
		logger.debug("TestingPanel initialised.");
	}
}
