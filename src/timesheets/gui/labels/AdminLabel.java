package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class AdminLabel extends JLabel {
	private static final long serialVersionUID = -7230745815968369475L;
	private static final Logger logger = new Logger(AdminLabel.class);
	
	private static String labelText = LanguageManager.language.get("admin_label");

	public AdminLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("AdminLabel initialised.");
	}

}
