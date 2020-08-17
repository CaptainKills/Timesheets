package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import timesheets.Employee;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextAreaList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class SubmitDateButton extends JButton {

	private static final long serialVersionUID = 2083380215334310336L;
	private static final Logger logger = new Logger(SubmitDateButton.class.toString());
	
	private static JTextPane display = TextAreaList.timesheetDisplay;
	private static JTextField yearInput_from = TextFieldList.yearInput_from;
	private static JTextField yearInput_to = TextFieldList.yearInput_from;
	private static JTextField monthInput_from = TextFieldList.monthInput_from;
	private static JTextField monthInput_to = TextFieldList.monthInput_to;
	private static JTextField dayInput_from = TextFieldList.dayInput_from;
	private static JTextField dayInput_to = TextFieldList.dayInput_to;
	
	private LocalDate beginDate;
	private LocalDate endDate;

	public SubmitDateButton() {
		super("Submit Date");
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					beginDate = LocalDate.of(Integer.parseInt(yearInput_from.getText()),
							Integer.parseInt(monthInput_from.getText()), Integer.parseInt(dayInput_from.getText()));
					endDate = LocalDate.of(Integer.parseInt(yearInput_to.getText()),
							Integer.parseInt(monthInput_to.getText()), Integer.parseInt(dayInput_to.getText()));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(PanelList.mainPanel, "Please enter a correct date!", "Incorrect date",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} finally {
					display.setText("");
				}

				for (Employee emp : Database.EmployeeList.values()) {
					display.append(emp.getName() + " (" + emp.getID() + ")\n");
					if (!emp.getWorkedTime().isEmpty()) {
						for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
							if (entry.getKey().isAfter(beginDate) && entry.getKey().isBefore(endDate)) {
								display.append(
										entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-" + entry.getValue()[1]
												+ ", P-" + entry.getValue()[2] + ", W-" + entry.getValue()[3] + "\n");
							} else {
								display.append("----------------------------------------------------\n");
								continue;
							}
						}
					}
					display.append("----------------------------------------------------\n");
				}
			}
		});
		
		logger.debug("SubmitDateButton initialised.");
	}

}
