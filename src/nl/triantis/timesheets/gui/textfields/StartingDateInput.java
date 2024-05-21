package nl.triantis.timesheets.gui.textfields;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JSpinner;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.textfields.spinnermodels.SpinnerTemporalModel;
import nl.triantis.timesheets.logging.Logger;

public class StartingDateInput extends JSpinner {
	private static final long serialVersionUID = 6476201239078849628L;
	private static final Logger logger = new Logger(StartingDateInput.class);

	private LocalDate current = LocalDate.now().minusMonths(1);
	private LocalDate min = LocalDate.of(1970, 01, 01);
	private LocalDate max = null;

	public StartingDateInput() {
		setPreferredSize(DimensionList.dateSpinnerSize);
		setFont(FontList.normalFont);
		setEnabled(false);

		SpinnerTemporalModel<LocalDate> model = new SpinnerTemporalModel<LocalDate>(current, min, max, ChronoUnit.DAYS);
		setModel(model);
		((DefaultEditor) getEditor()).getTextField().setEditable(true);

		logger.debug("StartingDateInput initialised.");
	}

}
