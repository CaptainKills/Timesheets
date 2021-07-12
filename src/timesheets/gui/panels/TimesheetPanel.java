package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class TimesheetPanel extends JPanel {

	private static final long serialVersionUID = -6585500139170806555L;
	private static final Logger logger = new Logger(TimesheetPanel.class);

	public TimesheetPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);
		setBackground(Color.WHITE);
		setVisible(false);

		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// @formatter:off		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.todayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.weekLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(ButtonList.dateMonthButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.monthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)						
						
						.addComponent(ButtonList.dateSpecificButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(LabelList.fromDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.startingDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createSequentialGroup()
								.addComponent(LabelList.toDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.endingDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addComponent(ButtonList.printReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.todayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.weekLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.dateMonthButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.monthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				
				.addComponent(ButtonList.dateSpecificButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.fromDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.startingDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(LabelList.toDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(TextFieldList.endingDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				
				.addComponent(ButtonList.printReportButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				);
		// @formatter:on
		setLayout(layout);

		logger.debug("TimesheetPanel initialised.");
	}

}
