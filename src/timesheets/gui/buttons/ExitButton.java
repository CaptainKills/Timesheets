package timesheets.gui.buttons;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class ExitButton extends JButton {
	private static final long serialVersionUID = -5805705893852029673L;
	private static final Logger logger = new Logger(ExitButton.class);

	public ExitButton() {
		super("Exit");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				exitApplication(getRootPane());
			}
		});

		logger.debug("ExitButton initialised.");
	}

	public void exitApplication(Container rootPane) {
		int decision = JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Exit Application", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if (decision == JOptionPane.YES_OPTION) {
			logger.info("Closing Application...");
			Database.backupDatabase();
			logger.info("Application Closed.\n");
			System.exit(0);
		}
	}

}
