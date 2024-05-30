package nl.triantis.timesheets.gui.textfields;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JSpinner;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.textfields.spinnermodels.SpinnerTemporalModel;
import nl.triantis.timesheets.logging.Logger;

public class EndingDateInput extends JSpinner {
	private static final long serialVersionUID = 2014720119709736931L;
	private static final Logger logger = new Logger(EndingDateInput.class);

	private LocalDate current = LocalDate.now();
	private LocalDate min = LocalDate.of(1970, 01, 01);
	private LocalDate max = null;

	public EndingDateInput() {
		setPreferredSize(DimensionList.dateSpinnerSize);
		setFont(FontList.normalFont);
		setEnabled(false);

		SpinnerTemporalModel<LocalDate> model = new SpinnerTemporalModel<LocalDate>(current, min, max, ChronoUnit.DAYS);
		setModel(model);
		((DefaultEditor) getEditor()).getTextField().setEditable(true);

		logger.debug("EndingDateInput initialised.");
	}

}
