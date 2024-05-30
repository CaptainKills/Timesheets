package nl.triantis.timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import nl.triantis.timesheets.Settings;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

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
