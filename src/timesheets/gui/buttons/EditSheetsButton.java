package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class EditSheetsButton extends JButton {
	private static final long serialVersionUID = 7930622460066225999L;
	private static final Logger logger = new Logger(EditSheetsButton.class);

	public EditSheetsButton() {
		super("Edit Timesheets");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setEnabled(false);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayAdminPanels(false, false, true, false);
			}
		});

		logger.debug("EditSheetsButton initialised.");
	}

}
