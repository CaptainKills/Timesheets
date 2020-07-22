package timesheets.gui.textfields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.TimeHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class MonthInputTo extends JTextField{

	private static final long serialVersionUID = -2023560341229107826L;
	private static final Logger logger = new Logger(MonthInputTo.class.toString());
	private static TimeHandler time = new TimeHandler();
	
	private static int length = 2;
	private static String date = Integer.toString(time.getCurrentDate().getMonthValue());
	
	public MonthInputTo() {
		super(date, length);
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
		
		logger.debug("MonthInputTo initialised.");
	}

}
