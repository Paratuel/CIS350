package package1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SubCreateGUI extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private GridLayout layout;

	private JButton okButton;
	private JButton cancelButton;

	private JLabel nameLabel;
	private JLabel dateLabel;
	//private JLabel catLabel;
	private JLabel noteLabel;

	private JTextField nameField;
	private JTextField dateField;
	//private JTextField catField;
	private JTextField noteField;

	private DateFormat format;

	private int WIDTH; 
	private int HEIGHT;

	private boolean isOk;

	private Project aProject;

	/*
	 * Creating the panel for adding a subGroup
	 * @param create is passed into it from the CreateGUI
	 */
	public SubCreateGUI(CreateGUI create) {
		super(create, true);
		WIDTH = 400;
		HEIGHT = 400;
		isOk = false;
		aProject = new Project();
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		nameLabel = new JLabel("Name of Sub-Project:");
		dateLabel = new JLabel("Due date (MM/DD/YYYY):");
		//catLabel = new JLabel("Category:");
		noteLabel = new JLabel("Notes:");
		nameField = new JTextField(20);
		nameField.setText("Untitled");
		dateField = new JTextField(10);
		dateField.setText("1/1/2015");
		//catField = new JTextField(20);
		//catField.setText("Untitled");
		noteField = new JTextField(70);
		noteField.setText("");

		add(nameLabel);
		add(nameField);
		add(dateLabel);
		add(dateField);
		//add(catLabel);
		//add(catField);
		add(noteLabel);
		add(noteField);
		add(cancelButton);
		add(okButton);

		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("New Project");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * Assigns actions to buttons and JMenuItems
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			dispose();
		}
		if (e.getSource() == okButton) {
			try {
				String newName = nameField.getText();
				String newDateString = dateField.getText();
				//String newCat = catField.getText();
				String newNote = noteField.getText();
				Date newDate = format.parse(newDateString);
				GregorianCalendar newDate2 = new GregorianCalendar();
				newDate2.setTime(newDate);
				aProject.setName(newName);
				aProject.setDueDate(newDate2);
				aProject.setNotes(newNote);
				//aProject.setCategory(newCat);
				isOk = true;
				dispose();
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
	}

	/*
	 * Checking the status
	 * @return isOk if true
	 */
	public boolean isOkPressed() {
		return isOk;
	}

	/*
	 * Checks which project
	 * @return aProject is the project
	 */
	public Project whatProject() {
		return aProject;
	}
	
	public void clear(){
		nameField.setText(null);
		dateField.setText(null);
		noteField.setText(null);
	}
}
