package timesheets.gui.buttons;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class AdminRadioButton extends JRadioButton{
	private static final long serialVersionUID = 6317798246219219156L;
	private static final Logger logger = new Logger(AdminRadioButton.class);

	public AdminRadioButton(String text, Boolean onoff) {
		super(text, onoff);
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("AdminRadioButton initialised.");
	}

}
