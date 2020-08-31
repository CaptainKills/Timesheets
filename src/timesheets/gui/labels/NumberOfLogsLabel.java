package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class NumberOfLogsLabel extends JLabel {
	private static final long serialVersionUID = 2419773811473470507L;
	private static final Logger logger = new Logger(NumberOfLogsLabel.class);

	public NumberOfLogsLabel() {
		super("# of logs:");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("NumberOfLogsLabel initialised.");
	}

}
