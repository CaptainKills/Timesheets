package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class ToDateLabel extends JLabel {

	private static final long serialVersionUID = 6808647604405530632L;
	private static final Logger logger = new Logger(ToDateLabel.class);
	
	private static String labelText = LanguageManager.language.get("to_date_label");

	public ToDateLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("ToDateLabel initialised.");
	}

}
