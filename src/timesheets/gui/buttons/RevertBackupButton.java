package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class RevertBackupButton extends JButton {
	
	private static final long serialVersionUID = 3714748366642217763L;
	private static final Logger logger = new Logger(RevertBackupButton.class.toString());
	
	public RevertBackupButton() {
		super("Revert Backup");
		setPreferredSize(DimensionList.dateDisplaySize_large);
		setFont(FontList.textDisplayFont);
		setEnabled(true);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				logger.debug(UnusualsList.backupBox.getSelectedItem() + " is the selected backup.");
			}
		});
		
		logger.debug("RevertBackupButton initialised.");
	}

}
