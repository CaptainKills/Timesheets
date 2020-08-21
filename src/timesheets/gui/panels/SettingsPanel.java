package timesheets.gui.panels;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import timesheets.gui.lists.ButtonList;
import timesheets.gui.lists.DimensionList;
import timesheets.gui.lists.LabelList;
import timesheets.gui.lists.TextFieldList;
import timesheets.gui.lists.UnusualsList;
import timesheets.logging.Logger;

public class SettingsPanel extends JPanel{
	private static final long serialVersionUID = 8313230141682785085L;
	private static final Logger logger = new Logger(SettingsPanel.class);

	public SettingsPanel() {
		super();
		setPreferredSize(DimensionList.defaultPanelSize);
		setBackground(Color.WHITE);
		setVisible(false);
		
		GroupLayout layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(LabelList.fontLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createSequentialGroup()
											.addComponent(LabelList.fontsizeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(TextFieldList.fontsizeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									
									.addComponent(LabelList.dimensionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createSequentialGroup()
											.addComponent(LabelList.widthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(TextFieldList.widthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									
									.addGroup(layout.createSequentialGroup()
											.addComponent(LabelList.heightLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(TextFieldList.heightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									
									)
							
							.addGroup(layout.createParallelGroup()
									.addComponent(LabelList.backupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createSequentialGroup()
											.addComponent(LabelList.numberOfBackupsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(TextFieldList.backupCountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									
									.addComponent(LabelList.logLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addGroup(layout.createSequentialGroup()
											.addComponent(ButtonList.deleteLogEnabledButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(ButtonList.deleteLogDisabledButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									
									.addGroup(layout.createSequentialGroup()
											.addComponent(LabelList.numberOfLogsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											.addComponent(TextFieldList.logCountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
											)
									)
							)
					.addGroup(layout.createParallelGroup()
							.addGroup(layout.createSequentialGroup()
									.addComponent(LabelList.selectBackupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addComponent(UnusualsList.backupBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									)
							.addGroup(layout.createSequentialGroup()
									.addComponent(LabelList.emptyLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									.addComponent(ButtonList.revertBackupButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
									)
							)
					
					.addGroup(layout.createSequentialGroup()
							.addComponent(ButtonList.saveSettingsButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
							)
				);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.fontLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.backupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)

						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.fontsizeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.fontsizeInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.numberOfBackupsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.backupCountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.dimensionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.logLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.widthLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.widthInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(ButtonList.deleteLogEnabledButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(ButtonList.deleteLogDisabledButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.heightLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.heightInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(LabelList.numberOfLogsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(TextFieldList.logCountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						
						)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.selectBackupLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(UnusualsList.backupBox, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						.addGroup(layout.createParallelGroup()
								.addComponent(LabelList.emptyLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								.addComponent(ButtonList.revertBackupButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
								)
						)
				
				.addGroup(layout.createSequentialGroup()
						.addComponent(ButtonList.saveSettingsButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
						)
				);
		setLayout(layout);
		
		logger.debug("SettingsPanel initialised.");
	}

}
