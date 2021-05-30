package timesheets.report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import timesheets.logging.Logger;

public class StyleFormatter {
	private static final Logger logger = new Logger(StyleFormatter.class);
	
	public static void initFile(File style_file) {
		try (FileWriter file_writer = new FileWriter(style_file, false);
				PrintWriter report_writer = new PrintWriter(file_writer)) {
			logger.debug("Writing to CSS file.");
			
			String css_text = buildCSS();
			report_writer.print(css_text);
			logger.debug("Fnished writing to CSS file.");
			
		} catch (IOException e) {
			logger.error("COULD NOT WRITE TO CSS FILE!", e);
		}
	}
	
	private static String buildCSS() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("html, body {\n");
		builder.append("\tmargin-top: 1%;\n");
		builder.append("\tmargin-left: 1%;\n");
		builder.append("\tbackground-color: #ffffff;\n");
		builder.append("}\n");
		
		builder.append("table, th, td {\n");
		builder.append("\tborder: 1px solid black;\n");
		builder.append("\tborder-collapse: collapse;\n");
		builder.append("\tbackground-color: #ececec;\n");
		builder.append("\ttext-align: center;\n");
		builder.append("\tfont-family: Arial, Helvetica, sans-serif;\n");
		builder.append("}\n");
		
		builder.append("caption {\n");
		builder.append("\ttext-align: left;\n");
		builder.append("\tfont-size: 20px;\n");
		builder.append("\tfont-family: Arial, Helvetica, sans-serif;\n");
		builder.append("}\n");
		
		return builder.toString();
	}

}
