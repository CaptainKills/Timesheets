package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.logging.Logger;
import timesheets.report.ReportFormatter;
import timesheets.report.ReportFormatter.OutputType;
import timesheets.report.Reporter;

public class DateWeekButton extends JRadioButton {

	private static final long serialVersionUID = -2102233715141535919L;
	private static final Logger logger = new Logger(DateWeekButton.class);

	private static JTextPane display = DisplayList.timesheetDisplay;

	public DateWeekButton() {
		super("Current Week", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.weekLabel.setEnabled(true);

					String display_text = ReportFormatter.build(OutputType.WEEK);
					display.setText(display_text);
					Reporter.createReport(display_text);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.weekLabel.setEnabled(false);
					display.setText("");
				}
			}
		});

		logger.debug("DateWeekButton initialised.");
	}

}
