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

public class DateTodayButton extends JRadioButton{
	private static final long serialVersionUID = 4966799733565135084L;
	private static final Logger logger = new Logger(DateTodayButton.class);
	
	private static JTextPane display = DisplayList.timesheetDisplay;

	public DateTodayButton() {
		super("Date Today", false);
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setHorizontalAlignment(SwingConstants.LEFT);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					LabelList.todayLabel.setEnabled(true);

					String display_text = DisplayOutput.build(OutputType.TODAY);
					display.setText(display_text);
				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
					LabelList.todayLabel.setEnabled(false);
					display.setText("");
				}
			}
		});
		
		logger.debug("DateTodayButton initialised.");
	}

}
