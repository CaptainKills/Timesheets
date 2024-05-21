package nl.triantis.timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class EmployeeLabel extends JLabel {
	private static final long serialVersionUID = 570599185293754786L;
	private static final Logger logger = new Logger(EmployeeLabel.class);
	
	private static String labelText = LanguageManager.language.get("employee_label");

	public EmployeeLabel() {
		super(labelText);
		setPreferredSize(DimensionList.labelSize);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		

		logger.debug("EmployeeLabel initialised.");
	}

}
