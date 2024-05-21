package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

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
