package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class EmployeeLabel extends JLabel {
	private static final long serialVersionUID = 570599185293754786L;
	private static final Logger logger = new Logger(EmployeeLabel.class);

	public EmployeeLabel() {
		super("Employee");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("EmployeeLabel initialised.");
	}

}
