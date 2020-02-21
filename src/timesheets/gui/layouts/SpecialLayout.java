package timesheets.gui.layouts;

import java.awt.FlowLayout;

public class SpecialLayout  extends FlowLayout{
	private static final long serialVersionUID = 8029710491737110123L;
	
	private static int horGap = 10;
	private static int verGap = 20;

	public SpecialLayout() {
		super(FlowLayout.CENTER, horGap, verGap);
	}

}
