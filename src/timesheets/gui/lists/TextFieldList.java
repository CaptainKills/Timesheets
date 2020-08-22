package timesheets.gui.lists;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import timesheets.gui.textfields.*;

public class TextFieldList {

	public static JTextField inputField = new NumberInputTextField();
	public static JTextField idField = new IDInputField();
	public static JTextField nameField = new NameInputField();
	public static JSpinner ageField = new AgeInputField();
	public static JSpinner salaryField = new SalaryInputField();
	
	public static JTextField yearInput_from = new YearInputFrom();
	public static YearInputTo yearInput_to = new YearInputTo();
	public static JTextField monthInput_from = new MonthInputFrom();
	public static MonthInputTo monthInput_to = new MonthInputTo();
	public static JTextField dayInput_from = new DayInputFrom();
	public static DayInputTo dayInput_to = new DayInputTo();
	
	public static JSpinner fontsizeInput = new FontsizeInput();
	public static JSpinner logCountInput = new LogCountInput();
	public static JSpinner backupCountInput = new BackupCountInput();
	public static JSpinner widthInput = new WidthInput();
	public static JSpinner heightInput = new HeightInput();

}
