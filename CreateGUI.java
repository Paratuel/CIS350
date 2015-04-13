package package1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


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
	private JTextField noteField;

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
	private boolean isOk;

	private boolean ok;

	private boolean cancel;

	private boolean isSubOkay;

	private boolean subUsed;

	private ProjectGUI parent;
	
	private String month;
	
	private String day;
	
	private String year;
	
	private GregorianCalendar today = new GregorianCalendar();

	/**
	 * Project for creating new projects.
	 */
	private Project aProject;

	/**
	 * Sets up the panel for adding a Project.
	 * @param parent is the parent GUI
	 */
	public CreateGUI(ProjectGUI parent){
		super(parent, true);
		String a = null;
		setTitle("New Project");
		setupDialog(a);
	}
	public CreateGUI(ProjectGUI parent, String projName){
		super(parent, true);
		this.projectName = projName;
		setTitle("Splitting a Project");
		setupDialog(projName);
	}
	public CreateGUI(ProjectGUI parent, String n, String s, GregorianCalendar d, int r, 
			String notes){
		super(parent, true);
		this.projectName = n;
		this.subName = s;
		this.dueDate = d;
		this.reminder = r;
		this.notes = notes;
		setTitle("Edit a Project");
		editDialog(projectName, subName, dueDate, reminder, notes);
	}

	public void setupDialog(String a) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
				
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		formatter.setCalendar(today);
		String currentDate = formatter.format(today.getTime());
		
		dateField.setText(currentDate);
		noteField.setText("");
		reminderField.setText("0");
		//subField.setText(notThis);

		isOk = false;
		isSubOkay = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		aProject = new Project();

		WIDTH = 400;
		HEIGHT = 400;
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		if(a != null) {
			nameField.setText(projectName);
			subField = new JTextField(3);
			subField.setText("");
			subLabel = new JLabel("Sub-Project");
			add(nameLabel);
			add(nameField);
			add(subLabel);
			add(subField);
			subUsed = true;
		}else{
			nameField.setText("");
			//subField.setText(notThis);
			add(nameLabel);
			add(nameField);
		}

		add(dateLabel);
		add(dateField);
		add(remLabel);
		add(reminderField);
		add(noteLabel);
		add(noteField);

		add(cancelButton);
		add(okButton);

		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true); 
	}

	public void editDialog(String a, String b, GregorianCalendar c, int d, String e) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);

		dateField.setText(Utilities.gToString(c));
		noteField.setText(e);
		reminderField.setText(String.valueOf(d));


		isOk = false;
		isSubOkay = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		//aProject = new Project();

		WIDTH = 400;
		HEIGHT = 400;
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this); 
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		if(b != null){
			nameField.setText(a);
			subField = new JTextField(3);
			subField.setText(b);
			subLabel = new JLabel("Sub-Project");
			add(nameLabel);
			add(nameField);
			add(subLabel);
			add(subField);
			subUsed = true;
		}else{
			nameField.setText(a);
			//subField.setText(notThis);
			add(nameLabel);
			add(nameField);
		}

		add(dateLabel);
		add(dateField);
		add(remLabel);
		add(reminderField);
		add(noteLabel);
		add(noteField);

		add(cancelButton);
		add(okButton);

		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true); 
	}

	/*
	 * Assigns actions to buttons and JMenuItems
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
			if(isValidField()){
				isOk = true;
				cancel = false;
				setVisible(false);
			}else{
//				JOptionPane.showMessageDialog(null, "Some fields are not entered correctly "
//						+ "or missing information.", "Input Validation", JOptionPane.ERROR_MESSAGE);
			}
			return;
			//			try {
			//				String newName = nameField.getText();
			//				String newDateString = dateField.getText();
			//				int newReminder = getReminder();
			//				String newNote = noteField.getText();
			//				Date newDate = format.parse(newDateString);
			//				GregorianCalendar newDate2 = new GregorianCalendar();
			//				int reminder = Integer.parseInt(reminderField.getText());
			//				newDate2.setTime(newDate);
			//				aProject.setName(newName);
			//				aProject.setDueDate(newDate2);
			//				aProject.setNotes(newNote);
			//				aProject.setReminder(reminder);
			//				aProject.setReminder(newReminder);
			//				isOk = true;
			//				dispose();
			//				int num = Integer.parseInt(subField.getText());
			//				while (num > 0) {
			//					SubCreateGUI subCreate = new SubCreateGUI(this);
			//					if (subCreate.isOkPressed()) {
			//						aProject.addItems(subCreate.whatProject());
			//					}
			//					num--;
			//				}
			//			} catch (Exception x) {
			//				x.printStackTrace();
			//			}
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
		if(subUsed == true){
			return subField.getText();
		}
		return null;
	}
	

	public boolean isValidField(){
		if(nameField.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Name Wasn't Entered.", "Input Validation",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(subUsed == true){
			if(subField.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Split Name Wasn't Entered.", "Input Validation",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		GregorianCalendar today = new GregorianCalendar();
		if(getDueDate().compareTo(today) < 0){
			JOptionPane.showMessageDialog(null, 
					"You set the Due Date before today's date.",
					"Input Validation", JOptionPane.ERROR_MESSAGE);
		
			return false;
			
		}
		else if (getDueDate().compareTo(today) == 0){
			JOptionPane.showMessageDialog(null,"Due date is today");
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
