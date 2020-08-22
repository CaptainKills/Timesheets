package timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class AgeInputField extends JSpinner {
	private static final long serialVersionUID = -3950876451954224558L;
	private static final Logger logger = new Logger(AgeInputField.class);
	
	private int current = 20;
	private int min = 14;
	private int max = 100;
	private int step = 1;

	public AgeInputField() {
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		
		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);
		
		logger.debug("AgeInputField initialised.");
	}

}
