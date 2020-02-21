package timesheets.gui.textfields;

import javax.swing.*;
import timesheets.gui.lists.*;

public class SalaryInputField extends JTextField{

	public SalaryInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
