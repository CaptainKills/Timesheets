package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.logging.Logger;

public class DateWeekButton extends JRadioButton {

	private static final long serialVersionUID = -2102233715141535919L;
	private static final Logger logger = new Logger(DateWeekButton.class);
	

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
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.weekLabel.setEnabled(false);
				}
			}
		});

		logger.debug("DateWeekButton initialised.");
	}

}
