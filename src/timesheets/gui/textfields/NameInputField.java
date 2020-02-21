package timesheets.gui.textfields;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class NameInputField extends JTextField{
	private static final long serialVersionUID = -9125512445184520959L;

	public NameInputField() {
		super("");
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.normalFont);
		setEditable(true);
		setHorizontalAlignment(SwingConstants.CENTER);
	}

}
