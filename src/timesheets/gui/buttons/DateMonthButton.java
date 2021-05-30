package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.logging.Logger;
import timesheets.report.ReportFormatter;
import timesheets.report.ReportFormatter.OutputType;
import timesheets.report.Reporter;

public class DateMonthButton extends JRadioButton {

	private static final long serialVersionUID = 960145533543341180L;
	private static final Logger logger = new Logger(DateMonthButton.class);


	public DateMonthButton() {
		super("Current Month", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.monthLabel.setEnabled(true);

					String display_text = ReportFormatter.build(OutputType.MONTH);
					Reporter.createReport(display_text);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.monthLabel.setEnabled(false);
				}
			}
		});

		logger.debug("DateMonthButton initialised.");
	}

}
