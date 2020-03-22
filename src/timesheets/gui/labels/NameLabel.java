package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class NameLabel extends JLabel {
	private static final long serialVersionUID = 6515486293672131894L;

	public NameLabel() {
		super("Name");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

}
