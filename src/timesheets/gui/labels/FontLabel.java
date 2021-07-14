package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class FontLabel extends JLabel {
	private static final long serialVersionUID = 3153734607755987762L;
	private static final Logger logger = new Logger(FontLabel.class);
	
	private static String labelText = LanguageManager.language.get("font_label");

	public FontLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("FontLabel initialised.");
	}

}
