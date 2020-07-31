package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class LogCountInput extends JTextField{
	private static final long serialVersionUID = -4631438193119479926L;
	private static final Logger logger = new Logger(LogCountInput.class.toString());
	
	public LogCountInput() {
		super("");
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("LogCountInput initialised.");
	}

}
