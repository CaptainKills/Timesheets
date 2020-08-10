package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextAreaList;
import timesheets.logging.Logger;

public class PrintSheetsButton extends JButton{
	private static final long serialVersionUID = 2103797465124170334L;
	private static final Logger logger = new Logger(PrintSheetsButton.class.toString());

	public PrintSheetsButton() {
		super("Print Timesheets");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.info("Button Clicked.");
				ExtendedHandler.displayAdminPanels(false, false, true, false);
				TextAreaList.timesheetDisplay.setText("");
			}
		});
		
		logger.debug("PrintSheetsButton initialised.");
	}

}
