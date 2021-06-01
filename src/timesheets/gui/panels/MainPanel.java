package timesheets.gui.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 7649238192790429353L;
	private static final Logger logger = new Logger(MainPanel.class);

	public MainPanel() {
		super();
		setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		logger.debug("MainPanel initialised.");
	}

	public void setupPanel() {
		// add(PanelList.testPanel);
		add(PanelList.numpadPanel);
		add(PanelList.menuPanel);
		add(PanelList.editPanel);
		add(PanelList.timesheetPanel);
		add(PanelList.settingsPanel);

		logger.debug("Panel Setup Complete.");
	}

}