package timesheets.gui.buttons;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JButton;

import timesheets.gui.lists.*;

public class ClearButton extends JButton{

	public ClearButton() {
		super("Clear");
		setPreferredSize(DimensionList.buttonSize_numbers);
		setFont(FontList.buttonFont);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
				//pack();
			}
		});
	}

}
