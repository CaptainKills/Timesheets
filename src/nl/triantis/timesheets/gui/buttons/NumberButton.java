package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;

public class NumberButton extends JButton {
	private static final long serialVersionUID = -4962990327179565242L;
	private static final Logger logger = new Logger(NumberButton.class);

	public NumberButton(String number) {
		super(number);
		setPreferredSize(DimensionList.buttonSize_numbers);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			JTextField field = TextFieldList.inputField;

			@Override
			public void actionPerformed(ActionEvent event) {
				if (field.getText().length() < 5) {
					field.setText(field.getText() + number);
				}
			}
		});

		logger.debug("NumberButton " + this.getText() + " initialised.");
	}
}
