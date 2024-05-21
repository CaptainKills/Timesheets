package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class FirstNameLabel extends JLabel {
	private static final long serialVersionUID = 6515486293672131894L;
	private static final Logger logger = new Logger(FirstNameLabel.class);
	
	private static String labelText = LanguageManager.language.get("first_name_label");

	public FirstNameLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("FirstNameLabel initialised.");
	}

}
