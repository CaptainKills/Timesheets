package timesheets.gui.labels;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class AgeSalaryLabel extends JLabel{
	private static final long serialVersionUID = -8131209570006669940L;

	public AgeSalaryLabel() {
		super("Age | Salary");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		//setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

}
