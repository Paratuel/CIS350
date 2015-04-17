package package1;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
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
	private boolean isOk, isSubOk, subUsed, isCompleteOk, isDeleteOk;

	private boolean ok;

	private boolean cancel;

	private boolean isSubOkay;

	private ProjectGUI parent;
	
	private String month;
	
	private String day;
	
	private String year;
	private JButton complete, delete, sub;
	private JPanel northPanel, centralPanel, southPanel, verySouthPanel;
	
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

		northPanel = new JPanel(new GridLayout(7,2));
		southPanel = new JPanel(new GridLayout(1,1));
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
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText("");
		northPanel.add(nameLabel);
		northPanel.add(nameField);
		northPanel.add(dateLabel);
		northPanel.add(dateField);
		northPanel.add(remLabel);
		northPanel.add(reminderField);
		northPanel.add(noteLabel);
		northPanel.add(noteField);
		northPanel.add(cancelButton);
		northPanel.add(okButton);
		add(northPanel, BorderLayout.NORTH);
		setSize(400, 175);

		setLocationRelativeTo(null);
		setVisible(true); 
	}

	public void editDialog(String name, GregorianCalendar date, int rem, String notes){
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		northPanel = new JPanel(new GridLayout(7,2));
		southPanel = new JPanel(new GridLayout(1,1));
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);
		
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
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");

		nameField.setText(projectName);
		subField = new JTextField(3);
		subField.setText(" ");
		subLabel = new JLabel("Split Project");
		northPanel.add(nameLabel);
		northPanel.add(nameField);
		northPanel.add(dateLabel);
		northPanel.add(dateField);
		northPanel.add(remLabel);
		northPanel.add(reminderField);
		northPanel.add(noteLabel);
		northPanel.add(noteField);
		//northPanel.add(subLabel);
		//northPanel.add(subField);
		northPanel.add(cancelButton);
		northPanel.add(okButton);
		northPanel.add(delete);
		northPanel.add(complete);
		southPanel.add(sub);
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		setSize(400, 225);
		//subUsed = true;

		setLocationRelativeTo(null);
		setVisible(true); 
	}

	public void subDialog(String a, String b, GregorianCalendar c, int d, String e) {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		northPanel = new JPanel(new GridLayout(7,2));
		nameField = new JTextField(20);
		dateField = new JTextField(10);
		noteField = new JTextField(70);
		reminderField = new JTextField(20);

		dateField.setText(Utilities.gToString(c));
		noteField.setText(e);
		reminderField.setText(String.valueOf(d));


		//isOk = false;
		//isSubOkay = true;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		//aProject = new Project();

		WIDTH = 400;
		HEIGHT = 225;
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
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		noteLabel = new JLabel("Notes:");
		remLabel = new JLabel("Reminder (Days Needed):");
		
		nameField.setText(projectName);
		subField = new JTextField(3);
		if (b != null) {		
			subField.setText(b);
			subLabel = new JLabel("Sub-Project");
			northPanel.add(nameLabel);
			northPanel.add(nameField);
			northPanel.add(subLabel);
			northPanel.add(subField);
			northPanel.add(dateLabel);
			northPanel.add(dateField);
			northPanel.add(remLabel);
			northPanel.add(reminderField);
			northPanel.add(noteLabel);
			northPanel.add(noteField);
		
			southPanel = new JPanel(new GridLayout(1,1));
			northPanel.add(cancelButton);
			northPanel.add(okButton);
			northPanel.add(delete);
			northPanel.add(complete);
			//southPanel.add(sub);

			add(northPanel, BorderLayout.NORTH);
			//add(southPanel, BorderLayout.SOUTH);
			subUsed = true;
		} else {
			subField.setText("");
			subLabel = new JLabel("Split Project");
			northPanel.add(nameLabel);
			northPanel.add(nameField);
			northPanel.add(subLabel);
			northPanel.add(subField);
			northPanel.add(dateLabel);
			northPanel.add(dateField);
			northPanel.add(remLabel);
			northPanel.add(reminderField);
			northPanel.add(noteLabel);
			northPanel.add(noteField);
		
			southPanel = new JPanel(new GridLayout(1,1));
			northPanel.add(cancelButton);
			northPanel.add(okButton);
			northPanel.add(delete);
			northPanel.add(complete);
			//southPanel.add(sub);

			add(northPanel, BorderLayout.NORTH);
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
			return;
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
