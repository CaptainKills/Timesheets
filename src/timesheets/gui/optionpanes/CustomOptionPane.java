package timesheets.gui.optionpanes;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;

public class CustomOptionPane extends JOptionPane {

	private static final long serialVersionUID = -7341742724337883119L;
	private JDialog dialog;
	
	public CustomOptionPane(String title, String msg, int msgType, int optionType) {
		setMessage(msg);
		setMessageType(msgType);
		setOptionType(optionType);
		
		setFont(FontList.normalFont);
		setBackground(Color.WHITE);
		
		this.dialog = createDialog(PanelList.mainPanel, title);
	}
	
	public int showDialog() {
		this.dialog.pack();
		this.dialog.setVisible(true);
		
		int value = ((Integer) this.getValue()).intValue();
		return value;
	}

}
