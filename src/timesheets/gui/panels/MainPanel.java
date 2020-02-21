package timesheets.gui.panels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.PanelList;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 7649238192790429353L;

	public MainPanel() {
		setVisible(true);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				//System.out.println("Resized to " + e.getComponent().getSize());
			}
		});
	}
	
	public void setupPanel() {
		//add(PanelList.testPanel);
		add(PanelList.numpadPanel);
		add(PanelList.menuPanel);
		add(PanelList.adminPanel);
		add(PanelList.editPanel);
	}

}