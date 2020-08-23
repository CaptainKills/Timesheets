package timesheets.gui.textareas.scrollpanes;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import timesheets.gui.lists.DisplayList;

public class ScrollPane extends JScrollPane {

	private static final long serialVersionUID = 4507250274917970495L;

	public ScrollPane() {
		super(DisplayList.timesheetDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setPreferredSize(new Dimension(250,330));
	}

}
