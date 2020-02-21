package timesheets.gui.textfields;

import javax.swing.*;
import timesheets.gui.lists.*;

public class AgeInputField extends JTextField{

	public AgeInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
