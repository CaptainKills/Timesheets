package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class AppDimensionLabel extends JLabel {
	private static final long serialVersionUID = 3012688302533129418L;
	private static final Logger logger = new Logger(AppDimensionLabel.class);

	public AppDimensionLabel() {
		super("Dimensions:");
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		// setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

		logger.debug("AppDimensionLabel initialised.");
	}

}
