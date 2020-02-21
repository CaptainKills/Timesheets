package timesheets.gui.textfields;

import javax.swing.*;
import timesheets.gui.lists.*;

public class NameInputField extends JTextField{

	public NameInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
