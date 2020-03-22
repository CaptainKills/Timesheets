package timesheets.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.DimensionList;

public class TestingPanel extends JPanel {
	private static final long serialVersionUID = -3152890576497459265L;

	public TestingPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);

		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					
				);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				
				);
		setLayout(layout);
	}
}
