package timesheets.gui.textareas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import timesheets.TimeHandler;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class LoginDisplay extends JTextArea {
	private static final long serialVersionUID = -8427377108410611993L;
	private static final Logger logger = new Logger(LoginDisplay.class);

	private TimeHandler time = new TimeHandler();
	private static String enterIdText = LanguageManager.language.get("enter_id_text");

	public LoginDisplay() {
		super();
		setFont(FontList.infolabelFont);
		setPreferredSize(new Dimension(460, 61));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		setEditable(false);
		setLineWrap(true);

		setText("| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n" + enterIdText);

		logger.debug("LoginDisplay initialised.");
	}

	public void updateInfoText(String newText) {
		String timeText = "| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n";
		String infoText = newText;

		setText("");
		setText(timeText + infoText);
	}

	public void updateTimeText() {
		String timeText = "| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n";
		String infoText = getInfoText(getText());

		setText("");
		setText(timeText + infoText);
	}

	private String getInfoText(String currentText) {
		String[] parts = currentText.split("\n");
		return parts[parts.length - 1];
	}

}
