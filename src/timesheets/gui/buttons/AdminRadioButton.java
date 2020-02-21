package timesheets.gui.buttons;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.*;

public class AdminRadioButton extends JRadioButton{

	public AdminRadioButton(String text, Boolean onoff) {
		super(text, onoff);
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
