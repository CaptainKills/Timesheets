package nl.triantis.timesheets.gui.buttons.groups;

import javax.swing.ButtonGroup;

import nl.triantis.timesheets.gui.lists.ButtonList;
import nl.triantis.timesheets.logging.Logger;

public class ReportSelectionGroup extends ButtonGroup {
	
	private static final long serialVersionUID = 6801051377490951601L;
	private static final Logger logger = new Logger(ReportSelectionGroup.class);

	public ReportSelectionGroup() {
		add(ButtonList.excelReportButton);
		add(ButtonList.htmlReportButton);

		logger.debug("ReportSelectionGroup initialised.");
	}

}
