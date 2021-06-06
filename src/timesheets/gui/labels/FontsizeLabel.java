package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class FontsizeLabel extends JLabel {
	private static final long serialVersionUID = -1773885514926890347L;
	private static final Logger logger = new Logger(FontsizeLabel.class);
	
	private static String labelText = LanguageManager.language.get("font_size_label");

	public FontsizeLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("FontsizeLabel initialised.");
	}

}
