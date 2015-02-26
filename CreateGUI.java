package package1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateGUI extends JDialog implements ActionListener {

	
	private static final long serialVersionUID = 1L;
	
	private GridLayout layout;
	
	private JButton okButton, cancelButton;
	
	private JLabel nameLabel, dateLabel, catLabel, noteLabel;
	
	private JTextField nameField, dateField, catField, noteField;
	
	private DateFormat format;

	private int WIDTH, HEIGHT;
	
	private boolean isOk;
	
	private Project aProject;
	
	public CreateGUI(ProjectGUI parent, Project proj){
		super(parent, true);
		isOk = false;
		aProject = proj;
		format = DateFormat.getDateInstance(DateFormat.SHORT);
		WIDTH = 400;
		HEIGHT = 400;
		layout = new GridLayout(7,2);
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		nameLabel = new JLabel("Name of Project:");
		dateLabel = new JLabel("Enter date in the form MM/DD/YYYY:");
		catLabel = new JLabel("Category:");
		noteLabel = new JLabel("Notes:");
		nameField = new JTextField(20);
		nameField.setText(proj.getName());
		dateField = new JTextField(10);
		dateField.setText(format.format(proj.getDueDate()));
		catField = new JTextField(20);
		catField.setText(proj.getCategory());
		noteField = new JTextField(70);
		noteField.setText(proj.getNotes());
		
		add(nameLabel);
		add(nameField);
		add(dateLabel);
		add(dateField);
		add(catLabel);
		add(catField);
		add(noteLabel);
		add(noteField);
		add(cancelButton);
		add(okButton);
		
		setLayout(layout);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("New Project");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton){
			dispose();
		}
		if(e.getSource() == okButton){
			try{
				String newName = nameField.getText();
				String newDateString = dateField.getText();
				String newCat = catField.getText();
				String newNote = noteField.getText();
				Date newDate = format.parse(newDateString);
				aProject.setName(newName);
				aProject.setDueDate(newDate);
				aProject.setNotes(newNote);
				aProject.setCategory(newCat);
				isOk = true;
				dispose();
			}catch(Exception x){
				x.printStackTrace();
			}
		}
		
		
	}
	
	public boolean isOkPressed(){
		return isOk;
	}
	public Project whatProject(){
		return aProject;
	}

}
