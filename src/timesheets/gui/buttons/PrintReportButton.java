package timesheets.gui.buttons;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import timesheets.report.ReportManager;
import timesheets.report.Reporter;
import timesheets.resources.LanguageManager;
import timesheets.resources.ResourceHandler;

public class PrintReportButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(PrintReportButton.class);
	
	private static String buttonText = LanguageManager.language.get("print_report_button");

	public PrintReportButton() {
		super(buttonText);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		
		Object[] options = {"Ok", "Open Report"};

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
				
				int status = JOptionPane.showOptionDialog(PanelList.mainPanel, "Report has been successfully created!", "Success Report!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				if(status == JOptionPane.NO_OPTION) {
					logger.info("Opening report with desktop.");
					
					Path report_directory = ResourceHandler.report_directory_path;
					String report_name = ReportManager.getReportName();
					Path report_path = Paths.get(report_directory + File.separator + report_name).toAbsolutePath();
					File report_file = report_path.toFile();
					
					try {
						Desktop.getDesktop().open(report_file);
					} catch(IOException e) {
						logger.error("COULD NOT OPEN FILE WITH DESKTOP!", e);
					}
				}
			}
		});

		logger.debug("SubmitDateButton initialised.");
	}

}
