package timesheets.gui.layouts;

import java.awt.FlowLayout;

public class SpecialLayout  extends FlowLayout{
	private static int horGap = 10;
	private static int verGap = 20;

	public SpecialLayout() {
		super(FlowLayout.CENTER, horGap, verGap);
	}

}
