package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class ReturnToNumpadButton extends JButton {
	private static final long serialVersionUID = 4691645706084905079L;
	private static final Logger logger = new Logger(ReturnToNumpadButton.class);
	
	private static String buttonText = LanguageManager.language.get("return_to_numpad_button");

	public ReturnToNumpadButton() {
		super(buttonText);
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayPanels(true, true, false, false, false, false);
			}
		});

		logger.debug("ReturnToNumpadButton initialised.");
	}
}
