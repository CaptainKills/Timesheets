package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class DeleteLogRadioButton extends JRadioButton{
	private static final long serialVersionUID = 8835755808458717978L;
	private static final Logger logger = new Logger(DeleteLogRadioButton.class.toString());

	public DeleteLogRadioButton(String text, Boolean onoff) {
		super(text, onoff);
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if(text.equals("Yes")) {
					TextFieldList.logCountInput.setEditable(true);
				} else if(text.equals("No")) {
					TextFieldList.logCountInput.setEditable(false);
				}
			}
		});
		
		logger.debug("DeleteLogRadioButton initialised.");
	}

}