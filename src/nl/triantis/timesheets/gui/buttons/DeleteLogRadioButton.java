package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;

public class DeleteLogRadioButton extends JRadioButton {
	private static final long serialVersionUID = 8835755808458717978L;
	private static final Logger logger = new Logger(DeleteLogRadioButton.class);

	public DeleteLogRadioButton(String text, Boolean onoff) {
		super(text, onoff);
		setPreferredSize(DimensionList.dateDisplaySize_medium);
		setFont(FontList.normalFont);
		setHorizontalAlignment(SwingConstants.CENTER);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (text.equals("Yes")) {
					TextFieldList.logCountInput.setEnabled(true);
				} else if (text.equals("No")) {
					TextFieldList.logCountInput.setEnabled(false);
				}
			}
		});

		logger.debug("DeleteLogRadioButton initialised.");
	}

}
