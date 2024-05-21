package nl.triantis.timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import nl.triantis.timesheets.Settings;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class LogCountInput extends JSpinner {
	private static final long serialVersionUID = -4631438193119479926L;
	private static final Logger logger = new Logger(LogCountInput.class);

	private int current = Integer.parseInt(Settings.settings.get("number_of_logs"));
	private int min = 1;
	private int max = 500;
	private int step = 1;

	public LogCountInput() {
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);

		logger.debug("LogCountInput initialised.");
	}

}
