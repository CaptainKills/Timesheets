package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.update.Update;

public class CheckUpdatesButton extends JButton {
	private static final long serialVersionUID = -1498236342567587430L;
	private static final Logger logger = new Logger(CheckUpdatesButton.class);
	
	private static String buttonText = LanguageManager.language.get("check_updates_button");

	public CheckUpdatesButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				Update.checkForUpdates(true);
			}
		});
		logger.debug("CheckUpdatesButton initialised.");
	}

}
