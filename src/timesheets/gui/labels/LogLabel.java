package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class LogLabel extends JLabel{
	private static final long serialVersionUID = -3521919778502204622L;
	private static final Logger logger = new Logger(LogLabel.class);

	public LogLabel() {
		super("Delete Logs:");
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("LogLabel initialised.");
	}

}
