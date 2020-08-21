package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class FontsizeInput extends JTextField{
	private static final long serialVersionUID = 3754150334860596593L;
	private static final Logger logger = new Logger(FontsizeInput.class);
	
	public FontsizeInput() {
		super("", 3);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("FontsizeInput initialised.");
	}

}
