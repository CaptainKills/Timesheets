package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;

public class PrintSheetsButton extends JButton{
	private static final long serialVersionUID = 2103797465124170334L;

	public PrintSheetsButton() {
		super("Print Timesheets");
		setPreferredSize(DimensionList.buttonSize_menu);
		setFont(FontList.buttonFont);
		setVisible(false);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//mainEmpLabelPanel.setVisible(false);
				//mainEmpControlPanel.setVisible(false);
				//dateControlPanel.setVisible(true);
				//dateDisplayPanel.setVisible(true);

				//dateDisplay.setText("");
				//pack();
			}
		});
	}

}
