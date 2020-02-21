package timesheets.gui.labels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class AdminLabel extends JLabel{
	private static final long serialVersionUID = -7230745815968369475L;

	public AdminLabel() {
		super("Admin");
		setPreferredSize(DimensionList.labelSize_small);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(FontList.labelFont);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

}
