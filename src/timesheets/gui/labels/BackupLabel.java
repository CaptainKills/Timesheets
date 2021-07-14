package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class BackupLabel extends JLabel {
	private static final long serialVersionUID = -4724032273462013983L;
	private static final Logger logger = new Logger(BackupLabel.class);
	
	private static String labelText = LanguageManager.language.get("backup_label");

	public BackupLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("BackupLabel initialised.");
	}

}
