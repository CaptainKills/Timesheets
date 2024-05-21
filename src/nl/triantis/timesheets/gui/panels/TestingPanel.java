package nl.triantis.timesheets.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.logging.Logger;

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
				.addComponent(ButtonList.tipCalculationButton)
				);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(ButtonList.tipCalculationButton)
				);
		// @formatter:on
		setLayout(layout);

		logger.debug("TestingPanel initialised.");
	}
}
