package timesheets.gui.buttons;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import timesheets.DataHandler;
import timesheets.gui.lists.*;

public class IDGeneratorButton extends JButton{
	private DataHandler data = new DataHandler();

	public IDGeneratorButton() {
		super("#");
		setPreferredSize(new Dimension(48, 30));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setEnabled(true);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				TextFieldList.idField.setText(Integer.toString(data.generateNewID()));
				//pack();
			}
		});
	}

}
