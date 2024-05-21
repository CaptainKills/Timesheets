package nl.triantis.timesheets.gui.combobox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import nl.triantis.timesheets.gui.lists.DimensionList;
import nl.triantis.timesheets.gui.lists.FontList;
import nl.triantis.timesheets.logging.Logger;
import nl.triantis.timesheets.resources.ResourceHandler;

public class BackupBox<T> extends JComboBox<String> {
	private static final long serialVersionUID = -229433229070533167L;
	private static final Logger logger = new Logger(BackupBox.class);

	private static String[] directory_files = ResourceHandler.data_directory_path.toFile().list();

	public BackupBox() {
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.normalFont);
		setVisible(true);
		setEnabled(true);

		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (getSelectedItem() != null) {
					for (String f : directory_files) {
						if (f.equals(getSelectedItem().toString())) {
							logger.debug(f + " got selected.");
						}
					}
					logger.info("Backups succesfully loaded into combobox field.");
				} else {
					return;
				}
			}
		});

		logger.debug("BackupBox initialised.");
	}

	public void loadBackupsInBox() {
		removeAllItems();
		for (String backup : directory_files) {
			if (backup.contains("Backup")) {
				addItem(backup.substring(0, backup.length() - 10));
			}
		}
		setSelectedItem(-1);
	}

}
