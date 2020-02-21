package timesheets.gui.textfields;

import javax.swing.*;
import timesheets.gui.lists.*;

public class IDInputField extends JTextField{

	public IDInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
