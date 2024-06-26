package nl.triantis.timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;

public class NameInputField extends JTextField {
	private static final long serialVersionUID = -9125512445184520959L;
	private static final Logger logger = new Logger(NameInputField.class);

	public NameInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		logger.debug("NameInputField initialised.");
	}

}
