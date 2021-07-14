package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class LastNameLabel extends JLabel {
	private static final long serialVersionUID = 6515486293672131894L;
	private static final Logger logger = new Logger(LastNameLabel.class);
	
	private static String labelText = LanguageManager.language.get("last_name_label");

	public LastNameLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("LastNameLabel initialised.");
	}

}
