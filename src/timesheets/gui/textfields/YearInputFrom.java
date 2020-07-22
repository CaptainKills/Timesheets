package timesheets.gui.textfields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class YearInputFrom extends JTextField{
	
	private static final long serialVersionUID = 3544458880537219183L;
	private static final Logger logger = new Logger(YearInputFrom.class.toString());

	private static int length = 4;
	
	public YearInputFrom() {
		super("yyyy", length);
		setPreferredSize(DimensionList.dateDisplaySize_medium);
		setFont(FontList.textDisplayFont);
		setEnabled(false);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {				
				if (getText().length() >= length) {
					e.consume();
				}
			}
		});
		
		logger.debug("YearInputFrom initialised.");
	}

}
