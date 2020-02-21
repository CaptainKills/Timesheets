package timesheets.gui.buttons;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JTextField;

import timesheets.gui.lists.*;

public class RemoveNumberButton extends JButton{

	public RemoveNumberButton() {
		super("<<");
		setPreferredSize(DimensionList.buttonSize_numbers);
		setFont(FontList.buttonFont);
		addActionListener(new ActionListener() {
			JTextField field = TextFieldList.inputField;
			@Override
			public void actionPerformed(ActionEvent event) {
				if (field.getText().length() > 0) {
					field.setText(field.getText().substring(0, field.getText().length() - 1));
				}
				//pack();
			}
		});
	}
	
}
