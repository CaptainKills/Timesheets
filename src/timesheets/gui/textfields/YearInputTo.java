package timesheets.gui.textfields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import timesheets.TimeHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.logging.Logger;

public class YearInputTo extends JTextField{

	private static final long serialVersionUID = -4924445974187541184L;
	private static final Logger logger = new Logger(YearInputTo.class.toString());
	private static TimeHandler time = new TimeHandler();
	
	private static int length = 4;
	private static String date = Integer.toString(time.getCurrentDate().getYear());

	public YearInputTo() {
		super(date, length-1);
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
		
		logger.debug("YearInputTo initialised.");
	}
	
	public void updateText() {
		setText(Integer.toString(time.getCurrentDate().getYear()));
	}

}
