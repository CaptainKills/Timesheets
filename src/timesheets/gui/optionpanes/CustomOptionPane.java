package timesheets.gui.optionpanes;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import timesheets.gui.lists.PanelList;

public class CustomOptionPane extends JOptionPane {

	private static final long serialVersionUID = -7341742724337883119L;
	private String title;
	
	public CustomOptionPane() {
		setBackground(Color.WHITE);
	}
	
//	public CustomOptionPane(String title, String msg, int msgType, int optionType) {
//		this.title = title;
//		setMessage(msg);
//		setMessageType(msgType);
//		setOptionType(optionType);
//		setBackground(Color.WHITE);
//	}
	
	public int showDialog() {
		JDialog dialog = createDialog(PanelList.mainPanel, title);
		dialog.pack();
		dialog.setVisible(true);
		
		if(this.getValue() == null) {
			return JOptionPane.CANCEL_OPTION;
		}
		
		int value = ((Integer) this.getValue()).intValue();
		return value;
	}
	
	public void setText(String title, String msg) {
		this.title = title;
		setMessage(msg);
	}
	
	public void setOptions(int msgType, int optionType) {
		setMessageType(msgType);
		setOptionType(optionType);
	}

}
