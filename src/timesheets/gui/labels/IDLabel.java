package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class IDLabel extends JLabel {
	private static final long serialVersionUID = 4398690736312288268L;
	private static final Logger logger = new Logger(IDLabel.class);

	public IDLabel() {
		super("ID");
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("IDLabel initialised.");
	}

}
