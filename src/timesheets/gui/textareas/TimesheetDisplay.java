package timesheets.gui.textareas;

import javax.swing.JTextPane;

import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class TimesheetDisplay extends JTextPane{
	
	private static final long serialVersionUID = -5510955245847604938L;
	private static final Logger logger = new Logger(TimesheetDisplay.class.toString());
	
	public TimesheetDisplay() {
		//super(17,20);
		setFont(FontList.normalFont);
		setEditable(false);
		setContentType("text/html");
		
		
		logger.debug("TimesheetDisplay initialised.");
	}
}
