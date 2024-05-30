package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class WidthLabel extends JLabel {
	private static final long serialVersionUID = -8955312753411703189L;
	private static final Logger logger = new Logger(WidthLabel.class);
	
	private static String labelText = LanguageManager.language.get("width_label");

	public WidthLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("WidthLabel initialised.");
	}

}
