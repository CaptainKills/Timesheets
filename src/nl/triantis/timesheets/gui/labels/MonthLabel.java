package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.TimeHandler;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class MonthLabel extends JLabel {

	private static final long serialVersionUID = -6481359066540061446L;
	private static final Logger logger = new Logger(MonthLabel.class);
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

	public void updateText() {
		setText(time.getCurrentDate().getMonth().toString());
	}

}
