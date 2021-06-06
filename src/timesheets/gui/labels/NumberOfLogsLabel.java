package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class NumberOfLogsLabel extends JLabel {
	private static final long serialVersionUID = 2419773811473470507L;
	private static final Logger logger = new Logger(NumberOfLogsLabel.class);
	
	private static String labelText = LanguageManager.language.get("number_of_logs_label");

	public NumberOfLogsLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("NumberOfLogsLabel initialised.");
	}

}
