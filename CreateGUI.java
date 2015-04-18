package package1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


/**
 * Creates the GUI for making a new Project.
 * @author  Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class CreateGUI extends JDialog implements ActionListener {

	/**
	 * serialVersionUID. 
	 */
	private static final long serialVersionUID = 1L;

	private String projectName = null;
	private String subName = null;
	private GregorianCalendar dueDate;
	private int reminder;
	private String notes;
	private String notThis = "USED FOR SPLITTING PROJECT";

	/**
	 * Creates the layout for the text fields and buttons.
	 */
	private GridLayout layout;

	/**
	 * Confirms choice and creates project.
	 */
	private JButton okButton; 

	/**
	 * Cancels project creation and closes the window.
	 */
	private JButton cancelButton;

	/**
	 * JLabel for the where the name is entered.
	 */
	private JLabel nameLabel;

	/**
	 * JLabel for the where the due date is entered.
	 */
	private JLabel dateLabel;

	/**
	 * JLabel for the where the note is entered.
	 */
	private JLabel noteLabel;

	/**
	 * JLabel for the where the reminder is entered.
	 */
	private JLabel remLabel;

	/**
	 * JLabel for the where the number of sub-projects is entered.
	 */
	private JLabel subLabel;

	/**
	 * JTextField for the where the name is entered.
	 */
	private JTextField nameField;

	/**
	 * JTextField for the where the due date is entered.
	 */
	private JTextField dateField;

	/**
	 * JTextField for the where the note is entered.
	 */
	private JTextArea noteField;

	/**
	 * JTextField for the where the number of sub-projects is entered.
	 */
	private JTextField subField;

	/**
	 * JTextField for the where the reminder is entered.
	 */
	private JTextField reminderField;

	/**
	 * Formatting  for the due date.
	 */
	private DateFormat format;

	/**
	 * Width of the field.
	 */
	private int WIDTH;

	/**
	 * Height of the field.
	 */
	private int HEIGHT;

	/**
	 * Checks if operation is allowed.
	 */
	private boolean isOk, isSubOk, subUsed, isCompleteOk, isDeleteOk;

	private boolean ok;

	private boolean cancel;

	private boolean isSubOkay;

	private ProjectGUI parent;
	
	private String month;
	
	private String day;
	
	private String year;
	private JButton complete, delete, sub;
	private JPanel panelOne, panelTwo, panelThree, panelFour;
	
	
	private GregorianCalendar today = new GregorianCalendar();

	/**
	 * Project for creating new projects.
	 */
	private Project aProject;

	/**
	 * Sets up the panel for adding a Project.
	 * @param parent is the parent GUI
	 */
	public CreateGUI(ProjectGUI parent) {
		super(parent, true);
		String a = null;
		setTitle("New Project");
		setupDialog(a);
	}
	public CreateGUI(ProjectGUI parent, String projName, GregorianCalendar date, 
			int rem, String n) {
		super(parent, true);
		subUsed = false;
		this.projectName = projName;
		this.dueDate = date;
		this.reminder = rem;
		this.notes = n;
		setTitle("Editing");
		editDialog(projectName, dueDate, reminder, notes);
	}
	
	public CreateGUI(ProjectGUI parent, String n, String s, 
			GregorianCalendar d, int r, String notes){
		super(parent, true);
		this.projectName = n;
		this.subName = s;
		this.dueDate = d;
		this.reminder = r;
		this.notes = notes;
		setTitle("Edit a Project");
		subDialog(projectName, subName, dueDate, reminder, notes);
	}

	public void setupDialog(String a) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//Setting up panel 1:  Holds name, date, reminder
		panelOne = new JPanel(new GridLayout(3,1));
		//Setting up panel 2:  Holds notes
		panelTwo = new JPanel(new BorderLayout());
		//panelTwo.setPreferredSize(new Dimension(400, 400));
		//Setting up panel 3:  Holds cancel, ok, delete and complete
		panelThree = new JPanel(new GridLayout(1,2));
		//Setting up panel 4:  Holds split project
		panelFour = new JPanel(new GridLayout(1,1));
		
		nameField = new JTextField(20);
		nameField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Name of Project"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                nameField.getBorder()));
		dateField = new JTextField(10);
		dateField.setBorder(
				BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Due date (MM/DD/YYYY)"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                dateField.getBorder()));
		noteField = new JTextArea("");
		noteField.setLineWrap(true);
		noteField.setWrapStyleWord(true);
		noteField.setBounds(5,35,385,330);
		JScrollPane scroll = new JScrollPane(noteField, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(20, 30, 100, 40);
		panelTwo.add(scroll);
		noteField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Notes"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                noteField.getBorder()));
		reminderField = new JTextField(20);
		reminderField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Reminder:  How many days do you need?"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                reminderField.getBorder()));
		
		

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		formatter.setCalendar(today);
		String currentDate = formatter.format(today.getTime());

		dateField.setText(currentDate);
		
		noteField.setText("");
		reminderField.setText("0");
	
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();

		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText("");
		panelOne.add(nameField);
		panelOne.add(dateField);
		panelOne.add(reminderField);
		panelThree.add(cancelButton);
		panelThree.add(okButton);
		add(panelOne, BorderLayout.NORTH);
		
		add(panelTwo, BorderLayout.CENTER);
		add(panelThree, BorderLayout.SOUTH);
		setSize(400, 400);

		setLocationRelativeTo(null);
		setVisible(true); 
	}

	public void editDialog(String name, GregorianCalendar date, int rem, String notes){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//Setting up panel 1:  Holds name, date, reminder
		panelOne = new JPanel(new GridLayout(3,1));
		//Setting up panel 2:  Holds notes
		panelTwo = new JPanel(new GridLayout(1,2));
		panelTwo.setPreferredSize(new Dimension(400,100));
		//Setting up panel 3:  Holds cancel, ok, delete and complete
		panelThree = new JPanel(new GridLayout(2,2));
		//Setting up panel 4:  Holds split project
		panelFour = new JPanel(new GridLayout(1,1));

		nameField = new JTextField(20);
		nameField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Name of Project"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                nameField.getBorder()));
		dateField = new JTextField(10);
		dateField.setBorder(
				BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Due date (MM/DD/YYYY)"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                dateField.getBorder()));
		noteField = new JTextArea("");
		noteField.setLineWrap(true);
		noteField.setWrapStyleWord(true);
		noteField.setBounds(5,35,385,330);
		JScrollPane scroll = new JScrollPane(noteField, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(20, 30, 100, 40);
		panelTwo.add(scroll);

		noteField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Notes"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                noteField.getBorder()));
		reminderField = new JTextField(20);
		reminderField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Reminder:  How many days do you need?"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                reminderField.getBorder()));
		
		dateField.setText(Utilities.gToString(date));
		noteField.setText(notes);
		reminderField.setText(String.valueOf(rem));
		
		//isOk = false;
		//isSubOk = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();
	
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);

		nameField.setText(projectName);
		subField = new JTextField(3);
		subField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Sub Project Name"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                subField.getBorder()));
		subField.setText(" ");
		panelOne.add(nameField);
		panelOne.add(dateField);
		panelOne.add(reminderField);
		panelThree.add(cancelButton);
		panelThree.add(okButton);
		panelThree.add(delete);
		panelThree.add(complete);
		panelFour.add(sub);
		add(panelOne, BorderLayout.NORTH);
		add(panelTwo, BorderLayout.CENTER);
		JPanel threeFour = new JPanel(new BorderLayout());
		threeFour.add(panelThree, BorderLayout.NORTH);
		threeFour.add(panelFour, BorderLayout.SOUTH);
		add(threeFour, BorderLayout.SOUTH);
		
		setSize(400, 450);
		//subUsed = true;

		setLocationRelativeTo(null);
		setVisible(true); 
	}

	public void subDialog(String a, String b, GregorianCalendar c, int d, String e) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//Setting up panel 1:  Holds name, date, reminder
		panelOne = new JPanel(new GridLayout(4,2));
		//Setting up panel 2:  Holds notes
		panelTwo = new JPanel(new GridLayout(1,2));
		panelTwo.setPreferredSize(new Dimension(400,100));
		//Setting up panel 3:  Holds cancel, ok, delete and complete
		panelThree = new JPanel(new GridLayout(2,2));
		//Setting up panel 4:  Holds split project
		panelFour = new JPanel(new GridLayout(1,1));

		nameField = new JTextField(20);
		nameField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Name of Project"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                nameField.getBorder()));
		dateField = new JTextField(10);
		dateField.setBorder(
				BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Due date (MM/DD/YYYY)"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                dateField.getBorder()));
		noteField = new JTextArea("");
		//final Document document = noteField.getDocument();
		noteField.setLineWrap(true);
		noteField.setWrapStyleWord(true);
		noteField.setBounds(5,35,385,330);
		JScrollPane scroll = new JScrollPane(noteField, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(20, 30, 100, 40);
		panelTwo.add(scroll);

		noteField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Notes"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                noteField.getBorder()));
		reminderField = new JTextField(20);
		reminderField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Reminder:  How many days do you need?"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                reminderField.getBorder()));

		dateField.setText(Utilities.gToString(c));
		noteField.setText(e);
		reminderField.setText(String.valueOf(d));


		//isOk = false;
		//isSubOkay = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		//aProject = new Project();

		WIDTH = 400;
		HEIGHT = 475;
		//layout = new GridLayout(7, 2);
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		delete = new JButton("Delete");
		delete.addActionListener(this);
		complete = new JButton("Complete");
		complete.addActionListener(this);
		sub = new JButton("Split Project");
		sub.addActionListener(this);
		cancelButton.addActionListener(this);
		
		nameField.setText(projectName);
		subField = new JTextField(3);
		subField.setBorder(
	            BorderFactory.createCompoundBorder(
	                BorderFactory.createCompoundBorder(
	                                BorderFactory.createTitledBorder("Sub Project Name"),
	                                BorderFactory.createEmptyBorder(5,5,5,5)),
	                subField.getBorder()));
		if (b != null) {		
			subField.setText(b);
			subLabel = new JLabel("Sub-Project");
			panelOne.add(nameField);
			panelOne.add(subField);
			panelOne.add(dateField);
			panelOne.add(reminderField);
			
			panelThree.add(cancelButton);
			panelThree.add(okButton);
			panelThree.add(delete);
			panelThree.add(complete);
			//southPanel.add(sub);

			add(panelOne, BorderLayout.NORTH);
			add(panelTwo, BorderLayout.CENTER);
			add(panelThree, BorderLayout.SOUTH);
			//add(southPanel, BorderLayout.SOUTH);
			subUsed = true;
		} else {
			subField.setText("");
			panelOne.add(nameField);
			panelOne.add(subField);
			panelOne.add(dateField);
			panelOne.add(reminderField);
			panelThree.add(cancelButton);
			panelThree.add(okButton);
			panelThree.add(delete);
			panelThree.add(complete);
			//southPanel.add(sub);

			add(panelOne, BorderLayout.NORTH);
			add(panelTwo, BorderLayout.CENTER);
			add(panelThree, BorderLayout.SOUTH);
			//add(southPanel, BorderLayout.SOUTH);
			subUsed = true;
		}

		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true); 
	}

	/**
	 * Assigns actions to buttons and JMenuItems.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			isOk = false;
			cancel = true;
			setVisible(false);
			clear();
			return;
		}
		if (e.getSource() == okButton) {
			if (isValidField()) {
				isOk = true;
				cancel = false;
				setVisible(false);
			}
			//return;
		}
		if(e.getSource() == delete){
			if(isValid()){
				isDeleteOk = true;
				cancel = false;
				setVisible(false);
			}
			return;
		}
		if(e.getSource() == complete){
			if (isValidField()){
				isCompleteOk = true;
				cancel = false;
				setVisible(false);
			}
			//return;
		}
		if(e.getSource() == sub){
			if (isValidField()){
				isSubOk = true;
				cancel = false;
				setVisible(false);
			}
			//return;
		}
	}

	public boolean isCancel(){
		return cancel;
	}

	/*
	 * Checks status
	 * @return isOk if true
	 */
	public boolean isOkPressed() {
		return isOk;
	}

	public boolean isCompletePressed(){
		return isCompleteOk;
	}

	public boolean isDeletePressed(){
		return isDeleteOk;
	}

	public boolean isSubPressed(){
		return isSubOk;
	}

	public String getName() {
		return nameField.getText();
	}

	public GregorianCalendar getDueDate() {
		return Utilities.strToGregCalendar(dateField.getText());
	}

	public String getNotes() {
		return noteField.getText();
	}

	public int getReminder() {
		return Integer.parseInt(reminderField.getText());
	}

	public String getSub() {
		if (subUsed) {
			return subField.getText();
		}
		return null;
	}


	public boolean isValidField() {
		if (nameField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, 
					"Name Wasn't Entered.", "Input Validation",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (subUsed) {
			if (subField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, 
						"Subproject Name Wasn't Entered.", "Input Validation",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		GregorianCalendar today = new GregorianCalendar();
		today.set(GregorianCalendar.HOUR_OF_DAY, 0);
		today.set(GregorianCalendar.MINUTE, 0);
		today.set(GregorianCalendar.SECOND, 0);
		today.set(GregorianCalendar.MILLISECOND, 0);
		GregorianCalendar compare = getDueDate();
		compare.set(GregorianCalendar.HOUR_OF_DAY, 0);
		compare.set(GregorianCalendar.MINUTE, 0);
		compare.set(GregorianCalendar.SECOND, 0);
		compare.set(GregorianCalendar.MILLISECOND, 0);
		if(compare.compareTo(today) < 0) {
			JOptionPane.showMessageDialog(null, 
					"You set the Due Date before today's date.",
					"Input Validation", JOptionPane.ERROR_MESSAGE);
			return false;

		}


		return true;
	}

	/**
	 * Checks which Project
	 * @return aProject is the Project
	 */
	public Project whatProject() {
		return aProject;
	}

	public void clear() {
		nameField.setText(null);
		dateField.setText(null);
		noteField.setText(null);
		reminderField.setText(null);
		if (subUsed == true) {
			subField.setText(null);
		}
	}
}
