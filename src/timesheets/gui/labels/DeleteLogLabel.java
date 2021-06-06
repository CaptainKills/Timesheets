package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class DeleteLogLabel extends JLabel {
	private static final long serialVersionUID = -3521919778502204622L;
	private static final Logger logger = new Logger(DeleteLogLabel.class);
	
	private static String labelText = LanguageManager.language.get("delete_log_label");

	public DeleteLogLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("LogLabel initialised.");
	}

}
