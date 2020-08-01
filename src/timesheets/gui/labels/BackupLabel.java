package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class BackupLabel extends JLabel{
	private static final long serialVersionUID = -4724032273462013983L;
	private static final Logger logger = new Logger(BackupLabel.class.toString());

	public BackupLabel() {
		super("Backups:");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("BackupLabel initialised.");
	}

}
