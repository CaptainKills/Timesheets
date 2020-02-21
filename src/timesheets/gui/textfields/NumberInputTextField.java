package timesheets.gui.textfields;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.FontList;

public class NumberInputTextField extends JTextField{
	private static final long serialVersionUID = 6958032769304114606L;

	public NumberInputTextField() {
		super("");
		setPreferredSize(new Dimension(460, 61));
		setFont(FontList.textfieldFont);
		setEditable(false);
		setBackground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

}
