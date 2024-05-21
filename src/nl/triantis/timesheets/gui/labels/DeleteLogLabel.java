package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class DeleteLogLabel extends JLabel {
	private static final long serialVersionUID = -3521919778502204622L;
	private static final Logger logger = new Logger(DeleteLogLabel.class);
	
	private static String labelText = LanguageManager.language.get("delete_log_label");

	public DeleteLogLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("LogLabel initialised.");
	}

}
