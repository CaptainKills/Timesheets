package timesheets.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.DimensionList;
import timesheets.logging.Logger;

public class TestingPanel extends JPanel {
	private static final long serialVersionUID = -3152890576497459265L;
	private static final Logger logger = new Logger(TestingPanel.class);

	public TestingPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);

		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// @formatter:off
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
					
				);
		// @formatter:on
		setLayout(layout);

		logger.debug("TestingPanel initialised.");
	}
}
