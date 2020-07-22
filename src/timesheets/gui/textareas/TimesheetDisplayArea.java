package timesheets.gui.textareas;

import javax.swing.JTextArea;

import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class TimesheetDisplayArea extends JTextArea{
	
	private static final long serialVersionUID = -5510955245847604938L;
	private static final Logger logger = new Logger(TimesheetDisplayArea.class.toString());
	
	public TimesheetDisplayArea() {
		super(17,20);
		setFont(FontList.normalFont);
		setEditable(true);
		
		logger.debug("TimesheetdisplayArea initialised.");
	}
}
