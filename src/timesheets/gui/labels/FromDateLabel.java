package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class FromDateLabel extends JLabel{

	private static final long serialVersionUID = 2864686207805975703L;
	private static final Logger logger = new Logger(FromDateLabel.class);

	public FromDateLabel() {
		super("From:");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		logger.debug("FromDateLabel initialised.");
	}

}
