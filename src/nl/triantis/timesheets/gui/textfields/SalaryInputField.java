package nl.triantis.timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class SalaryInputField extends JSpinner {
	private static final long serialVersionUID = 5223232371355031531L;
	private static final Logger logger = new Logger(SalaryInputField.class);

	private double current = 0.0;
	private double min = 0.0;
	private double max = 10000.0;
	private double step = 0.25;

	public SalaryInputField() {
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);

		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);
		setEditor(new JSpinner.NumberEditor(this, "0.00"));

		logger.debug("SalaryInputField initialised.");
	}

}
