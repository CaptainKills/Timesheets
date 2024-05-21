package nl.triantis.timesheets.gui.textfields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class IDInputField extends JTextField {
	private static final long serialVersionUID = -7437435542193984592L;
	private static final Logger logger = new Logger(IDInputField.class);

	public IDInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (getText().length() >= 5) {
					e.consume();
				}
			}
		});

		logger.debug("IDInputField initialised.");
	}

}
