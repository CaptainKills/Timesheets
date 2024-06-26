package nl.triantis.timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.gui.other.CustomOptionPane;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.LanguageManager;
import nl.triantis.timesheets.sql.Database;

public class ExitButton extends JButton {
	private static final long serialVersionUID = -5805705893852029673L;
	private static final Logger logger = new Logger(ExitButton.class);
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String buttonText = lang.get("exit_button");
	private static String dialogTitle = lang.get("jop_eb_title");
	private static String dialogMsg = lang.get("jop_eb_msg");

	public ExitButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				exitApplication();
			}
		});

		logger.debug("ExitButton initialised.");
	}

	public void exitApplication() {
		CustomOptionPane cop = new CustomOptionPane("Exit Application");
		cop.setText(dialogTitle, dialogMsg);
		cop.setConfig(JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		
		int decision = cop.showDialog();
		
		if (decision == JOptionPane.YES_OPTION) {
			logger.info("Closing Application...");
			Database.backupDatabase();
			logger.info("Application Closed.\n");
			System.exit(0);
		}
	}

}
