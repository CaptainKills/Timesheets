package timesheets.gui.textfields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class MonthInputFrom extends JTextField{

	private static final long serialVersionUID = -8560045718472572545L;
	private static final Logger logger = new Logger(MonthInputFrom.class);

	private static int length = 2;
	
	public MonthInputFrom() {
		super("mm", length);
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
		
		logger.debug("MonthInputFrom initialised.");
	}

}
