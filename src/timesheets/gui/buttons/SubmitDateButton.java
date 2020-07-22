package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.Employee;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class SubmitDateButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(SubmitDateButton.class.toString());

	public SubmitDateButton() {
		super("Submit Date");
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					beginDate = LocalDate.of(Integer.parseInt(fromYearInput.getText()),
							Integer.parseInt(fromMonthInput.getText()), Integer.parseInt(fromDayInput.getText()));
					endDate = LocalDate.of(Integer.parseInt(toYearInput.getText()),
							Integer.parseInt(toMonthInput.getText()), Integer.parseInt(toDayInput.getText()));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(mainPanel, "Please enter a correct date!", "Incorrect date",
							JOptionPane.INFORMATION_MESSAGE);
					//pack();
					return;
				} finally {
					dateDisplay.setText("");
				}

				for (Employee emp : EmployeeList.values()) {
					dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
					if (!emp.getWorkedTime().isEmpty()) {
						for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
							if (entry.getKey().isAfter(beginDate) && entry.getKey().isBefore(endDate)) {
								dateDisplay.append(
										entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-" + entry.getValue()[1]
												+ ", P-" + entry.getValue()[2] + ", W-" + entry.getValue()[3] + "\n");
							} else {
								dateDisplay.append("----------------------------------------------------\n");
								continue;
							}
						}
					}
					dateDisplay.append("----------------------------------------------------\n");
				}
				//pack();
			}
		});
		
		logger.debug("SubmitDateButton initialised.");
	}

}
