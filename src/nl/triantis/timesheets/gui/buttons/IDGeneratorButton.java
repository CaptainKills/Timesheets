package nl.triantis.timesheets.gui.buttons;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import nl.triantis.timesheets.gui.lists.TextFieldList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.sql.Database;

public class IDGeneratorButton extends JButton {
	private static final long serialVersionUID = -3233791224942503549L;
	private static final Logger logger = new Logger(IDGeneratorButton.class);

	public IDGeneratorButton() {
		super("#");
		setPreferredSize(new Dimension(48, 30));
		setFont(new Font("Arial", Font.PLAIN, 14));
		setEnabled(true);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String textID = String.format("%05d", generateNewID());
				TextFieldList.idField.setText(textID);
			}
		});

		logger.debug("IDGeneratorButton initialised.");
	}

	private int generateNewID() {
		int newID = new Random().nextInt(100000); // Generate number from 00000 to 99999 - a five digit ID

		if (isIdUsed(newID)) {
			return generateNewID();
		} else {
			logger.debug("Generated new ID: " + newID);
			return newID;
		}
	}

	private Boolean isIdUsed(int id) {
		if (Database.EmployeeList.get(id) != null) {
			return true;
		} else {
			return false;
		}
	}

}
