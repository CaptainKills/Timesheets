package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class SwitchMenuButton extends JButton{
	private static final long serialVersionUID = 4691645706084905079L;
	private static final Logger logger = new Logger(SwitchMenuButton.class);

	public SwitchMenuButton() {
		super(">>");
		setPreferredSize(DimensionList.buttonSize_small);
		setFont(FontList.buttonFont);
		setEnabled(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				if (getText().equals(">>")) {
					ExtendedHandler.displayAdminPanels(false, true, false, false);
					ExtendedHandler.displayTimeButtons(false);
					ExtendedHandler.displayAdminButtons(true);
					
					setText("<<");
				} else if (getText().equals("<<")) {
					ExtendedHandler.displayAdminPanels(true, false, false, false);
					ExtendedHandler.displayTimeButtons(true);
					ExtendedHandler.displayAdminButtons(false);
					
					setText(">>");
				}
			}
		});
		
		logger.debug("SwitchMenuButton initialised.");
	}
}
