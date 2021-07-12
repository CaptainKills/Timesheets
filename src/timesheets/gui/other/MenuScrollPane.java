package timesheets.gui.other;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import timesheets.logging.Logger;

public class MenuScrollPane extends JScrollPane {
	
	private static final long serialVersionUID = 4523068007435120865L;
	private static final Logger logger = new Logger(MenuScrollPane.class);
	
	public MenuScrollPane() {
		super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);		
		logger.debug("MenuScrollPane initialised.");
	}
	
	public void setupButtons() {
		JPanel buttonPanel = new JPanel();
		
		GroupLayout layout = new GroupLayout(buttonPanel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// @formatter:off
		layout.setHorizontalGroup(layout.createSequentialGroup()
				
				);

		layout.setVerticalGroup(layout.createSequentialGroup()

				);
		// @formatter:on
		
		buttonPanel.setLayout(layout);
		buttonPanel.setBackground(Color.WHITE);
				
		setViewportView(buttonPanel);
	}

}
