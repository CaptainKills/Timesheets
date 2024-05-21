package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class FontsizeLabel extends JLabel {
	private static final long serialVersionUID = -1773885514926890347L;
	private static final Logger logger = new Logger(FontsizeLabel.class);
	
	private static String labelText = LanguageManager.language.get("font_size_label");

	public FontsizeLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		

		logger.debug("FontsizeLabel initialised.");
	}

}
