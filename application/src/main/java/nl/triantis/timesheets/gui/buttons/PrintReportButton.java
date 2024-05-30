package nl.triantis.timesheets.gui.buttons;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.other.CustomOptionPane;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.report.ReportOutputType;
import nl.triantis.timesheets.report.excel.ExcelReporter;
import nl.triantis.timesheets.report.html.HTMLReporter;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.resources.ResourceHandler;

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
				ReportOutputType type;
				
				if(ButtonList.dateTodayButton.isSelected()) {
					type = ReportOutputType.TODAY;
				} else if(ButtonList.dateWeekButton.isSelected()) {
					type = ReportOutputType.WEEK;
				} else if(ButtonList.dateMonthButton.isSelected()) {
					type = ReportOutputType.MONTH;
				} else if(ButtonList.dateSpecificButton.isSelected()) {
					type = ReportOutputType.SPECIFIC;
				} else {
					CustomOptionPane cop = new CustomOptionPane("Report Type Selection Fail");
					cop.setText(dialogTitleFail, dialogMsgFail);
					cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
					cop.showDialog();
					
					return;
				}
				
				String fileName = "";
				if(ButtonList.excelReportButton.isSelected()) {
					fileName = ExcelReporter.createReport(type);
				} else if(ButtonList.htmlReportButton.isSelected()) {
					fileName = HTMLReporter.createReport(type);
				}
				
				int status = showSuccessDialog();
				if(status == JOptionPane.NO_OPTION) {
					openReport(fileName);
				}
			}
		});

		logger.debug("SubmitDateButton initialised.");
	}
	
	private void openReport(String reportName) {
		logger.info("Opening report with desktop.");
		
		Path reportDirectory = ResourceHandler.report_directory_path;
		Path reportPath = Paths.get(reportDirectory + File.separator + reportName).toAbsolutePath();
		File reportFile = reportPath.toFile();
		
		try {
			Desktop.getDesktop().open(reportFile);
		} catch(Exception e) {
			logger.error("COULD NOT OPEN FILE WITH DESKTOP!", e);
		}
	}
	
	private int showSuccessDialog() {
		CustomOptionPane cop = new CustomOptionPane("Report Success");
		cop.setText(dialogTitleSuccess, dialogMsgSuccess);
		cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
		cop.setButtons(buttonOptions);
		
		return cop.showDialog();
	}

}
