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

public class DateTodayButton extends JRadioButton{
	private static final long serialVersionUID = 4966799733565135084L;
	private static final Logger logger = new Logger(DateTodayButton.class.toString());
	private static TimeHandler time = new TimeHandler();
	
	private static JTextPane display = DisplayList.timesheetDisplay;
	private StringBuilder builder = new StringBuilder();

	public DateTodayButton() {
		super("Date Today", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.todayLabel.setEnabled(true);

					for (Employee emp : Database.EmployeeList.values()) {
						builder.append(emp.getName() + " (" + emp.getID() + ")\n");
						if (emp.getWorkedTime() != null) {
							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
								if (entry.getKey().isEqual(time.getCurrentDate())) {
									builder.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
											+ entry.getValue()[3] + "\n");
								} else {
									continue;
								}
							}
						} else {
							builder.append("----------------------------------------------------\n");
							continue;
						}
						builder.append("----------------------------------------------------\n");
						display.setText(builder.toString());
					}
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.todayLabel.setEnabled(false);
					display.setText("");
				}
			}
		});
		
		logger.debug("DateTodayButton initialised.");
	}

}
