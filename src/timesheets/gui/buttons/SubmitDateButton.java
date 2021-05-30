package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.report.ReportFormatter;
import timesheets.report.Reporter;

public class SubmitDateButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(SubmitDateButton.class);

	public SubmitDateButton() {
		super("Submit Date");
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				LocalDate beginDate = (LocalDate) TextFieldList.startingDateInput.getValue();
				LocalDate endDate = (LocalDate) TextFieldList.endingDateInput.getValue();

				String display_text = ReportFormatter.build(beginDate, endDate);
				Reporter.createReport(display_text);
			}
		});

		logger.debug("SubmitDateButton initialised.");
	}

}
