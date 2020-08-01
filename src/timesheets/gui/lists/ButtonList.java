package timesheets.gui.lists;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import timesheets.gui.buttons.*;

public class ButtonList {
	
	public static JButton numberButton0 = new NumberButton("0");
	public static JButton numberButton1 = new NumberButton("1");
	public static JButton numberButton2 = new NumberButton("2");
	public static JButton numberButton3 = new NumberButton("3");
	public static JButton numberButton4 = new NumberButton("4");
	public static JButton numberButton5 = new NumberButton("5");
	public static JButton numberButton6 = new NumberButton("6");
	public static JButton numberButton7 = new NumberButton("7");
	public static JButton numberButton8 = new NumberButton("8");
	public static JButton numberButton9 = new NumberButton("9");
	
	public static JButton clearButton = new ClearButton();
	public static JButton removeNumberButton = new RemoveNumberButton();
	public static JButton loginButton = new LoginButton();
	
	public static JButton startShiftButton = new StartShiftButton();
	public static JButton endShiftButton = new EndShiftButton();
	public static JButton startBreakButton = new StartBreakButton();
	public static JButton endBreakButton = new EndBreakButton();
	public static JButton logoutButton = new LogoutButton();
	public static JButton switchMenuButton = new SwitchMenuButton();
	
	public static JButton idButton = new IDGeneratorButton();
	public static JButton submitButton = new SubmitButton();
	
	public static JRadioButton adminEnableButton = new AdminRadioButton("Enable", false);
	public static JRadioButton adminDisableButton = new AdminRadioButton("Disable", true);
	public static ButtonGroup adminGroup = new AdminSelectionGroup();
	
	public static JButton addEmployeeButton = new AddEmployeeButton();
	public static JButton removeEmployeeButton = new RemoveEmployeeButton();
	public static JButton editEmployeeButton = new EditEmployeeButton();
	public static JButton printSheetsButton = new PrintSheetsButton();
	public static JButton editSheetsButton = new EditSheetsButton();
	public static JButton settingsButton = new SettingsButton();
	public static JButton exitButton = new ExitButton();
	
	public static JRadioButton dateTodayButton = new DateTodayButton();
	public static JRadioButton dateWeekButton = new DateWeekButton();
	public static JRadioButton dateMonthButton = new DateMonthButton();
	public static JRadioButton dateSpecificButton = new DateSpecificButton();
	public static ButtonGroup dateSelectionGroup = new DateSelectionGroup();
	public static JButton submitDateButton = new SubmitDateButton();
	
	public static JRadioButton deleteLogEnabledButton = new DeleteLogRadioButton("Yes", false);
	public static JRadioButton deleteLogDisabledButton = new DeleteLogRadioButton("No", true);
	public static ButtonGroup logGroup = new DeleteLogSelectionGroup();
}