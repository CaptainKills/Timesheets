package timesheets.gui.combobox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.ResourceHandler;

public class BackupBox<T> extends JComboBox<T>{
	private static final long serialVersionUID = -229433229070533167L;
	private static final Logger logger = new Logger(BackupBox.class.toString());
	
	private static String[] directory_files = ResourceHandler.data_directory_path.toFile().list();

	public BackupBox() {
		setPreferredSize(DimensionList.fieldSize_large);
		setFont(FontList.normalFont);
		setVisible(true);
		setEnabled(true);
		
		addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (getSelectedItem() != null) {
					for(String f : directory_files) {
						if(f.equals(getSelectedItem().toString())) {
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
	
	@SuppressWarnings("unchecked")
	public void loadBackupsInBox() {
		removeAllItems();
		for (String backup : directory_files) {
			if(backup.contains("Backup")) {
				addItem((T) backup.substring(0, backup.length()-10));
			}
		}
		setSelectedItem(-1);

	}

}
