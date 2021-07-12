package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

import timesheets.Settings;
import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.other.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class SaveSettingsButton extends JButton {
	private static final long serialVersionUID = 8704736175682340957L;
	private static final Logger logger = new Logger(SaveSettingsButton.class);

	private JSpinner fontsizeInput = TextFieldList.fontsizeInput;
	private JSpinner logCountInput = TextFieldList.logCountInput;
	private JSpinner backupCountInput = TextFieldList.backupCountInput;
	private JSpinner widthInput = TextFieldList.widthInput;
	private JSpinner heightInput = TextFieldList.heightInput;
	private JRadioButton deleteLogEnabledButton = ButtonList.deleteLogEnabledButton;

	private Map<String, String> settings = Settings.settings;
	private static Map<String, String> lang = LanguageManager.language;
	
	private static String buttonText = lang.get("save_settings_button");
	private static String dialogTitleConfirm = lang.get("jop_ssb_title_confirm");
	private static String dialogMsgConfirm = lang.get("jop_ssb_msg_confirm");
	private static String dialogTitleSuccess = lang.get("jop_ssb_title_success");
	private static String dialogMsgSuccess = lang.get("jop_ssb_msg_success");

	public SaveSettingsButton() {
		super(buttonText);
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				CustomOptionPane cop = new CustomOptionPane("SaveSettingsButton");
				cop.setText(dialogTitleConfirm, dialogMsgConfirm);
				cop.setConfig(JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
				
				int menuChoice = cop.showDialog();

				if (menuChoice == JOptionPane.YES_OPTION) {
					updateSettings();
					
					cop.setText(dialogTitleSuccess, dialogMsgSuccess);
					cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
					cop.showDialog();
				} else {
					return;
				}
			}
		});

		logger.debug("SaveSettingsButton initialised.");
	}

	private void updateSettings() {
		settings.put("fontsize", Integer.toString((Integer) fontsizeInput.getValue()));

		if (deleteLogEnabledButton.isSelected()) {
			settings.put("delete_logs", "true");
		} else {
			settings.put("delete_logs", "false");
		}

		settings.put("number_of_logs", Integer.toString((Integer) logCountInput.getValue()));
		settings.put("number_of_backups", Integer.toString((Integer) backupCountInput.getValue()));

		settings.put("width", Integer.toString((Integer) widthInput.getValue()));
		settings.put("height", Integer.toString((Integer) heightInput.getValue()));

		Settings.save();
	}
}