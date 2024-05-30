package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class FromDateLabel extends JLabel {

	private static final long serialVersionUID = 2864686207805975703L;
	private static final Logger logger = new Logger(FromDateLabel.class);
	
	private static String labelText = LanguageManager.language.get("from_date_label");

	public FromDateLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("FromDateLabel initialised.");
	}

}
