package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class RemoveNumberButton extends JButton{
	private static final long serialVersionUID = -6376697726067622946L;
	private static final Logger logger = new Logger(RemoveNumberButton.class.toString());

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
		
		logger.debug("RemoveNumberButton initialised.");
	}
	
}
