package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.TimeHandler;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class WeekLabel extends JLabel {

	private static final long serialVersionUID = -6481359066540061446L;
	private static final Logger logger = new Logger(WeekLabel.class);
	private static TimeHandler time = new TimeHandler();

	private static String week = time.getWeekStart().toString();

	public WeekLabel() {
		super(week);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		setHorizontalAlignment(SwingConstants.CENTER);

		logger.debug("WeekLabel initialised.");
	}

	public void updateText() {
		setText(time.getWeekStart().toString());
	}

}
