package timesheets.gui.labels;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class SeperatorLabel extends JLabel{
	private static final long serialVersionUID = 6505078244088230911L;
	private static final Logger logger = new Logger(SeperatorLabel.class);

	public SeperatorLabel(String c) {
		super(c);
		setPreferredSize(new Dimension(1, 1));
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		logger.debug("SeperatorLabel initialised.");
	}

}
