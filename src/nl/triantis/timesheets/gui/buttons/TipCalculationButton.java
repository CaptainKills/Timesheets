package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import nl.triantis.timesheets.Employee;
import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.other.CustomOptionPane;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.sql.Database;

public class TipCalculationButton extends JButton {
	private static final long serialVersionUID = -3559866133745077141L;
	private static final Logger logger = new Logger(TipCalculationButton.class);
	
	private double tipAmount = 26;
	
	public TipCalculationButton() {
		super("Calculate Tips");
		setPreferredSize(DimensionList.buttonSize_menu);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.debug("Button pressed.");
				
				LocalDate date = LocalDate.now();
				double totalWorkedTime = 0;
				Employee[] participatingEmployees = {};
				int numberOfParticipatingEmployees = 0;
				
				for(Employee emp : Database.EmployeeList.values()) {
					if(!emp.getWorkedTime().containsKey(date)) {
						continue;
					}
					
					if(date.equals(LocalDate.now()) && emp.getTimeStarted() != null) {
						logger.warn("An employee has not finished working! Skipping tip calculation.");
						returnError();
						
						return;
					}
					
					LocalTime[] shift = emp.getWorkedTime().get(date);						
					totalWorkedTime += shift[3].getHour() + ((double) shift[3].getMinute() / 60);
					participatingEmployees[numberOfParticipatingEmployees] = emp;
					numberOfParticipatingEmployees++;
				}
				
				
				if(totalWorkedTime == 0) {
					returnError();
					return;
				}
				
				double rate = tipAmount / totalWorkedTime;
				
				logger.debug("Total worked time: " + totalWorkedTime + ", by " + participatingEmployees + " employees.");
				logger.debug(String.format("Hourly rate: €%.2f", rate));
				
				for(Employee emp : participatingEmployees) {
					LocalTime[] shift = emp.getWorkedTime().get(date);
					
					double workedTime = shift[3].getHour() + ((double) shift[3].getMinute() / 60);
					double tip = workedTime * rate;
					
					logger.debug(String.format(emp.getName() + ": €%.2f", tip));
				}
				
			}
		});
	}
	
	private void returnError() {
		CustomOptionPane cop = new CustomOptionPane("Tip Calculation");
		cop.setText("Tip Error!", "Something went wrong with calculating the tip.");
		cop.setConfig(JOptionPane.ERROR_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
		cop.showDialog();
	}

}
