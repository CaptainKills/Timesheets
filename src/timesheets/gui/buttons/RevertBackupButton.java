package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;
import timesheets.sql.Database;

public class RevertBackupButton extends JButton {

	private static final long serialVersionUID = 3714748366642217763L;
	private static final Logger logger = new Logger(RevertBackupButton.class);

	public RevertBackupButton() {
		super("Revert Backup");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.textDisplayFont);
		setEnabled(true);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String backup = UnusualsList.backupBox.getSelectedItem().toString();
				int menuChoice = JOptionPane.showConfirmDialog(PanelList.mainPanel,
						"Are you sure you want to revert this backup?", "Are you sure?",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (UnusualsList.backupBox.getSelectedItem() != null && menuChoice == JOptionPane.YES_OPTION) {
					logger.debug(backup + " is the selected backup.");
					Database.revertBackup(backup);
				}
			}
		});

		logger.debug("RevertBackupButton initialised.");
	}

}
