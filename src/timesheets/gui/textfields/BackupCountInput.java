package timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import timesheets.Settings;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class BackupCountInput extends JSpinner {
	private static final long serialVersionUID = -5606731836124670339L;
	private static final Logger logger = new Logger(BackupCountInput.class);

	private int current = Integer.parseInt(Settings.settings.get("number_of_backups"));
	private int min = 1;
	private int max = 100;
	private int step = 1;

	public BackupCountInput() {
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);

		logger.debug("BackupCountInput initialised.");
	}

}
