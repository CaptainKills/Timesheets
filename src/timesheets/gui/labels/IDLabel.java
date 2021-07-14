package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class IDLabel extends JLabel {
	private static final long serialVersionUID = 4398690736312288268L;
	private static final Logger logger = new Logger(IDLabel.class);
	
	private static String labelText = LanguageManager.language.get("id_label");

	public IDLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("IDLabel initialised.");
	}

}
