package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.resources.LanguageManager;

public class ClearButton extends JButton {
	private static final long serialVersionUID = -6774862779006432309L;
	
	private static String buttonText = LanguageManager.language.get("clear_button");

	public ClearButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_numbers);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.inputField.setText("");
			}
		});
	}

}
