package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

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
