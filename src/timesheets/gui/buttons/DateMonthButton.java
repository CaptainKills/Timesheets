package timesheets.gui.buttons;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.textareas.DisplayOutput;
import timesheets.gui.textareas.DisplayOutput.OutputType;
import timesheets.logging.Logger;

public class DateMonthButton extends JRadioButton{

	private static final long serialVersionUID = 960145533543341180L;
	private static final Logger logger = new Logger(DateMonthButton.class.toString());
	
	private static JTextPane display = DisplayList.timesheetDisplay;

	public DateMonthButton() {
		super("Current Month", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.monthLabel.setEnabled(true);

					String display_text = DisplayOutput.build(OutputType.MONTH);
					display.setText(display_text);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.monthLabel.setEnabled(false);
					display.setText("");
				}
			}
		});
		
		logger.debug("DateMonthButton initialised.");
	}

}
