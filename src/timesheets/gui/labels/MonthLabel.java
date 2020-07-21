package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.TimeHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class MonthLabel extends JLabel{

	private static final long serialVersionUID = -6481359066540061446L;
	private static final Logger logger = new Logger(MonthLabel.class.toString());
	private static TimeHandler time = new TimeHandler();
	
	private static String month = time.getCurrentDate().getMonth().toString();

	public MonthLabel() {
		super(month);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("MonthLabel initialised.");
	}

}
