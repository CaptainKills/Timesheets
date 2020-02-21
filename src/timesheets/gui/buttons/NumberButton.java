package timesheets.gui.buttons;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;

import timesheets.gui.lists.*;

public class NumberButton extends JButton{

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
				//pack();
			}
		});
	}
	
}
