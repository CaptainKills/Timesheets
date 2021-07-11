package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.UnusualsList;
import timesheets.gui.other.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;
import timesheets.sql.Database;

public class RevertBackupButton extends JButton {

	private static final long serialVersionUID = 3714748366642217763L;
	private static final Logger logger = new Logger(RevertBackupButton.class);
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String buttonText = lang.get("revert_backup_button");
	private static String dialogTitle = lang.get("jop_rbb_title");
	private static String dialogMsg = lang.get("jop_rbb_msg");

	public RevertBackupButton() {
		super(buttonText);
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String backup = UnusualsList.backupBox.getSelectedItem().toString();
				
				CustomOptionPane cop = new CustomOptionPane("RevertBackupButton");
				cop.setText(dialogTitle, dialogMsg);
				cop.setConfig(JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
				
				int menuChoice = cop.showDialog();

				if (UnusualsList.backupBox.getSelectedItem() != null && menuChoice == JOptionPane.YES_OPTION) {
					logger.debug(backup + " is the selected backup.");
					Database.revertBackup(backup);
				}
			}
		});

		logger.debug("RevertBackupButton initialised.");
	}

}
