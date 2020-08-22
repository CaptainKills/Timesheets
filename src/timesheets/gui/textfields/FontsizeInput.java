package timesheets.gui.textfields;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import timesheets.Settings;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class FontsizeInput extends JSpinner{
	private static final long serialVersionUID = 3754150334860596593L;
	private static final Logger logger = new Logger(FontsizeInput.class);
	
	private int current = Integer.parseInt(Settings.settings.get("fontsize"));
	private int min = 1;
	private int max = 50;
	private int step = 1;
	
	public FontsizeInput() {
		setPreferredSize(DimensionList.dateDisplaySize_small);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		
		SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
		setModel(model);
		
		logger.debug("FontsizeInput initialised.");
	}

}
