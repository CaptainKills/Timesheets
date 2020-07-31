package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class FontsizeLabel extends JLabel{
	private static final long serialVersionUID = -1773885514926890347L;
	private static final Logger logger = new Logger(FontsizeLabel.class.toString());

	public FontsizeLabel() {
		super("Font size:");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("FontsizeLabel initialised.");
	}

}
