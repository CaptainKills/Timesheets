package nl.triantis.timesheets.gui.lists;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import nl.triantis.timesheets.gui.textfields.*;

public class TextFieldList {

	public static JTextField inputField = new NumberInputTextField();
	public static JTextField idField = new IDInputField();
	public static JTextField firstNameField = new NameInputField();
	public static JTextField lastNameField = new NameInputField();
	public static JSpinner ageField = new AgeInputField();
	public static JSpinner salaryField = new SalaryInputField();

	public static JSpinner startingDateInput = new StartingDateInput();
	public static JSpinner endingDateInput = new EndingDateInput();

	public static JSpinner fontsizeInput = new FontsizeInput();
	public static JSpinner logCountInput = new LogCountInput();
	public static JSpinner backupCountInput = new BackupCountInput();
	public static JSpinner widthInput = new WidthInput();
	public static JSpinner heightInput = new HeightInput();

}
