package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class IDInputField extends JTextField{
	private static final long serialVersionUID = -7437435542193984592L;
	private static final Logger logger = new Logger(IDInputField.class.toString());

	public IDInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		logger.debug("IDInputField initialised.");
	}

}
