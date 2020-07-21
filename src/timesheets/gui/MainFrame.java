package timesheets.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import timesheets.DataHandler;
import timesheets.Employee;
import timesheets.TimeHandler;
import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 01L;
	private static final Logger logger = new Logger(MainFrame.class.toString());
	
	private DataHandler data = new DataHandler();
	private TimeHandler time = new TimeHandler();
	private Map<Integer, Employee> EmployeeList = DataHandler.EmployeeList;

//	private int id, menuChoice;
//	private Employee activeEmployee, transferEmployee;

//	private LocalTime[] previousShift, newShift;
//	private LocalTime currentTime, differenceTime, additionalBreakTime, newTime;
	private LocalDate currentDate, beginDate, endDate;

	public final int frameWidth = 960; // 1=960 2=1440
	public final int frameHeight = 350; // 1=350 2=700

	private JPanel mainPanel, numpadPanel, menuPanel, mainEmpLabelPanel, mainEmpControlPanel, dateControlPanel,
			dateDisplayPanel;
	private JTextField numInput, idInput, nameInput, ageInput, salaryInput, fromDayInput, fromMonthInput, fromYearInput,
			toDayInput, toMonthInput, toYearInput;
	private JLabel infoLabel, timeLabel, selectedEmpLabel, idLabel, nameLabel, ageSalLabel, adminLabel, dayLabel,
			weekLabel, monthLabel, fromLabel, toLabel;

	private JTextArea dateDisplay;
	private JScrollPane scrollPane;
//	private JComboBox<String> employeeComboBox;

	private ButtonGroup adminSelectionGroup, dateSelectionGroup;
	// numpadPanel buttons
//	private JButton numButton, clearButton, removeButton, zeroButton, loginButton;
	// menuPanel buttons
//	private JButton startButton, endButton, startBreakButton, endBreakButton, logoutButton, menuSwitchButton,
//			addEmpButton, removeEmpButton, editEmpButton, printSheetButton, editSheetButton, exitButton;
	// mainEmpControlPanel buttons
	private JButton idGenButton, submitButton, submitDateButton;
	private JRadioButton adminEnabled, adminDisabled, dateToday, dateWeek, dateMonth, dateSpecific;

//	private BoxLayout mainLayout;
	private FlowLayout normalPanelLayout, mainLabelPanelLayout, mainControlPanelLayout;

//	private Dimension panelSize = new Dimension(frameWidth / 2, frameHeight);
//	private Dimension buttonSize_numbers = new Dimension((5 * frameWidth) / 32, frameHeight / 7);
//	private Dimension buttonSize_menu = new Dimension((91 * frameWidth) / 384, frameHeight / 7);
//	private Dimension buttonSize_large = new Dimension((23 * frameWidth) / 48, frameHeight / 7);
//	private Dimension buttonSize_medium = new Dimension((43 * frameWidth) / 120, frameHeight / 7);
//	private Dimension buttonSize_small = new Dimension((89 * frameWidth) / 768, frameHeight / 7);
//	private Dimension labelSize_large = new Dimension((23 * frameWidth) / 48, (2 * frameHeight) / 25);
//	private Dimension labelSize_small = new Dimension(frameWidth / 8, frameHeight / 10);
//	private Dimension fieldSize_large = new Dimension((5 * frameWidth) / 16, frameHeight / 10);
//	private Dimension fieldSize_medium = new Dimension((29 * frameWidth) / 192, frameHeight / 10);
	private Dimension dateDisplaySize_large = new Dimension((11 * frameWidth) / 64, frameHeight / 14);
	private Dimension dateDisplaySize_medium = new Dimension(frameWidth / 9, frameHeight / 14);
	private Dimension dateDisplaySize_small = new Dimension(frameWidth / 20, frameHeight / 14);

//	private Font inputFont = new Font("Arial", Font.PLAIN, (3 * frameWidth) / 64);
//	private Font buttonFont = new Font("Arial", Font.PLAIN, frameWidth / 48);
//	private Font infolabelFont = new Font("Arial", Font.BOLD, frameWidth / 64);
	private Font normalFont = new Font("Arial", Font.PLAIN, frameWidth / 60);
	private Font textDisplayFont = new Font("Arial", Font.PLAIN, frameWidth / 64);
//	private Font labelFont = new Font("Arial", Font.PLAIN, frameWidth / 48);

	public MainFrame() {
		super("Timesheets");
		logger.info("Start MainFrame Setup.");
		data.loadDataFromFiles();

		PanelList.mainPanel.setupPanel();
		getContentPane().add(PanelList.mainPanel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Are you sure?") == JOptionPane.YES_OPTION) {
					logger.info("Closing Application...");
					data.saveDataToFiles();
					logger.info("Application Closed.\n");
					System.exit(0);
				}
			}
		});
		
		logger.info("MainFrame Setup Complete.");

		/*
		 * 
		 * 
		 * 
		 * 
		 */

//		mainPanel = new JPanel();
//		mainLayout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
//		mainPanel.setVisible(true);
//		mainPanel.setLayout(mainLayout);

//		numpadPanel = new JPanel();
//		normalPanelLayout = new FlowLayout(FlowLayout.CENTER, frameWidth / 192, frameWidth / 192);
//		numpadPanel.setPreferredSize(panelSize);
//		numpadPanel.setLayout(normalPanelLayout);
//		numpadPanel.setBackground(Color.WHITE);
//		numpadPanel.setVisible(true);

//		numInput = new JTextField("");
//		numInput.setPreferredSize(new Dimension((23 * frameWidth) / 48, (6 * frameHeight) / 35 + 1));
//		numInput.setFont(inputFont);
//		numInput.setEditable(false);
//		numInput.setBackground(Color.WHITE);
//		numInput.setHorizontalAlignment(SwingConstants.RIGHT);
//		numpadPanel.add(numInput);

//		for (int i = 1; i < 10; i++) {
//			String nameplate = Integer.toString(i);
//			numButton = new JButton(nameplate);
//			numButton.setPreferredSize(buttonSize_numbers);
//			numButton.setFont(buttonFont);
//			numButton.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent event) {
//					if (numInput.getText().length() < 5) {
//						numInput.setText(numInput.getText() + nameplate);
//					}
//					pack();
//				}
//			});
//			numpadPanel.add(numButton);
//		}

//		clearButton = new JButton("Clear");
//		clearButton.setPreferredSize(buttonSize_numbers);
//		clearButton.setFont(buttonFont);
//		clearButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				numInput.setText("");
//				pack();
//			}
//		});
//		numpadPanel.add(clearButton);

//		zeroButton = new JButton("0");
//		zeroButton.setPreferredSize(buttonSize_numbers);
//		zeroButton.setFont(buttonFont);
//		zeroButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				if (numInput.getText().length() < 5) {
//					numInput.setText(numInput.getText() + "0");
//				}
//				pack();
//			}
//		});
//		numpadPanel.add(zeroButton);

//		removeButton = new JButton("<<");
//		removeButton.setPreferredSize(buttonSize_numbers);
//		removeButton.setFont(buttonFont);
//		removeButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				if (numInput.getText().length() > 0) {
//					numInput.setText(numInput.getText().substring(0, numInput.getText().length() - 1));
//				}
//				pack();
//			}
//		});
//		numpadPanel.add(removeButton);

//		loginButton = new JButton("Login");
//		loginButton.setPreferredSize(buttonSize_large);
//		loginButton.setFont(buttonFont);
//		loginButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				id = Integer.parseInt(numInput.getText());
//				if (data.isIdUsed(id)) {
//					currentDate = time.getCurrentDate();
//					activeEmployee = EmployeeList.get(id);
//					infoLabel.setText("Welcome " + activeEmployee.getName());
//					loginButton.setEnabled(false);
//
//					if (activeEmployee.getTimeStarted() == null) {
//						startButton.setEnabled(true);
//					}
//					
//					if (activeEmployee.getTimeStarted() != null && activeEmployee.getTimePaused() != null) {
//						endButton.setEnabled(true);
//					} else if (activeEmployee.getTimeStarted() != null && (activeEmployee.getTimePaused() == null
//							&& activeEmployee.getBreakStarted() == null)) {
//						endButton.setEnabled(true);
//					}
//
//					if (activeEmployee.getBreakStarted() == null && activeEmployee.getTimeStarted() != null) {
//						startBreakButton.setEnabled(true);
//					}
//
//					if (activeEmployee.getBreakEnded() == null && activeEmployee.getBreakStarted() != null) {
//						endBreakButton.setEnabled(true);
//					}
//
//					if (activeEmployee.getAdmin() == true) {
//						menuSwitchButton.setEnabled(true);
//					}
//
//					logoutButton.setEnabled(true);
//					pack();
//				} else {
//					JOptionPane.showMessageDialog(mainPanel, "Please enter correct ID!", "Incorrect ID",
//							JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//		numpadPanel.add(loginButton);		

//		menuPanel = new JPanel();
//		menuPanel.setPreferredSize(panelSize);
//		menuPanel.setLayout(normalPanelLayout);
//		menuPanel.setBackground(Color.WHITE);
//		menuPanel.setVisible(true);

//		timeLabel = new JLabel("| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |");
//		timeLabel.setPreferredSize(labelSize_large);
//		timeLabel.setFont(infolabelFont);
//		new Timer(1000, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent evt) {
//				timeLabel.setText("| " + time.getCurrentDate() + " - " + time.getCurrentTime() + " |");
//			}
//		}).start();
//		menuPanel.add(timeLabel);

//		infoLabel = new JLabel("Please enter ID to log in...");
//		infoLabel.setPreferredSize(labelSize_large);
//		infoLabel.setFont(infolabelFont);
//		infoLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
//		menuPanel.add(infoLabel);

//		startButton = new JButton("Start Time");
//		startButton.setPreferredSize(buttonSize_menu);
//		startButton.setFont(buttonFont);
//		startButton.setEnabled(false);
//		startButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				currentTime = time.roundOffTime(time.getCurrentTime());
//				infoLabel.setText("Workshift has been started at: " + currentTime);
//				activeEmployee.setTimeStarted(currentTime);
//				enableTimeButtons(false, true, true, false);
//				pack();
//			}
//		});
//		menuPanel.add(startButton);

//		endButton = new JButton("End Time");
//		endButton.setPreferredSize(buttonSize_menu);
//		endButton.setFont(buttonFont);
//		endButton.setEnabled(false);
//		endButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				currentTime = time.roundOffTime(time.getCurrentTime());
//				activeEmployee.setTimeEnded(currentTime);
//				
//				if(activeEmployee.getTimePaused() != null) {
//					differenceTime = time.subtractFromTime(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded(), activeEmployee.getTimePaused());
//					infoLabel.setText("Total time worked this shift: " + differenceTime + ", and " + activeEmployee.getTimePaused() + " of breaktime.");
//				} else if(activeEmployee.getTimePaused() == null){
//					differenceTime = time.subtractFromTime(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded());
//					infoLabel.setText("Total time worked this shift: " + differenceTime + ", without a break.");
//				}
//				
//				if (activeEmployee.getWorkedTime().containsKey(currentDate)) {
//					previousShift = activeEmployee.getWorkedTime().get(currentDate);					
//					newTime = time.addToTime(previousShift[3], differenceTime);
//					additionalBreakTime = time.subtractFromTime(previousShift[1], activeEmployee.getTimeStarted());
//					
//					if(activeEmployee.getTimePaused() != null) {
//						activeEmployee.setTimePaused(time.addToTime(previousShift[2], additionalBreakTime, activeEmployee.getTimePaused()));
//					} else if(activeEmployee.getTimePaused() == null){
//						activeEmployee.setTimePaused(time.addToTime(previousShift[2], additionalBreakTime));
//					}
//					addToShift(previousShift[0], activeEmployee.getTimeEnded(), activeEmployee.getTimePaused(), newTime);
//				} else {
//					newTime = differenceTime;
//					addToShift(activeEmployee.getTimeStarted(), activeEmployee.getTimeEnded(), activeEmployee.getTimePaused(), newTime);
//				}
//
//				activeEmployee.setWorkedTime(currentDate, newShift);
//				activeEmployee.resetTime();
//				data.saveDataToFiles();
//				enableTimeButtons(true, false, false, false);
//				pack();
//			}
//		});
//		menuPanel.add(endButton);

//		startBreakButton = new JButton("Start Break");
//		startBreakButton.setPreferredSize(buttonSize_menu);
//		startBreakButton.setFont(buttonFont);
//		startBreakButton.setEnabled(false);
//		startBreakButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				currentTime = time.roundOffTime(time.getCurrentTime());
//				infoLabel.setText("Break has been started at: " + currentTime);
//				activeEmployee.setBreakStarted(currentTime);
//				enableTimeButtons(false, false, false, true);
//				pack();
//			}
//		});
//		menuPanel.add(startBreakButton);

//		endBreakButton = new JButton("End Break");
//		endBreakButton.setPreferredSize(buttonSize_menu);
//		endBreakButton.setFont(buttonFont);
//		endBreakButton.setEnabled(false);
//		endBreakButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				currentTime = time.roundOffTime(time.getCurrentTime());
//				infoLabel.setText("Break has been ended at: " + currentTime);
//				activeEmployee.setBreakEnded(currentTime);
//				differenceTime = time.subtractFromTime(activeEmployee.getBreakStarted(), activeEmployee.getBreakEnded());
//
//				if (activeEmployee.getTimePaused() != null) {
//					newTime = time.addToTime(activeEmployee.getTimePaused(), differenceTime);
//				} else {
//					newTime = differenceTime;
//				}
//				infoLabel.setText("Total break taken this shift: " + differenceTime);
//
//				activeEmployee.setTimePaused(newTime);
//				activeEmployee.setBreakStarted(null);
//				activeEmployee.setBreakEnded(null);
//				enableTimeButtons(false, true, true, false);
//				pack();
//			}
//		});
//		menuPanel.add(endBreakButton);

//		addEmpButton = new JButton("Add Employee");
//		addEmpButton.setPreferredSize(buttonSize_menu);
//		addEmpButton.setFont(buttonFont);
//		addEmpButton.setVisible(false);
//		addEmpButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				mainEmpLabelPanel.setVisible(true);
//				mainEmpControlPanel.setVisible(true);
//				dateControlPanel.setVisible(false);
//				dateDisplayPanel.setVisible(false);
//
//				submitButton.setText("Add Employee");
//				idInput.setEnabled(true);
//				idGenButton.setEnabled(true);
//				nameInput.setEnabled(true);
//				ageInput.setEnabled(true);
//				salaryInput.setEnabled(true);
//				adminEnabled.setEnabled(true);
//				adminDisabled.setEnabled(true);
//				employeeComboBox.setSelectedIndex(-1);
//
//				//clearInputs();
//				pack();
//			}
//		});
//		menuPanel.add(addEmpButton);

//		printSheetButton = new JButton("Print Timesheets");
//		printSheetButton.setPreferredSize(buttonSize_menu);
//		printSheetButton.setFont(buttonFont);
//		printSheetButton.setVisible(false);
//		printSheetButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				mainEmpLabelPanel.setVisible(false);
//				mainEmpControlPanel.setVisible(false);
//				dateControlPanel.setVisible(true);
//				dateDisplayPanel.setVisible(true);
//
//				dateDisplay.setText("");
//				pack();
//			}
//		});
//		menuPanel.add(printSheetButton);

//		removeEmpButton = new JButton("Remove Employee");
//		removeEmpButton.setPreferredSize(buttonSize_menu);
//		removeEmpButton.setFont(buttonFont);
//		removeEmpButton.setVisible(false);
//		removeEmpButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				mainEmpLabelPanel.setVisible(true);
//				mainEmpControlPanel.setVisible(true);
//				dateControlPanel.setVisible(false);
//				dateDisplayPanel.setVisible(false);
//
//				submitButton.setText("Remove Employee");
//				idInput.setEnabled(false);
//				idGenButton.setEnabled(false);
//				nameInput.setEnabled(false);
//				ageInput.setEnabled(false);
//				salaryInput.setEnabled(false);
//				adminEnabled.setEnabled(false);
//				adminDisabled.setEnabled(false);
//				employeeComboBox.setEnabled(true);
//				//loadNames();
//
//				//clearInputs();
//				pack();
//			}
//		});
//		menuPanel.add(removeEmpButton);

//		editSheetButton = new JButton("Edit Timesheets");
//		editSheetButton.setPreferredSize(buttonSize_menu);
//		editSheetButton.setFont(buttonFont);
//		editSheetButton.setVisible(false);
//		editSheetButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				mainEmpLabelPanel.setVisible(false);
//				mainEmpControlPanel.setVisible(false);
//				dateControlPanel.setVisible(true);
//				dateDisplayPanel.setVisible(true);
//
//				dateDisplay.setText("");
//				pack();
//			}
//		});
//		menuPanel.add(editSheetButton);

//		editEmpButton = new JButton("Edit Employee");
//		editEmpButton.setPreferredSize(buttonSize_menu);
//		editEmpButton.setFont(buttonFont);
//		editEmpButton.setVisible(false);
//		editEmpButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				mainEmpLabelPanel.setVisible(true);
//				mainEmpControlPanel.setVisible(true);
//				dateControlPanel.setVisible(false);
//				dateDisplayPanel.setVisible(false);
//
//				submitButton.setText("Save Employee");
//				idInput.setEnabled(true);
//				idGenButton.setEnabled(true);
//				nameInput.setEnabled(true);
//				ageInput.setEnabled(true);
//				salaryInput.setEnabled(true);
//				adminEnabled.setEnabled(true);
//				adminDisabled.setEnabled(true);
//				employeeComboBox.setEnabled(true);
//				//loadNames();
//
//				//clearInputs();
//				pack();
//			}
//		});
//		menuPanel.add(editEmpButton);

//		exitButton = new JButton("Exit Program");
//		exitButton.setPreferredSize(buttonSize_menu);
//		exitButton.setFont(buttonFont);
//		exitButton.setVisible(false);
//		exitButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				data.saveDataToFiles();
//				System.exit(0);
//			}
//		});
//		menuPanel.add(exitButton);

//		logoutButton = new JButton("Logout");
//		logoutButton.setPreferredSize(buttonSize_medium);
//		logoutButton.setFont(buttonFont);
//		logoutButton.setEnabled(false);
//		logoutButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				numInput.setText("");
//				infoLabel.setText("Please enter ID to log in...");
//				menuSwitchButton.setText(">>");
//
//				loginButton.setEnabled(true);
//				//displayTimeButtons(true);
//				//enableTimeButtons(false, false, false, false);
//				//displayAdminButtons(false);
//				logoutButton.setEnabled(false);
//				menuSwitchButton.setEnabled(false);
//
//				numpadPanel.setVisible(true);
//				mainEmpLabelPanel.setVisible(false);
//				mainEmpControlPanel.setVisible(false);
//				dateControlPanel.setVisible(false);
//				dateDisplayPanel.setVisible(false);
//				pack();
//			}
//		});
//		menuPanel.add(logoutButton);

//		menuSwitchButton = new JButton(">>");
//		menuSwitchButton.setPreferredSize(buttonSize_small);
//		menuSwitchButton.setFont(buttonFont);
//		menuSwitchButton.setEnabled(false);
//		menuSwitchButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				if (menuSwitchButton.getText().equals(">>")) {
//					numpadPanel.setVisible(false);
//					//displayTimeButtons(false);
//					//displayAdminButtons(true);
//					mainEmpLabelPanel.setVisible(true);
//					mainEmpControlPanel.setVisible(true);
//					menuSwitchButton.setText("<<");
//				} else if (menuSwitchButton.getText().equals("<<")) {
//					numpadPanel.setVisible(true);
//					mainEmpLabelPanel.setVisible(false);
//					mainEmpControlPanel.setVisible(false);
//					dateControlPanel.setVisible(false);
//					dateDisplayPanel.setVisible(false);
//					//displayTimeButtons(true);
//					//displayAdminButtons(false);
//					menuSwitchButton.setText(">>");
//				}
//				pack();
//			}
//		});
//		menuPanel.add(menuSwitchButton);

//		mainEmpLabelPanel = new JPanel();
//		mainLabelPanelLayout = new FlowLayout(FlowLayout.CENTER, 0, 20);
//		mainEmpLabelPanel.setPreferredSize(new Dimension(frameWidth / 6, frameHeight));
//		mainEmpLabelPanel.setLayout(mainLabelPanelLayout);
//		mainEmpLabelPanel.setBackground(Color.WHITE);
//		mainEmpLabelPanel.setVisible(true);

//		selectedEmpLabel = new JLabel("Employee    :");
//		selectedEmpLabel.setPreferredSize(labelSize_small);
//		selectedEmpLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		selectedEmpLabel.setFont(labelFont);
//		mainEmpLabelPanel.add(selectedEmpLabel);

//		idLabel = new JLabel("ID               :");
//		idLabel.setPreferredSize(labelSize_small);
//		idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		idLabel.setFont(labelFont);
//		mainEmpLabelPanel.add(idLabel);

//		nameLabel = new JLabel("Name          :");
//		nameLabel.setPreferredSize(labelSize_small);
//		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		nameLabel.setFont(labelFont);
//		mainEmpLabelPanel.add(nameLabel);

//		ageSalLabel = new JLabel("Age|Salary  :");
//		ageSalLabel.setPreferredSize(labelSize_small);
//		ageSalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		ageSalLabel.setFont(labelFont);
//		mainEmpLabelPanel.add(ageSalLabel);

//		adminLabel = new JLabel("Admin         :");
//		adminLabel.setPreferredSize(labelSize_small);
//		adminLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		adminLabel.setFont(labelFont);
//		mainEmpLabelPanel.add(adminLabel);

//		mainEmpControlPanel = new JPanel();
//		mainControlPanelLayout = new FlowLayout(FlowLayout.CENTER, 10, 20);
//		mainEmpControlPanel.setPreferredSize(new Dimension((2 * frameWidth) / 6, frameHeight));
//		mainEmpControlPanel.setLayout(mainControlPanelLayout);
//		mainEmpControlPanel.setBackground(Color.WHITE);

//		employeeComboBox = new JComboBox<String>();
//		employeeComboBox.setPreferredSize(fieldSize_large);
//		employeeComboBox.setFont(normalFont);
//		employeeComboBox.setVisible(true);
//		employeeComboBox.setEnabled(false);
//		employeeComboBox.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent event) {
//				if (employeeComboBox.getSelectedItem() != null) {
//					for (Employee emp : EmployeeList.values()) {
//						if (employeeComboBox.getSelectedItem().toString().equals(emp.getName())) {
//							idInput.setText(Integer.toString(emp.getID()));
//							nameInput.setText(emp.getName());
//							ageInput.setText(Integer.toString(emp.getAge()));
//							salaryInput.setText(Double.toString(emp.getSalary()));
//							if (emp.getAdmin() == true) {
//								adminEnabled.setSelected(true);
//							} else if (emp.getAdmin() == false) {
//								adminDisabled.setSelected(true);
//							}
//						} else {
//							continue;
//						}
//					}
//				} else {
//					return;
//				}
//				pack();
//			}
//		});
//		mainEmpControlPanel.add(employeeComboBox);

//		idGenButton = new JButton("#");
//		idGenButton.setPreferredSize(new Dimension(frameWidth / 20, frameHeight / 10));
//		idGenButton.setFont(new Font("Arial", Font.PLAIN, frameHeight / 25));
//		idGenButton.setEnabled(true);
//		idGenButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				idInput.setText(Integer.toString(data.generateNewID()));
//				pack();
//			}
//		});
//		mainEmpControlPanel.add(idGenButton);

//		idInput = new JTextField("");
//		idInput.setPreferredSize(new Dimension((241 * frameWidth) / 960, frameHeight / 10));
//		idInput.setFont(normalFont);
//		idInput.setEditable(false);
//		idInput.setHorizontalAlignment(SwingConstants.CENTER);
//		idInput.setBackground(Color.WHITE);
//		mainEmpControlPanel.add(idInput);

//		nameInput = new JTextField("");
//		nameInput.setPreferredSize(fieldSize_large);
//		nameInput.setFont(normalFont);
//		nameInput.setEditable(true);
//		nameInput.setHorizontalAlignment(SwingConstants.CENTER);
//		mainEmpControlPanel.add(nameInput);

//		ageInput = new JTextField("");
//		ageInput.setPreferredSize(fieldSize_medium);
//		ageInput.setFont(normalFont);
//		ageInput.setEditable(true);
//		ageInput.setHorizontalAlignment(SwingConstants.CENTER);
//		mainEmpControlPanel.add(ageInput);

//		salaryInput = new JTextField("");
//		salaryInput.setPreferredSize(fieldSize_medium);
//		salaryInput.setFont(normalFont);
//		salaryInput.setEditable(true);
//		salaryInput.setHorizontalAlignment(SwingConstants.CENTER);
//		mainEmpControlPanel.add(salaryInput);

//		adminSelectionGroup = new ButtonGroup();
//		adminEnabled = new JRadioButton("Enalbed", false);
//		adminEnabled.setPreferredSize(fieldSize_medium);
//		adminEnabled.setFont(normalFont);
//		adminEnabled.setHorizontalAlignment(SwingConstants.CENTER);
//		adminSelectionGroup.add(adminEnabled);
//		mainEmpControlPanel.add(adminEnabled);

//		adminDisabled = new JRadioButton("Disabled", true);
//		adminDisabled.setPreferredSize(fieldSize_medium);
//		adminDisabled.setFont(normalFont);
//		adminDisabled.setHorizontalAlignment(SwingConstants.CENTER);
//		adminSelectionGroup.add(adminDisabled);
//		mainEmpControlPanel.add(adminDisabled);

//		submitButton = new JButton("Add Employee");
//		submitButton.setPreferredSize(fieldSize_large);
//		submitButton.setFont(normalFont);
//		submitButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				if (submitButton.getText().equals("Add Employee")) {
//					if (idInput.getText().equals("") || nameInput.getText().equals("") || ageInput.getText().equals("")
//							|| salaryInput.getText().equals("")) {
//						JOptionPane.showMessageDialog(mainPanel, "Please fill in all fields before submitting!",
//								"Empty Field!", JOptionPane.INFORMATION_MESSAGE);
//					} else {
//						if (adminEnabled.isSelected()) {
//							transferEmployee = new Employee(Integer.parseInt(idInput.getText()), nameInput.getText(),
//									Integer.parseInt(ageInput.getText()),
//									Double.parseDouble(salaryInput.getText().replace(",", ".")), true,
//									new TreeMap<LocalDate, LocalTime[]>());
//						} else if (adminDisabled.isSelected()) {
//							transferEmployee = new Employee(Integer.parseInt(idInput.getText()), nameInput.getText(),
//									Integer.parseInt(ageInput.getText()),
//									Double.parseDouble(salaryInput.getText().replace(",", ".")), false,
//									new TreeMap<LocalDate, LocalTime[]>());
//						}
//						EmployeeList.put(transferEmployee.getID(), transferEmployee);
//						data.saveDataToFiles();
//						JOptionPane.showMessageDialog(mainPanel, "Employee has succesfully been created!",
//								"Successful Creation!", JOptionPane.INFORMATION_MESSAGE);
//
//						clearInputs();
//					}
//				} else if (submitButton.getText().equals("Remove Employee")) {
//					menuChoice = JOptionPane.showConfirmDialog(mainPanel,
//							"Are you sure you want to remove this employee?", "Are you sure?",
//							JOptionPane.OK_CANCEL_OPTION);
//					if (employeeComboBox.getSelectedItem() != null && menuChoice == JOptionPane.OK_OPTION) {
//						for (Employee emp : EmployeeList.values()) {
//							if (employeeComboBox.getSelectedItem().toString().equals(emp.getName())) {
//								transferEmployee = emp;
//							} else {
//								continue;
//							}
//						}
//						employeeComboBox.removeItem(employeeComboBox.getSelectedItem());
//						EmployeeList.remove(transferEmployee.getID());
//						data.saveDataToFiles();
//						JOptionPane.showMessageDialog(mainPanel, "Employee has succesfully been removed!",
//								"Successfull Removal!", JOptionPane.INFORMATION_MESSAGE);
//
//					} else if (menuChoice == JOptionPane.CANCEL_OPTION) {
//						return;
//					}
//				} else if (submitButton.getText().equals("Save Employee")) {
//					if (idInput.getText().equals("") || nameInput.getText().equals("") || ageInput.getText().equals("")
//							|| salaryInput.getText().equals("")) {
//						JOptionPane.showMessageDialog(mainPanel, "Please fill in all fields before submitting!",
//								"Empty Field!", JOptionPane.INFORMATION_MESSAGE);
//					} else {
//						menuChoice = JOptionPane.showConfirmDialog(mainPanel,
//								"Are you sure you want to saveDataToFiles this employee?", "Are you sure?",
//								JOptionPane.OK_CANCEL_OPTION);
//						if (employeeComboBox.getSelectedItem() != null && menuChoice == JOptionPane.OK_OPTION) {
//							for (Employee emp : EmployeeList.values()) {
//								if (employeeComboBox.getSelectedItem().toString().equals(emp.getName())) {
//									transferEmployee = emp;
//								} else {
//									continue;
//								}
//							}
//							EmployeeList.remove(transferEmployee.getID());
//							transferEmployee.setID(Integer.parseInt(idInput.getText()));
//							transferEmployee.setName(nameInput.getText());
//							transferEmployee.setAge(Integer.parseInt(ageInput.getText()));
//							transferEmployee.setSalary(Double.parseDouble(salaryInput.getText()));
//							if (adminEnabled.isSelected()) {
//								transferEmployee.setAdmin(true);
//							} else if (adminDisabled.isSelected()) {
//								transferEmployee.setAdmin(false);
//							}
//							EmployeeList.put(transferEmployee.getID(), transferEmployee);
//							data.saveDataToFiles();
//							JOptionPane.showMessageDialog(mainPanel, "Employee has succesfully been saveDataToFilesd!",
//									"Successful Edit!", JOptionPane.INFORMATION_MESSAGE);
//
//							clearInputs();
//						} else if (menuChoice == JOptionPane.CANCEL_OPTION) {
//							return;
//						}
//					}
//				}
//				pack();
//			}
//		});
//		mainEmpControlPanel.add(submitButton);

//		dateControlPanel = new JPanel();
//		dateControlPanel.setPreferredSize(new Dimension(692, 424));
//		dateControlPanel.setLayout(normalPanelLayout);
//		dateControlPanel.setBackground(Color.WHITE);

		dateSelectionGroup = new ButtonGroup();
//		dateToday = new JRadioButton("Current Day", false);
//		dateToday.setPreferredSize(dateDisplaySize_large);
//		dateToday.setFont(textDisplayFont);
//		dateToday.setHorizontalAlignment(SwingConstants.LEFT);
//		dateToday.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent event) {
//				if (event.getStateChange() == ItemEvent.SELECTED) {
//					dayLabel.setEnabled(true);
//
//					for (Employee emp : EmployeeList.values()) {
//						dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
//						if (emp.getWorkedTime() != null) {
//							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
//								if (entry.getKey().isEqual(time.getCurrentDate())) {
//									dateDisplay.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
//											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
//											+ entry.getValue()[3] + "\n");
//								} else {
//									continue;
//								}
//							}
//						} else {
//							dateDisplay.append("----------------------------------------------------\n");
//							continue;
//						}
//						dateDisplay.append("----------------------------------------------------\n");
//					}
//				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
//					dayLabel.setEnabled(false);
//					dateDisplay.setText("");
//				}
//				pack();
//			}
//		});
//		dateSelectionGroup.add(dateToday);
//		PanelList.testPanel.add(dateToday);

//		dayLabel = new JLabel(time.getCurrentDate().getDayOfWeek().toString());
//		dayLabel.setPreferredSize(dateDisplaySize_large);
//		dayLabel.setFont(textDisplayFont);
//		dayLabel.setEnabled(false);
//		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		PanelList.testPanel.add(dayLabel);

//		dateWeek = new JRadioButton("Current Week", false);
//		dateWeek.setPreferredSize(dateDisplaySize_large);
//		dateWeek.setFont(textDisplayFont);
//		dateWeek.setHorizontalAlignment(SwingConstants.LEFT);
//		dateWeek.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent event) {
//				if (event.getStateChange() == ItemEvent.SELECTED) {
//					weekLabel.setEnabled(true);
//
//					for (Employee emp : EmployeeList.values()) {
//						dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
//						if (emp.getWorkedTime() != null) {
//							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
//								if (entry.getKey().isAfter(time.getWeekStart().minusDays(1))
//										&& entry.getKey().isBefore(time.getWeekEnd().plusDays(1))) {
//									dateDisplay.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
//											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
//											+ entry.getValue()[3] + "\n");
//								} else {
//									continue;
//								}
//							}
//						} else {
//							dateDisplay.append("----------------------------------------------------\n");
//							continue;
//						}
//						dateDisplay.append("----------------------------------------------------\n");
//					}
//				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
//					weekLabel.setEnabled(false);
//					dateDisplay.setText("");
//				}
//				pack();
//			}
//		});
//		dateSelectionGroup.add(dateWeek);
//		PanelList.testPanel.add(dateWeek);

//		weekLabel = new JLabel(time.getWeekStart().toString());
//		weekLabel.setPreferredSize(dateDisplaySize_large);
//		weekLabel.setFont(textDisplayFont);
//		weekLabel.setEnabled(false);
//		weekLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		PanelList.testPanel.add(weekLabel);

//		dateMonth = new JRadioButton("Current Month", false);
//		dateMonth.setPreferredSize(dateDisplaySize_large);
//		dateMonth.setFont(textDisplayFont);
//		dateMonth.setHorizontalAlignment(SwingConstants.LEFT);
//		dateMonth.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent event) {
//				if (event.getStateChange() == ItemEvent.SELECTED) {
//					monthLabel.setEnabled(true);
//
//					for (Employee emp : EmployeeList.values()) {
//						dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
//						if (emp.getWorkedTime() != null) {
//							for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
//								if (entry.getKey().getMonth() == time.getCurrentDate().getMonth()
//										&& entry.getKey().getYear() == time.getCurrentDate().getYear()) {
//									dateDisplay.append(entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-"
//											+ entry.getValue()[1] + ", P-" + entry.getValue()[2] + ", W-"
//											+ entry.getValue()[3] + "\n");
//								} else {
//									continue;
//								}
//							}
//						} else {
//							dateDisplay.append("----------------------------------------------------\n");
//							continue;
//						}
//						dateDisplay.append("----------------------------------------------------\n");
//					}
//				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
//					monthLabel.setEnabled(false);
//					dateDisplay.setText("");
//				}
//				pack();
//			}
//		});
//		dateSelectionGroup.add(dateMonth);
//		PanelList.testPanel.add(dateMonth);

//		monthLabel = new JLabel(time.getCurrentDate().getMonth().toString());
//		monthLabel.setPreferredSize(dateDisplaySize_large);
//		monthLabel.setFont(textDisplayFont);
//		monthLabel.setEnabled(false);
//		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		PanelList.testPanel.add(monthLabel);

//		dateSpecific = new JRadioButton("Specific Date", false);
//		dateSpecific.setPreferredSize(dateDisplaySize_large);
//		dateSpecific.setFont(textDisplayFont);
//		dateSpecific.setHorizontalAlignment(SwingConstants.LEFT);
//		dateSpecific.addItemListener(new ItemListener() {
//			@Override
//			public void itemStateChanged(ItemEvent event) {
//				if (event.getStateChange() == ItemEvent.SELECTED) {
//					fromLabel.setEnabled(true);
//					fromYearInput.setEnabled(true);
//					fromYearInput.setText("yyyy");
//					fromMonthInput.setEnabled(true);
//					fromMonthInput.setText("mm");
//					fromDayInput.setEnabled(true);
//					fromDayInput.setText("dd");
//
//					toLabel.setEnabled(true);
//					toYearInput.setEnabled(true);
//					toYearInput.setText(Integer.toString(time.getCurrentDate().getYear()));
//					toMonthInput.setEnabled(true);
//					toMonthInput.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
//					toDayInput.setEnabled(true);
//					toDayInput.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
//					submitDateButton.setEnabled(true);
//				} else if (event.getStateChange() == ItemEvent.DESELECTED) {
//					fromLabel.setEnabled(false);
//					fromYearInput.setEnabled(false);
//					fromYearInput.setText("yyyy");
//					fromMonthInput.setEnabled(false);
//					fromMonthInput.setText("mm");
//					fromDayInput.setEnabled(false);
//					fromDayInput.setText("dd");
//
//					toLabel.setEnabled(false);
//					toYearInput.setEnabled(false);
//					toYearInput.setText(Integer.toString(time.getCurrentDate().getYear()));
//					toMonthInput.setEnabled(false);
//					toMonthInput.setText(Integer.toString(time.getCurrentDate().getMonthValue()));
//					toDayInput.setEnabled(false);
//					toDayInput.setText(Integer.toString(time.getCurrentDate().getDayOfMonth()));
//					submitDateButton.setEnabled(false);
//					dateDisplay.setText("");
//				}
//				pack();
//			}
//		});
//		dateSelectionGroup.add(dateSpecific);
//		PanelList.testPanel.add(dateSpecific);

		fromLabel = new JLabel("From:");
		fromLabel.setPreferredSize(dateDisplaySize_small);
		fromLabel.setFont(textDisplayFont);
		fromLabel.setEnabled(false);
		PanelList.testPanel.add(fromLabel);

		fromDayInput = new JTextField("dd", 2);
		fromDayInput.setPreferredSize(dateDisplaySize_medium);
		fromDayInput.setFont(textDisplayFont);
		fromDayInput.setEnabled(false);
		fromDayInput.setHorizontalAlignment(SwingConstants.CENTER);
		fromDayInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {				
				if (fromDayInput.getText().length() >= 2 && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(fromDayInput);

		fromMonthInput = new JTextField("mm", 2);
		fromMonthInput.setPreferredSize(dateDisplaySize_medium);
		fromMonthInput.setFont(textDisplayFont);
		fromMonthInput.setEnabled(false);
		fromMonthInput.setHorizontalAlignment(SwingConstants.CENTER);
		fromMonthInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (fromMonthInput.getText().length() >= 2) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(fromMonthInput);

		fromYearInput = new JTextField("yyyy", 3);
		fromYearInput.setPreferredSize(dateDisplaySize_small);
		fromYearInput.setFont(textDisplayFont);
		fromYearInput.setEnabled(false);
		fromYearInput.setHorizontalAlignment(SwingConstants.CENTER);
		fromYearInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (fromYearInput.getText().length() >= 4) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(fromYearInput);

		toLabel = new JLabel("To:");
		toLabel.setPreferredSize(dateDisplaySize_small);
		toLabel.setFont(textDisplayFont);
		toLabel.setEnabled(false);
		PanelList.testPanel.add(toLabel);

		toDayInput = new JTextField(Integer.toString(time.getCurrentDate().getDayOfMonth()), 2);
		toDayInput.setPreferredSize(dateDisplaySize_medium);
		toDayInput.setFont(textDisplayFont);
		toDayInput.setEnabled(false);
		toDayInput.setHorizontalAlignment(SwingConstants.CENTER);
		toDayInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (toDayInput.getText().length() >= 2) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(toDayInput);

		toMonthInput = new JTextField(Integer.toString(time.getCurrentDate().getMonthValue()), 2);
		toMonthInput.setPreferredSize(dateDisplaySize_medium);
		toMonthInput.setFont(textDisplayFont);
		toMonthInput.setEnabled(false);
		toMonthInput.setHorizontalAlignment(SwingConstants.CENTER);
		toMonthInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (toMonthInput.getText().length() >= 2) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(toMonthInput);

		toYearInput = new JTextField(Integer.toString(time.getCurrentDate().getYear()), 3);
		toYearInput.setPreferredSize(dateDisplaySize_small);
		toYearInput.setFont(textDisplayFont);
		toYearInput.setEnabled(false);
		toYearInput.setHorizontalAlignment(SwingConstants.CENTER);
		toYearInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (toYearInput.getText().length() >= 4) {
					e.consume();
				}
				pack();
			}
		});
		PanelList.testPanel.add(toYearInput);

		submitDateButton = new JButton("Submit Date");
		submitDateButton.setPreferredSize(dateDisplaySize_large);
		submitDateButton.setFont(textDisplayFont);
		submitDateButton.setEnabled(false);
		submitDateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					beginDate = LocalDate.of(Integer.parseInt(fromYearInput.getText()),
							Integer.parseInt(fromMonthInput.getText()), Integer.parseInt(fromDayInput.getText()));
					endDate = LocalDate.of(Integer.parseInt(toYearInput.getText()),
							Integer.parseInt(toMonthInput.getText()), Integer.parseInt(toDayInput.getText()));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(mainPanel, "Please enter a correct date!", "Incorrect date",
							JOptionPane.INFORMATION_MESSAGE);
					pack();
					return;
				} finally {
					dateDisplay.setText("");
				}

				for (Employee emp : EmployeeList.values()) {
					dateDisplay.append(emp.getName() + " (" + emp.getID() + ")\n");
					if (!emp.getWorkedTime().isEmpty()) {
						for (Map.Entry<LocalDate, LocalTime[]> entry : emp.getWorkedTime().entrySet()) {
							if (entry.getKey().isAfter(beginDate) && entry.getKey().isBefore(endDate)) {
								dateDisplay.append(
										entry.getKey() + " : \nS-" + entry.getValue()[0] + ", E-" + entry.getValue()[1]
												+ ", P-" + entry.getValue()[2] + ", W-" + entry.getValue()[3] + "\n");
							} else {
								dateDisplay.append("----------------------------------------------------\n");
								continue;
							}
						}
					}
					dateDisplay.append("----------------------------------------------------\n");
				}
				pack();
			}
		});
		PanelList.testPanel.add(submitDateButton);

//		dateDisplayPanel = new JPanel();
//		dateDisplayPanel.setPreferredSize(new Dimension((5 * frameWidth) / 16, frameHeight));
//		dateDisplayPanel.setLayout(normalPanelLayout);
//		dateDisplayPanel.setBackground(Color.WHITE);
//		dateDisplayPanel.setVisible(true);

//		dateDisplay = new JTextArea(17, 20);
//		dateDisplay.setFont(normalFont);
//		dateDisplay.setEditable(false);
//		dateDisplay.setVisible(true);
//		PanelList.testPanel.add(dateDisplay);
//		scrollPane = new JScrollPane(dateDisplay, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		PanelList.testPanel.add(scrollPane);

		/*
		 * mainPanel.add(numpadPanel); mainPanel.add(menuPanel);
		 * mainPanel.add(mainEmpLabelPanel); mainPanel.add(mainEmpControlPanel);
		 * mainPanel.add(dateControlPanel); mainPanel.add(dateDisplayPanel);
		 * getContentPane().add(mainPanel);
		 */
	}

//	private void enableTimeButtons(Boolean start, Boolean end, Boolean startbreak, Boolean endbreak) {
//		startButton.setEnabled(start);
//		endButton.setEnabled(end);
//		startBreakButton.setEnabled(startbreak);
//		endBreakButton.setEnabled(endbreak);
//	}

//	private void displayTimeButtons(Boolean bool) {
//		startButton.setVisible(bool);
//		endButton.setVisible(bool);
//		startBreakButton.setVisible(bool);
//		endBreakButton.setVisible(bool);
//	}
//
//	private void displayAdminButtons(Boolean bool) {
//		addEmpButton.setVisible(bool);
//		removeEmpButton.setVisible(bool);
//		editEmpButton.setVisible(bool);
//		printSheetButton.setVisible(bool);
//		editSheetButton.setVisible(bool);
//		exitButton.setVisible(bool);
//	}

//	private void clearInputs() {
//		idInput.setText("");
//		nameInput.setText("");
//		ageInput.setText("");
//		salaryInput.setText("");
//		adminDisabled.setSelected(true);
//	}

//	private void loadNames() {
//		employeeComboBox.removeAllItems();
//		for (Employee emp : EmployeeList.values()) {
//			employeeComboBox.addItem(emp.getName());
//		}
//
//	}

//	private void addToShift(LocalTime start, LocalTime end, LocalTime paused, LocalTime difference) {
//		newShift = new LocalTime[4];
//		newShift[0] = start;
//		newShift[1] = end;
//		newShift[2] = paused;
//		newShift[3] = difference;
//	}
}
