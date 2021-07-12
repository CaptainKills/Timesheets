package timesheets.gui.buttons;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.other.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.report.html.HTMLFormatter;
import timesheets.report.html.HTMLFormatter.OutputType;
import timesheets.report.html.ReportManager;
import timesheets.report.html.Reporter;
import timesheets.resources.LanguageManager;
import timesheets.resources.ResourceHandler;

public class PrintReportButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(PrintReportButton.class);
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String buttonText = lang.get("print_report_button");
	
	private String dialogTitleFail = lang.get("jop_prb_title_fail");
	private String dialogMsgFail = lang.get("jop_prb_msg_fail");
	private String dialogTitleSuccess = lang.get("jop_prb_title_success");
	private String dialogMsgSuccess = lang.get("jop_prb_msg_success");
	private Object[] buttonOptions = {lang.get("jop_prb_option_oke"), lang.get("jop_prb_option_open")};

	public PrintReportButton() {
		super(buttonText);
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
					report_text = HTMLFormatter.build(OutputType.TODAY);
				} else if(ButtonList.dateWeekButton.isSelected()) {
					type = OutputType.WEEK;
					report_text = HTMLFormatter.build(OutputType.WEEK);
				} else if(ButtonList.dateMonthButton.isSelected()) {
					type = OutputType.MONTH;
					report_text = HTMLFormatter.build(OutputType.MONTH);
				} else if(ButtonList.dateSpecificButton.isSelected()) {
					type = OutputType.SPECIFIC;
					LocalDate beginDate = (LocalDate) TextFieldList.startingDateInput.getValue();
					LocalDate endDate = (LocalDate) TextFieldList.endingDateInput.getValue();
					report_text = HTMLFormatter.build(beginDate, endDate);
				} else {
					CustomOptionPane cop = new CustomOptionPane("HTML Report Fail");
					cop.setText(dialogTitleFail, dialogMsgFail);
					cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
					cop.showDialog();
					
					return;
				}
				
				Reporter.createReport(report_text, type);
				
				CustomOptionPane cop = new CustomOptionPane("HTML Report Success");
				cop.setText(dialogTitleSuccess, dialogMsgSuccess);
				cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
				cop.setButtons(buttonOptions);
				
				int status = cop.showDialog();
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
