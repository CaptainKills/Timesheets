package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class ReportTypeLabel extends JLabel {
	
	private static final long serialVersionUID = -2439843467358648885L;
	private static final Logger logger = new Logger(ReportTypeLabel.class);
	
	private static String labelText = LanguageManager.language.get("report_type_label");

	public ReportTypeLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("ReportTypeLabel initialised.");
	}

}
