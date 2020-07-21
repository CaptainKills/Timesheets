package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class DateSpecificButton extends JRadioButton {

	private static final long serialVersionUID = 8877741355348581691L;
	private static final Logger logger = new Logger(DateSpecificButton.class.toString());

	public DateSpecificButton() {
		super("Specific Date", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					fromLabel.setEnabled(true);
					fromYearInput.setEnabled(true);
					fromYearInput.setText("yyyy");
					fromMonthInput.setEnabled(true);
					fromMonthInput.setText("mm");
					fromDayInput.setEnabled(true);
					fromDayInput.setText("dd");

					toLabel.setEnabled(true);
					toYearInput.setEnabled(true);
					toYearInput.setText(Integer.toString(time.getCurrentDate().getYear()));
					toMonthInput.setEnabled(true);
					toMonthInput.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
					toDayInput.setEnabled(true);
					toDayInput.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
					submitDateButton.setEnabled(true);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					fromLabel.setEnabled(false);
					fromYearInput.setEnabled(false);
					fromYearInput.setText("yyyy");
					fromMonthInput.setEnabled(false);
					fromMonthInput.setText("mm");
					fromDayInput.setEnabled(false);
					fromDayInput.setText("dd");

					toLabel.setEnabled(false);
					toYearInput.setEnabled(false);
					toYearInput.setText(Integer.toString(time.getCurrentDate().getYear()));
					toMonthInput.setEnabled(false);
					toMonthInput.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
					toDayInput.setEnabled(false);
					toDayInput.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
					submitDateButton.setEnabled(false);
					dateDisplay.setText("");
				}
				//pack();
			}
		});
		
		logger.debug("DateSpecificButton initialised.");
	}

}
