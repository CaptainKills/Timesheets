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
import timesheets.gui.lists.PanelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

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

	public SaveSettingsButton() {
		super("Save Settings");
		setPreferredSize(DimensionList.fieldSize_medium);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int menuChoice = JOptionPane.showConfirmDialog(PanelList.mainPanel,
						"Are you sure you want to save these settings?", "Are you sure?",
						JOptionPane.YES_NO_CANCEL_OPTION);

				if (menuChoice == JOptionPane.YES_OPTION) {
					updateSettings();
					JOptionPane.showMessageDialog(PanelList.mainPanel,
							"Settings have succesfully been saved!\nPlease restart the application for the new settings to take effect.",
							"Settings Saved!", JOptionPane.INFORMATION_MESSAGE);
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
			settings.put("number_of_logs", Integer.toString((Integer) logCountInput.getValue()));
		} else {
			settings.put("delete_logs", "false");
		}

		settings.put("number_of_backups", Integer.toString((Integer) backupCountInput.getValue()));

		settings.put("width", Integer.toString((Integer) widthInput.getValue()));
		settings.put("height", Integer.toString((Integer) heightInput.getValue()));

		Settings.saveSettings();
	}
}