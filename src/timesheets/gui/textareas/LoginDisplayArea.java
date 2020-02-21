package timesheets.gui.textareas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.Timer;

import timesheets.TimeHandler;
import timesheets.gui.lists.FontList;

public class LoginDisplayArea extends JTextArea{
	private static final long serialVersionUID = -8427377108410611993L;
	
	private TimeHandler time = new TimeHandler();

	public LoginDisplayArea() {
		super();
		setFont(FontList.infolabelFont);
		setPreferredSize(new Dimension(460, 61));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		setEditable(false);
		setLineWrap(true);
		
		setText("| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n" + "Please enter ID to log in...");
		
		new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				updateTimeText();
			}
		}).start();
	}
	
	public void updateInfoText(String newText) {
		String timeText = "| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n";
		String infoText = newText;
		
		setText("");
		setText(timeText + infoText);
	}
	
	private void updateTimeText() {
		String timeText = "| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |\n";
		String infoText = getInfoText(getText());
		
		setText("");
		setText(timeText + infoText);
	}
	
	private String getInfoText(String currentText) {
		String[] parts = currentText.split("\n");
		return parts[parts.length-1];
	}

}
