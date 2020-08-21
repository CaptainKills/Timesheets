package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.LayoutList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class TimesheetPanel extends JPanel {

	private static final long serialVersionUID = -6585500139170806555L;
	private static final Logger logger = new Logger(TimesheetPanel.class);

	public TimesheetPanel() {
		setPreferredSize(DimensionList.defaultPanelSize);
		setLayout(LayoutList.defaultLayout);
		setBackground(Color.WHITE);
		setVisible(false);
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.todayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.weekLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateMonthButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.monthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateSpecificButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addComponent(LabelList.fromDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.dayInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.monthInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.yearInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						.addGroup(layout.createSequentialGroup()
								.addComponent(LabelList.toDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.dayInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.monthInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.yearInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						.addComponent(ButtonList.submitDateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addComponent(UnusualsList.scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)	
				);

		layout.setVerticalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(ButtonList.dateTodayButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.todayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateWeekButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.weekLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateMonthButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(LabelList.monthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addComponent(ButtonList.dateSpecificButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.fromDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.dayInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.monthInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.yearInput_from, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.toDateLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.dayInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.monthInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.yearInput_to, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						.addComponent(ButtonList.submitDateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				.addComponent(UnusualsList.scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
				);
		setLayout(layout);
		
		logger.debug("TimesheetPanel initialised.");
	}

}
