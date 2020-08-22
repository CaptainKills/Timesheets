package timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import timesheets.Settings;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class WidthInput extends JSpinner {
	private static final long serialVersionUID = 4567396461584996056L;
	private static final Logger logger = new Logger(WidthInput.class);

	private int current = Integer.parseInt(Settings.settings.get("width"));
	private int min = 1000;
	private int max = 4096;
	private int step = 10;

	public WidthInput() {
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);

		logger.debug("WidthInput initialised.");
	}

}
