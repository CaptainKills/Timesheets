package timesheets.gui.labels;

import java.awt.Color;

import javax.swing.*;
import timesheets.gui.lists.*;

public class AgeSalaryLabel extends JLabel{

	public AgeSalaryLabel() {
		super("Age | Salary");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

}
