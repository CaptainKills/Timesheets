package timesheets.gui.textfields;

import java.awt.Color;
import java.awt.Dimension;
import timesheets.gui.lists.*;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NumberInputTextField extends JTextField{

	public NumberInputTextField() {
		super("");
		setPreferredSize(new Dimension(460, 61));
		setFont(FontList.textfieldFont);
		setEditable(false);
		setBackground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

}
