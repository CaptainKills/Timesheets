package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.report.ReportFormatter;
import timesheets.report.ReportFormatter.OutputType;
import timesheets.report.Reporter;

public class PrintReportButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(PrintReportButton.class);

	public PrintReportButton() {
		super("Print Report");
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				String report_text = "";
				OutputType type;
				
				if(ButtonList.dateTodayButton.isSelected()) {
					type = OutputType.TODAY;
					report_text = ReportFormatter.build(OutputType.TODAY);
				} else if(ButtonList.dateWeekButton.isSelected()) {
					type = OutputType.WEEK;
					report_text = ReportFormatter.build(OutputType.WEEK);
				} else if(ButtonList.dateMonthButton.isSelected()) {
					type = OutputType.MONTH;
					report_text = ReportFormatter.build(OutputType.MONTH);
				} else if(ButtonList.dateSpecificButton.isSelected()) {
					type = OutputType.SPECIFIC;
					LocalDate beginDate = (LocalDate) TextFieldList.startingDateInput.getValue();
					LocalDate endDate = (LocalDate) TextFieldList.endingDateInput.getValue();
					report_text = ReportFormatter.build(beginDate, endDate);
				} else {
					JOptionPane.showMessageDialog(PanelList.mainPanel, "Please select the type of output before creating a report!",
							"No Setting Selected!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Reporter.createReport(report_text, type);
				JOptionPane.showMessageDialog(PanelList.mainPanel, "Report has been successfully created!",
						"Successful Report!", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		logger.debug("SubmitDateButton initialised.");
	}

}
