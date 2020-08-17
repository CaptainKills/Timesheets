package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.DisplayList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class DateWeekButton extends JRadioButton {

	private static final long serialVersionUID = -2102233715141535919L;
	private static final Logger logger = new Logger(DateWeekButton.class.toString());
	private static TimeHandler time = new TimeHandler();
	
	private static JTextPane display = DisplayList.timesheetDisplay;

	public DateWeekButton() {
		super("Current Week", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.weekLabel.setEnabled(true);

					for (Employee emp : Database.EmployeeList.values()) {
						display.append(emp.getName() + " (" + emp.getID() + ")\n");
						if (emp.getWorkedTime() != null) {
							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
								if (entry.getKey().isAfter(time.getWeekStart().minusDays(1))
										&& entry.getKey().isBefore(time.getWeekEnd().plusDays(1))) {
									display.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
											+ entry.getValue()[3] + "\n");
								} else {
									continue;
								}
							}
						} else {
							display.append("----------------------------------------------------\n");
							continue;
						}
						display.append("----------------------------------------------------\n");
					}
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.weekLabel.setEnabled(false);
					display.setText("");
				}
			}
		});
		
		logger.debug("DateWeekButton initialised.");
	}

}
