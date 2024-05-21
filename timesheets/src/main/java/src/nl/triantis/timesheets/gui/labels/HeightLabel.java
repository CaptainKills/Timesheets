package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class HeightLabel extends JLabel {
	private static final long serialVersionUID = -4552448806439343723L;
	private static final Logger logger = new Logger(HeightLabel.class);
	
	private static String labelText = LanguageManager.language.get("height_label");

	public HeightLabel() {
		super(labelText);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		logger.debug("NumberOfLogsLabel initialised.");
	}

}
