package timesheets.gui.labels;

import javax.swing.JLabel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class WidthLabel extends JLabel{
	private static final long serialVersionUID = -8955312753411703189L;
	private static final Logger logger = new Logger(WidthLabel.class);

	public WidthLabel() {
		super("Width:");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		logger.debug("WidthLabel initialised.");
	}

}
