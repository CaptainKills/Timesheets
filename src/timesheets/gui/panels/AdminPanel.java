package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LayoutList;
import timesheets.logging.Logger;

public class AdminPanel extends JPanel{
	private static final long serialVersionUID = -7890834504049559961L;
	private static final Logger logger = new Logger(AdminPanel.class.toString());

	public AdminPanel() {
		setPreferredSize(DimensionList.defaultPanelSize);
		setLayout(LayoutList.defaultLayout);
		setBackground(Color.WHITE);
		setVisible(false);
		
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
		
		logger.debug("AdminPanel initialised.");
	}

}