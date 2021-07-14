package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class AppDimensionLabel extends JLabel {
	private static final long serialVersionUID = 3012688302533129418L;
	private static final Logger logger = new Logger(AppDimensionLabel.class);
	
	private static String labelText = LanguageManager.language.get("app_dimension_label");

	public AppDimensionLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("AppDimensionLabel initialised.");
	}

}
