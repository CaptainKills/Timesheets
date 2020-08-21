package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class WidthInput extends JTextField{
	private static final long serialVersionUID = 4567396461584996056L;
	private static final Logger logger = new Logger(WidthInput.class);
	
	public WidthInput() {
		super("", 4);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("WidthInput initialised.");
	}

}
