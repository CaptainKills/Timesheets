package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import timesheets.Settings;
import timesheets.gui.ExtendedHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.TextFieldList;
import timesheets.logging.Logger;

public class SettingsButton extends JButton{
	private static final long serialVersionUID = -3562889450672410185L;
	private static final Logger logger = new Logger(SettingsButton.class.toString());
	
	private static Map<String, String> settings = Settings.settings;

	public SettingsButton() {
		super("Settings");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				ExtendedHandler.displayAdminPanels(false, false, false, true);
				loadSettingsFields();
			}
		});
		
		logger.debug("SettingsButton initialised.");
	}
	
	private void loadSettingsFields() {
		TextFieldList.fontsizeInput.setText(settings.get("fontsize"));
		
		TextFieldList.logCountInput.setText(settings.get("number_of_logs"));
		TextFieldList.backupCountInput.setText(settings.get("number_of_backups"));
		
	}

}
