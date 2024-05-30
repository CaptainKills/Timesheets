package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class NumberOfBackupsLabel extends JLabel {
	private static final long serialVersionUID = 9098013972829939667L;
	private static final Logger logger = new Logger(NumberOfBackupsLabel.class);
	
	private static String labelText = LanguageManager.language.get("number_of_backups_label");

	public NumberOfBackupsLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("NumberOfBackupsLabel initialised.");
	}

}
