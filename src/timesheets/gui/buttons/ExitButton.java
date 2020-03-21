package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import timesheets.DataHandler;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class ExitButton extends JButton{
	private static final long serialVersionUID = -5805705893852029673L;
	
	private DataHandler data = new DataHandler();

	public ExitButton() {
		super("Exit");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure?") == JOptionPane.YES_OPTION) {
					data.saveDataToFiles();
					System.exit(0);
				}
			}
		});
	}

}
