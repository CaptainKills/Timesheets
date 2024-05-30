package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.LabelList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;

public class DateMonthButton extends JRadioButton {

	private static final long serialVersionUID = 960145533543341180L;
	private static final Logger logger = new Logger(DateMonthButton.class);

	private static String buttonText = LanguageManager.language.get("date_month_button");

	public DateMonthButton() {
		super(buttonText, false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.monthLabel.setEnabled(true);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.monthLabel.setEnabled(false);
				}
			}
		});

		logger.debug("DateMonthButton initialised.");
	}

}
