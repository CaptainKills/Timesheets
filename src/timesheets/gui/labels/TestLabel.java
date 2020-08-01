package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class TestLabel extends JLabel{
	private static final long serialVersionUID = -5109469619092914834L;
	private static final Logger logger = new Logger(TestLabel.class.toString());

	public TestLabel() {
		super("Test.....:");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("TestLabel initialised.");
	}

}
