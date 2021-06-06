package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class AgeSalaryLabel extends JLabel {
	private static final long serialVersionUID = -8131209570006669940L;
	private static final Logger logger = new Logger(AgeSalaryLabel.class);
	
	private static String labelText = LanguageManager.language.get("age_salary_label");

	public AgeSalaryLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("AgeSalaryLabel initialised.");
	}

}
