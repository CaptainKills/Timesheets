package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class DateSpecificButton extends JRadioButton {

	private static final long serialVersionUID = 8877741355348581691L;
	private static final Logger logger = new Logger(DateSpecificButton.class);
	
	private static String buttonText = LanguageManager.language.get("date_specific_button");

	public DateSpecificButton() {
		super(buttonText, false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.fromDateLabel.setEnabled(true);
					TextFieldList.startingDateInput.setEnabled(true);

					LabelList.toDateLabel.setEnabled(true);
					TextFieldList.endingDateInput.setEnabled(true);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.fromDateLabel.setEnabled(false);
					TextFieldList.startingDateInput.setEnabled(false);

					LabelList.toDateLabel.setEnabled(false);
					TextFieldList.endingDateInput.setEnabled(false);
				}
			}
		});

		logger.debug("DateSpecificButton initialised.");
	}

}
