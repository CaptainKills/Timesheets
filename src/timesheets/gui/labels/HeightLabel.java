package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class HeightLabel extends JLabel{
	private static final long serialVersionUID = -4552448806439343723L;
	private static final Logger logger = new Logger(HeightLabel.class.toString());

	public HeightLabel() {
		super("Height:");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		logger.debug("NumberOfLogsLabel initialised.");
	}

}
