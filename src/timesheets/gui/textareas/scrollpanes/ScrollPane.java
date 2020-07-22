package timesheets.gui.textareas.scrollpanes;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import timesheets.gui.lists.TextAreaList;

public class ScrollPane extends JScrollPane {

	private static final long serialVersionUID = 4507250274917970495L;

	public ScrollPane() {
		super(TextAreaList.timesheetDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setPreferredSize(new Dimension(10,100));
	}

}
