package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class NumberOfBackupsLabel extends JLabel{
	private static final long serialVersionUID = 9098013972829939667L;
	private static final Logger logger = new Logger(NumberOfBackupsLabel.class.toString());

	public NumberOfBackupsLabel() {
		super("# of backups:");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		logger.debug("NumberOfBackupsLabel initialised.");
	}

}
