package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class SelectBackupLabel extends JLabel {

	private static final long serialVersionUID = -607485097021634039L;
	private static final Logger logger = new Logger(SelectBackupLabel.class);

	public SelectBackupLabel() {
		super("Select Backup:");
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("SelectBackupLabel initialised.");
	}

}
