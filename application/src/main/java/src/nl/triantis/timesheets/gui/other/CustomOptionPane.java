package nl.triantis.timesheets.gui.other;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import nl.triantis.timesheets.gui.lists.PanelList;
import nl.triantis.timesheets.logging.Logger;

public class CustomOptionPane extends JOptionPane {

	private static final long serialVersionUID = -7341742724337883119L;
	private static final Logger logger = new Logger(CustomOptionPane.class);
	
	private String id;
	private String title;
	private String okeOption;
	
	public CustomOptionPane(String id) {
		this.id = id;
		setBackground(Color.WHITE);
	}
	
	public int showDialog() {
		logger.info(id + ": Showing Dialog.");
		
		JDialog dialog = createDialog(PanelList.mainPanel, title);
		dialog.pack();
		dialog.setVisible(true);
		
		Object obj = this.getValue();
		
		if(obj == null) {	// If user closes dialog
			return JOptionPane.CANCEL_OPTION;
		} else if(obj.getClass() == String.class) {
			if(((String) obj) == okeOption){
				return JOptionPane.YES_OPTION;
			}
			
			return JOptionPane.NO_OPTION;
		}
		
		int value = ((Integer) obj).intValue();
		return value;
	}
	
	public void setText(String title, String msg) {
		this.title = title;
		setMessage(msg);
	}
	
	public void setConfig(int msgType, int optionType) {
		setMessageType(msgType);
		setOptionType(optionType);
	}
	
	public void setButtons(Object[] options) {
		setOptions(options);
		this.okeOption = (String) options[0];
		setInitialValue(options[0]);
	}

}
