package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class SelectBackupLabel extends JLabel {

	private static final long serialVersionUID = -607485097021634039L;
	private static final Logger logger = new Logger(SelectBackupLabel.class);
	
	private static String labelText = LanguageManager.language.get("select_backup_label");

	public SelectBackupLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("SelectBackupLabel initialised.");
	}

}
