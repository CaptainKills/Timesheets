package timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import timesheets.Settings;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class HeightInput extends JSpinner {
	private static final long serialVersionUID = -387410479178733551L;
	private static final Logger logger = new Logger(HeightInput.class);

	private int current = Integer.parseInt(Settings.settings.get("height"));
	private int min = 360;
	private int max = 2160;
	private int step = 10;

	public HeightInput() {
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);

		logger.debug("HeightInput initialised.");
	}

}
