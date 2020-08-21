package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class HeightInput extends JTextField {
	private static final long serialVersionUID = -387410479178733551L;
	private static final Logger logger = new Logger(HeightInput.class);

	public HeightInput() {
		super("", 4);
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		logger.debug("HeightInput initialised.");
	}

}
