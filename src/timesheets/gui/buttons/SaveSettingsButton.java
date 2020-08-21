package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

	private JTextField fontsizeInput = TextFieldList.fontsizeInput;
	private JTextField logCountInput = TextFieldList.logCountInput;
	private JTextField backupCountInput = TextFieldList.backupCountInput;
	private JTextField widthInput = TextFieldList.widthInput;
	private JTextField heightInput = TextFieldList.heightInput;
	private JRadioButton deleteLogEnabledButton = ButtonList.deleteLogEnabledButton;

	private Map<String, String> settings = Settings.settings;

	public SaveSettingsButton() {
		super("Save Settings");
		setPreferredSize(DimensionList.buttonSize_medium);
		setFont(FontList.buttonFont);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (fontsizeInput.getText().equals("") || logCountInput.getText().equals("")) {
					JOptionPane.showMessageDialog(PanelList.mainPanel, "Please fill in all fields before submitting!",
							"Empty Field!", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int menuChoice = JOptionPane.showConfirmDialog(PanelList.mainPanel, "Are you sure you want to save these settings?",
						"Are you sure?", JOptionPane.YES_NO_CANCEL_OPTION);

				if(menuChoice == JOptionPane.YES_OPTION) {
					updateSettings();
					JOptionPane.showMessageDialog(PanelList.mainPanel,
							"Settings have succesfully been saved!\nPlease restart the application for the new settings to take effect.",
							"Successful Creation!", JOptionPane.INFORMATION_MESSAGE);
				} else {
					return;
				}
			}
		});

		logger.debug("SaveSettingsButton initialised.");
	}

	private void updateSettings() {
		settings.put("fontsize", fontsizeInput.getText());
		
		if (deleteLogEnabledButton.isSelected()) {
			settings.put("delete_logs", "true");
			settings.put("number_of_logs", logCountInput.getText());
		} else {
			settings.put("delete_logs", "false");
		}
		
		settings.put("number_of_backups", backupCountInput.getText());
		
		settings.put("width", widthInput.getText());
		settings.put("height", heightInput.getText());

		Settings.saveSettings();
	}
}