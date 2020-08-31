package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;
import timesheets.update.Update;

public class CheckUpdatesButton extends JButton {
	private static final long serialVersionUID = -1498236342567587430L;
	private static final Logger logger = new Logger(CheckUpdatesButton.class);

	public CheckUpdatesButton() {
		super("Check For Updates");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				Update.checkForUpdates();
				boolean new_version = Update.hasNewVersion();

				if (new_version) {
					JOptionPane.showMessageDialog(PanelList.mainPanel, DisplayList.updateMessagePane,
							"New Update Available!", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(PanelList.mainPanel,
							"There are no new updates. The latest version is currently installed.",
							"No Update Available.", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		logger.debug("CheckUpdatesButton initialised.");
	}

}
