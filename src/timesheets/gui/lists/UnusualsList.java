package timesheets.gui.lists;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import timesheets.gui.combobox.BackupBox;
import timesheets.gui.combobox.EmployeeBox;
import timesheets.sql.Database;

public class UnusualsList {

	public static EmployeeBox<String> empBox = new EmployeeBox<String>();
	public static BackupBox<String> backupBox = new BackupBox<String>();


	public static Timer updateTimer = new Timer(500, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			// Update all time-related GUI Elements.

			DisplayList.loginTextArea.updateTimeText();
			LabelList.todayLabel.updateText();
			LabelList.weekLabel.updateText();
			LabelList.monthLabel.updateText();
		}
	});
	
	public static Timer backupTimer = new Timer(3600000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Backup database every hour.
			
			Database.backupDatabase();
		}
		
	});

}