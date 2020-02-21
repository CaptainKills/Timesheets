package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;

public class NumberButton extends JButton{
	private static final long serialVersionUID = -4962990327179565242L;

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
