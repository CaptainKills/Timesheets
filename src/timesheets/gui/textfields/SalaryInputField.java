package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class SalaryInputField extends JTextField{
	private static final long serialVersionUID = 5223232371355031531L;
	private static final Logger logger = new Logger(SalaryInputField.class);

	public SalaryInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("SalaryInputField initialised.");
	}

}
