package timesheets.gui.lists;

import javax.swing.JLabel;

import timesheets.gui.labels.*;

public class LabelList {
	
	public static JLabel empLabel = new EmployeeLabel();
	public static JLabel nameLabel = new NameLabel();
	public static JLabel idLabel = new IDLabel();
	public static JLabel ageLabel = new AgeSalaryLabel();
	public static JLabel adminLabel = new AdminLabel();
	
	public static JLabel sep1Label = new SeperatorLabel(":");
	public static JLabel sep2Label = new SeperatorLabel(":");
	public static JLabel sep3Label = new SeperatorLabel(":");
	public static JLabel sep4Label = new SeperatorLabel(":");
	public static JLabel sep5Label = new SeperatorLabel(":");
	
	public static JLabel todayLabel = new TodayLabel();
	public static JLabel weekLabel = new WeekLabel();
	public static JLabel monthLabel = new MonthLabel();
	public static JLabel fromDateLabel = new FromDateLabel();
	public static JLabel toDateLabel = new ToDateLabel();
	
	public static JLabel fontsizeLabel = new FontsizeLabel();
	public static JLabel logLabel = new LogLabel();
	public static JLabel backupLabel = new BackupLabel();
	
}
