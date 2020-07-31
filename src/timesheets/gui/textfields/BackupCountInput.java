package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class BackupCountInput extends JTextField{
	private static final long serialVersionUID = -5606731836124670339L;
	private static final Logger logger = new Logger(BackupCountInput.class.toString());
	
	public BackupCountInput() {
		super("");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("BackupCountInput initialised.");
	}

}
