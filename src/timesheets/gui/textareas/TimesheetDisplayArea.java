package timesheets.gui.textareas;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class TimesheetDisplayArea extends JTextArea{
	private static final long serialVersionUID = -5510955245847604938L;
	private static final Logger logger = new Logger(TimesheetDisplayArea.class.toString());

//	JScrollPane scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	public TimesheetDisplayArea() {
		super(17,20);
		setFont(FontList.normalFont);
		setEditable(true);
		//add(scrollPane);
		
		logger.debug("TimesheetdisplayArea initialised.");
	}

}
