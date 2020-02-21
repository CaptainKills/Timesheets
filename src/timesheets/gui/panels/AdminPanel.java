package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.JPanel;

import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LayoutList;

public class AdminPanel extends JPanel{
	private static final long serialVersionUID = -7890834504049559961L;

	public AdminPanel() {
		setPreferredSize(DimensionList.defaultPanelSize);
		setLayout(LayoutList.defaultLayout);
		setBackground(Color.WHITE);
		setVisible(false);
	}

}