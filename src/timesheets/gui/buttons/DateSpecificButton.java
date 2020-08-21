package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.TimeHandler;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class DateSpecificButton extends JRadioButton {

	private static final long serialVersionUID = 8877741355348581691L;
	private static final Logger logger = new Logger(DateSpecificButton.class);
	private static TimeHandler time = new TimeHandler();
	

	public DateSpecificButton() {
		super("Specific Date", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.fromDateLabel.setEnabled(true);
					TextFieldList.yearInput_from.setEnabled(true);
					TextFieldList.yearInput_from.setText("yyyy");
					TextFieldList.monthInput_from.setEnabled(true);
					TextFieldList.monthInput_from.setText("mm");
					TextFieldList.dayInput_from.setEnabled(true);
					TextFieldList.dayInput_from.setText("dd");

					LabelList.toDateLabel.setEnabled(true);
					TextFieldList.yearInput_to.setEnabled(true);
					TextFieldList.yearInput_to.setText(Integer.toString(time.getCurrentDate().getYear()));
					TextFieldList.monthInput_to.setEnabled(true);
					TextFieldList.monthInput_to.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
					TextFieldList.dayInput_to.setEnabled(true);
					TextFieldList.dayInput_to.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
					
					ButtonList.submitDateButton.setEnabled(true);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.fromDateLabel.setEnabled(false);
					TextFieldList.yearInput_from.setEnabled(false);
					TextFieldList.yearInput_from.setText("yyyy");
					TextFieldList.monthInput_from.setEnabled(false);
					TextFieldList.monthInput_from.setText("mm");
					TextFieldList.dayInput_from.setEnabled(false);
					TextFieldList.dayInput_from.setText("dd");

					LabelList.toDateLabel.setEnabled(false);
					TextFieldList.yearInput_to.setEnabled(false);
					TextFieldList.yearInput_to.setText(Integer.toString(time.getCurrentDate().getYear()));
					TextFieldList.monthInput_to.setEnabled(false);
					TextFieldList.monthInput_to.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
					TextFieldList.dayInput_to.setEnabled(false);
					TextFieldList.dayInput_to.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
					
					ButtonList.submitDateButton.setEnabled(false);
					DisplayList.timesheetDisplay.setText("");
				}
			}
		});
		
		logger.debug("DateSpecificButton initialised.");
	}

}
