package timesheets.gui.buttons;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class ReportRadioButton extends JRadioButton {
	
	private static final long serialVersionUID = 7002211714044734797L;
	private static final Logger logger = new Logger(ReportRadioButton.class);

	public ReportRadioButton(String text, Boolean onoff) {
		super(text, onoff);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.CENTER);

		logger.debug("ReportRadioButton initialised.");
	}

}
