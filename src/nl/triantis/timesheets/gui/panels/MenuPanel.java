package nl.triantis.timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.DisplayList;
import nl.triantis.timesheets.logging.Logger;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 6491634731585216891L;
	private static final Logger logger = new Logger(MenuPanel.class);

	public MenuPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);
		setBackground(Color.WHITE);

		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// @formatter:off		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(DisplayList.loginTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.startShiftButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.endShiftButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.startBreakButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.endBreakButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(ButtonList.logoutButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(ButtonList.switchMenuButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						)
			);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(DisplayList.loginTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(ButtonList.startShiftButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(ButtonList.endShiftButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(ButtonList.startBreakButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				.addComponent(ButtonList.endBreakButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.logoutButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.switchMenuButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				);
		// @formatter:on
		setLayout(layout);

		logger.debug("MenuPanel initalised.");
	}

}
