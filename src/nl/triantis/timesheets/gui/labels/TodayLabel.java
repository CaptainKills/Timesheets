package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.TimeHandler;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class TodayLabel extends JLabel {

	private static final long serialVersionUID = 53890497082589527L;
	private static final Logger logger = new Logger(TodayLabel.class);
	private static TimeHandler time = new TimeHandler();

	private static String today = time.getCurrentDate().getDayOfWeek().toString();

	public TodayLabel() {
		super(today);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		setHorizontalAlignment(SwingConstants.CENTER);

		logger.debug("TodayLabel initialised.");
	}

	public void updateText() {
		setText(time.getCurrentDate().getDayOfWeek().toString());
	}

}
