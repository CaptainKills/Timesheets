package timesheets.gui.textareas;

import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;
import timesheets.update.Update;

public class UpdateMessagePane extends JEditorPane {

	private static final long serialVersionUID = 3279940521683269699L;
	private static final Logger logger = new Logger(UpdateMessagePane.class);

	public UpdateMessagePane() {
		setFont(FontList.normalFont);
		setContentType("text/html");
		setEditable(false);
		setBackground(new Color(0, 0, 0, 0));

		setText(Update.getUpdateText());

		addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {

				if (event.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
					try {
						Desktop.getDesktop().browse(event.getURL().toURI());
					} catch (IOException | URISyntaxException e) {
						logger.error("COULD NOT OPEN HYPERLINK!", e);
					}
				}
			}
		});

		logger.debug("TimesheetDisplay initialised.");
	}
}
