package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class AdminLabel extends JLabel{
	private static final long serialVersionUID = -7230745815968369475L;
	private static final Logger logger = new Logger(AdminLabel.class);

	public AdminLabel() {
		super("Admin");
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("AdminLabel initialised.");
	}

}
