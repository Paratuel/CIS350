package package1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateGUI extends JDialog implements ActionListener {

  private static final long serialVersionUID = 1L;

  private GridLayout layout;

  private JButton okButton; 
  private JButton cancelButton;
  
  private JLabel nameLabel;
  private JLabel dateLabel;
  private JLabel catLabel; 
  private JLabel noteLabel;
  private JLabel subLabel;

  private JTextField nameField;
  private JTextField dateField;
  private JTextField catField;
  private JTextField noteField;
  private JTextField subField;

  private DateFormat format;

  private int WIDTH;
  private int HEIGHT;

  private boolean isOk;
  private boolean isSubOk;

  private Project aProject;
  
  /*
  * Sets up the panel for adding a project
  * @param ProjectGUI is the parent GUI
  * @param Project proj is passing in the array from ProjectGUI
  */
  public CreateGUI(ProjectGUI parent, Project proj){
    super(parent, true);
    isOk = false;
    isSubOk = true;
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
    dateLabel = new JLabel("Due date (MM/DD/YYYY):");
    catLabel = new JLabel("Category:");
    noteLabel = new JLabel("Notes:");
    subLabel = new JLabel("Number of sub projects:");
    nameField = new JTextField(20);
    nameField.setText(proj.getName());
    dateField = new JTextField(10);
    dateField.setText(format.format(proj.getDueDate()));
    catField = new JTextField(20);
    catField.setText(proj.getCategory());
    noteField = new JTextField(70);
    noteField.setText(proj.getNotes());
    subField = new JTextField(3);
    subField.setText("0");

    add(nameLabel);
    add(nameField);
    add(dateLabel);
    add(dateField);
    add(catLabel);
    add(catField);
    add(noteLabel);
    add(noteField);
    add(subLabel);
    add(subField);
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
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cancelButton) {
      dispose();
    }
    if (e.getSource() == okButton) {
      try {
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
        int num = Integer.parseInt(subField.getText());
        while (num > 0) {
          SubCreateGUI subCreate = new SubCreateGUI(this);
          if (subCreate.isOkPressed()) {
            aProject.addItems(subCreate.whatProject());
          }
          num--;
        }
      } catch (Exception x) {
        x.printStackTrace();
      }
    }
  }
  
  /*
  * Checks status
  * @return isOk if true
  */
  public boolean isOkPressed() {
    return isOk;
  }
  
  /*
  * Checks which project
  * @return aProject is the Project
  */
  public Project whatProject() {
    return aProject;
  }
}
