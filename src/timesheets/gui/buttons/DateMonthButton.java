package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.Employee;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class DateMonthButton extends JRadioButton{

	private static final long serialVersionUID = 960145533543341180L;

	public DateMonthButton() {
		super("Current Month", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					monthLabel.setEnabled(true);

					for (Employee emp : EmployeeList.values()) {
						dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
						if (emp.getWorkedTime() != null) {
							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
								if (entry.getKey().getMonth() == time.getCurrentDate().getMonth()
										&& entry.getKey().getYear() == time.getCurrentDate().getYear()) {
									dateDisplay.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
											+ entry.getValue()[3] + "\n");
								} else {
									continue;
								}
							}
						} else {
							dateDisplay.append("----------------------------------------------------\n");
							continue;
						}
						dateDisplay.append("----------------------------------------------------\n");
					}
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					monthLabel.setEnabled(false);
					dateDisplay.setText("");
				}
				//pack();
			}
		});
	}

}
