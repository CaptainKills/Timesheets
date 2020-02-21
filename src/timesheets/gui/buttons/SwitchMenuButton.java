package timesheets.gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.FontList;
import timesheets.gui.lists.PanelList;

public class SwitchMenuButton extends JButton{
	private static final long serialVersionUID = 4691645706084905079L;

	public SwitchMenuButton() {
		super(">>");
		setPreferredSize(DimensionList.buttonSize_small);
		setFont(FontList.buttonFont);
		setEnabled(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (getText().equals(">>")) {
					PanelList.numpadPanel.setVisible(false);
					PanelList.adminPanel.setVisible(true);
					
					setText("<<");
				} else if (getText().equals("<<")) {
					PanelList.numpadPanel.setVisible(true);
					PanelList.adminPanel.setVisible(false);
					
					setText(">>");
				}
				//pack();
			}
		});
	}

}
