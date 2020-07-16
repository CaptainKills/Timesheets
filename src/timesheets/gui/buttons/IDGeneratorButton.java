package timesheets.gui.buttons;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.DataHandler;
import timesheets.gui.lists.TextFieldList;

public class IDGeneratorButton extends JButton{
	private static final long serialVersionUID = -3233791224942503549L;
	
	private DataHandler data = new DataHandler();

	public IDGeneratorButton() {
		super("#");
		setPreferredSize(new Dimension(48, 30));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setEnabled(true);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String textID = String.format("%05d", data.generateNewID());
				TextFieldList.idField.setText(textID);
				//pack();
			}
		});
	}

}
