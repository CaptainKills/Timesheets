package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class AgeInputField extends JTextField{
	private static final long serialVersionUID = -3950876451954224558L;
	private static final Logger logger = new Logger(AgeInputField.class);

	public AgeInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("AgeInputField initialised.");
	}

}
