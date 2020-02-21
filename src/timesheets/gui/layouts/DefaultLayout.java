package timesheets.gui.layouts;

import java.awt.FlowLayout;

public class DefaultLayout extends FlowLayout{
	private static int horGap = 5;
	private static int verGap = 5;

	public DefaultLayout() {
		super(FlowLayout.CENTER, horGap, verGap);
	}

}
