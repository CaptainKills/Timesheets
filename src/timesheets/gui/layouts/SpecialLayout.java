package timesheets.gui.layouts;

import java.awt.FlowLayout;

import timesheets.logging.Logger;

public class SpecialLayout  extends FlowLayout{
	private static final long serialVersionUID = 8029710491737110123L;
	private static final Logger logger = new Logger(SpecialLayout.class);
	
	private static int horGap = 10;
	private static int verGap = 20;

	public SpecialLayout() {
		super(FlowLayout.CENTER, horGap, verGap);
		logger.debug("SpecialLayout intialised.");
	}

}
