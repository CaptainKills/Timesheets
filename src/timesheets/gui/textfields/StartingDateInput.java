package timesheets.gui.textfields;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JSpinner;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.textfields.spinnermodels.SpinnerTemporalModel;
import timesheets.logging.Logger;

public class StartingDateInput extends JSpinner {
	private static final long serialVersionUID = 6476201239078849628L;
	private static final Logger logger = new Logger(StartingDateInput.class);

	private LocalDate current = LocalDate.now().minusMonths(1);
	private LocalDate min = LocalDate.of(1970, 01, 01);
	private LocalDate max = null;

	public StartingDateInput() {
		setPreferredSize(DimensionList.dateSpinnerSize);
		setFont(FontList.normalFont);

		SpinnerTemporalModel<LocalDate> model = new SpinnerTemporalModel<LocalDate>(current, min, max, ChronoUnit.DAYS);
		setModel(model);
		((DefaultEditor) getEditor()).getTextField().setEditable(true);

		logger.debug("StartingDateInput initialised.");
	}

}
