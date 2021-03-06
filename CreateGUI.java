package packageproj;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Creates the GUI for making a new Project.
 * @author  Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class CreateGui extends JDialog implements ActionListener {

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private String projectName = null;
  private String subName = null;
  private GregorianCalendar dueDate;
  private int reminder;
  private String notes;

  /** Confirms choice and creates project. */
  private JButton okButton; 

  /** Cancels project creation and closes the window. */
  private JButton cancelButton;

  /** JTextField for the where the name is entered. */
  private JTextField nameField;

  /** JTextField for the where the due date is entered. */
  private JTextField dateField;

  /** JTextField for the where the note is entered. */
  private JTextArea noteField;

  /** JTextField for the where the number of sub-projects is entered. */
  private JTextField subField;

  /** JTextField for the where the reminder is entered. */
  private JTextField reminderField;

  /** Formatting  for the due date. */
  private DateFormat format;

  /** Width of the field. */
  private int width;

  /** Height of the field. */
  private int height;

  /** Checks if operation is allowed. */
  private boolean isOk;

  private boolean subUsed;

  private boolean isCompleteOk;

  private boolean isDeleteOk;

  private boolean cancel;

  private boolean isSubOk;

  private JButton complete;

  private JButton delete;

  private JButton sub;

  private JPanel panelOne;

  private JPanel panelTwo;

  private JPanel panelThree;

  private JPanel panelFour;


  private GregorianCalendar today = new GregorianCalendar();

  /**
   * Project for creating new projects.
   */
  private Project theProject;

  /**
   * Sets up the panel for adding a Project.
   * @param parent is the parent GUI
   */
  public CreateGui(ProjectGui parent) {
    super(parent, true);
    String temp = null;
    setTitle("New Project");
    setupDialog(temp);
  }

  /**
   * Sets up the panel for adding a Project.
   * @param parent is the parent GUI
   * @param projName is the project name
   * @param date is the due date
   * @param rem is the reminder
   * @param note is the note
   */
  public CreateGui(ProjectGui parent, String projName, GregorianCalendar date, 
      int rem, String note) {
    super(parent, true);
    subUsed = false;
    this.projectName = projName;
    this.dueDate = date;
    this.reminder = rem;
    this.notes = note;
    setTitle("Editing");
    editDialog(projectName, dueDate, reminder, notes);
  }

  /**
   * Sets up the panel for adding a Project.
   * @param parent is the parent GUI
   * @param name is the project name
   * @param sname is the sub project name
   * @param dd is the due date
   * @param rr is the reminder
   * @param notes is the notes
   */
  public CreateGui(ProjectGui parent, String name, String sname, 
      GregorianCalendar dd, int rr, String notes) {
    super(parent, true);
    this.projectName = name;
    this.subName = sname;
    this.dueDate = dd;
    this.reminder = rr;
    this.notes = notes;
    setTitle("Edit a Project");
    subDialog(projectName, subName, dueDate, reminder, notes);
  }

  /**
   * Sets up the display window.
   * @param name project name
   */
  public void setupDialog(String name) {
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
                BorderFactory.createTitledBorder("Reminder: How many days do you need?"),
                BorderFactory.createEmptyBorder(5,5,5,5)),
                reminderField.getBorder()));

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
    formatter.setCalendar(today);
    String currentDate = formatter.format(today.getTime());

    dateField.setText(currentDate);

    noteField.setText("");
    reminderField.setText("0");

    format = DateFormat.getDateInstance(DateFormat.SHORT);
    theProject = new Project();

    okButton = new JButton("OK");
    okButton.addActionListener(this); 
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    delete = new JButton("Delete");
    delete.addActionListener(this);
    complete = new JButton("Complete");
    complete.addActionListener(this);
    sub = new JButton("Sub-Project");
    sub.addActionListener(this);

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

  /**
   * Method for the edit window.
   * @param name the project's current name
   * @param date the project's current due date
   * @param rem the project's current reminder
   * @param notes the project's current notes
   */
  public void editDialog(String name, GregorianCalendar date, int rem, String notes) {
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

    dateField.setText(Utilities.gtoString(date));
    noteField.setText(notes);
    reminderField.setText(String.valueOf(rem));

    format = DateFormat.getDateInstance(DateFormat.SHORT);
    theProject = new Project();

    okButton = new JButton("OK");
    okButton.addActionListener(this); 
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    delete = new JButton("Delete");
    delete.addActionListener(this);
    complete = new JButton("Complete");
    complete.addActionListener(this);
    sub = new JButton("Sub Project");
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

    setLocationRelativeTo(null);
    setVisible(true); 
  }

  /**
   * method for dialog box with sub option.
   * @param nam project name
   * @param snam project sub name
   * @param dd project due date
   * @param rem projcet reminder
   * @param note project notes
   */
  public void subDialog(String nam, String snam, GregorianCalendar dd, int rem, String note) {
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

    dateField.setText(Utilities.gtoString(dd));
    noteField.setText(note);
    reminderField.setText(String.valueOf(rem));

    format = DateFormat.getDateInstance(DateFormat.SHORT);

    width = 400;
    height = 475;
    okButton = new JButton("OK");
    okButton.addActionListener(this); 
    cancelButton = new JButton("Cancel");
    delete = new JButton("Delete");
    delete.addActionListener(this);
    complete = new JButton("Complete");
    complete.addActionListener(this);
    sub = new JButton("Sub Project");
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
    if (snam != null) {
      subField.setText(snam);
      panelOne.add(nameField);
      panelOne.add(subField);
      panelOne.add(dateField);
      panelOne.add(reminderField);

      panelThree.add(cancelButton);
      panelThree.add(okButton);
      panelThree.add(delete);
      panelThree.add(complete);

      add(panelOne, BorderLayout.NORTH);
      add(panelTwo, BorderLayout.CENTER);
      add(panelThree, BorderLayout.SOUTH);
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

      add(panelOne, BorderLayout.NORTH);
      add(panelTwo, BorderLayout.CENTER);
      add(panelThree, BorderLayout.SOUTH);
      subUsed = true;
    }

    setSize(width, height);
    setLocationRelativeTo(null);
    setVisible(true); 
  }

  /**
   * Assigns actions to buttons and JMenuItems.
   */
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == cancelButton) {
      isOk = false;
      cancel = true;
      setVisible(false);
      clear();
      return;
    }
    if (event.getSource() == okButton) {
      if (isValidField()) {
        isOk = true;
        cancel = false;
        setVisible(false);
      }
    }
    if (event.getSource() == delete) {
      isDeleteOk = true;
      cancel = false;
      setVisible(false);

      return;
    }

    if (event.getSource() == complete) {
      isCompleteOk = true;
      cancel = false;
      setVisible(false);
    }

    if (event.getSource() == sub) {
      if (isValidField()) {
        isSubOk = true;
        cancel = false;
        setVisible(false);
      }
    }
  }

  public boolean isCancel() {
    return cancel;
  }

  /**
   * Checks status.
   * @return isOk if true
   */
  public boolean isOkPressed() {
    return isOk;
  }

  public boolean isCompletePressed() {
    return isCompleteOk;
  }

  public boolean isDeletePressed() {
    return isDeleteOk;
  }

  public boolean isSubPressed() {
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

  /**
   * returns name of sub project.
   * @return sub project name or null if there is none
   */
  public String getSub() {
    if (subUsed) {
      return subField.getText();
    }
    return null;
  }
  
  /**
   * Checks if fields have proper data.
   * @return true if there are no problems, false is there is
   */
  public boolean isValidField() {

    if (nameField.getText().equals("")) {
      JOptionPane.showMessageDialog(null, 
          "Invalid Name: Name was not entered.", "Input Validation",
          JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (subUsed) {
      if (subField.getText().equals("")) {
        JOptionPane.showMessageDialog(null, 
            "Invalid Sub-Name: Sub-Name was not entered.", "Input Validation",
            JOptionPane.ERROR_MESSAGE);
        return false;
      }
    }
    int rNum = 0;
    try {
      rNum = Integer.parseInt(reminderField.getText());
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Invalid Reminder:  Please try again.",
          "Input Validation", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (Utilities.strToGregCalendar(dateField.getText()) == null) {
      JOptionPane.showMessageDialog(null, "Invalid due date:  " 
          + "Please Enter a Date in mm/dd/yyyy Form.", "Input Validation", 
          JOptionPane.ERROR_MESSAGE);
      return false;
    } 
    GregorianCalendar today = new GregorianCalendar();
    today.set(GregorianCalendar.HOUR_OF_DAY, 0);
    today.set(GregorianCalendar.MINUTE, 0);
    today.set(GregorianCalendar.SECOND, 0);
    today.set(GregorianCalendar.MILLISECOND, 0);
    if (getDueDate().before(today)) {
      JOptionPane.showMessageDialog(null, 
          "Invalid due date:  Due date is before today.",
          "Input Validation", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    if (Utilities.daysLapsed(today, getDueDate()) < getReminder() & getReminder() != 0) {
      JOptionPane.showMessageDialog(null, 
          "Invalid reminder:  Reminder is in the past.",
          "Input Validation", JOptionPane.ERROR_MESSAGE);
      return false;
    }

    if (Utilities.daysLapsed(today, getDueDate()) == getReminder() && getReminder() > 0) {
      if (JOptionPane.showConfirmDialog(null, "Your set reminder is currently set to today.  "
          + "Is this correct?\n",
          null, JOptionPane.OK_CANCEL_OPTION) != 0) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        return false;
      } else {
        JOptionPane.showMessageDialog(null, 
            "You will be reminded next time you open Project Manager today");
        return true;
      }
    }

    try {
      if (getReminder() < 0) {
        throw new Exception();
      }
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "The Reminder days were entered incorrectly.", 
          "Input Validation", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    return true;
  }

  /**
   * Checks which Project.
   * @return aProject is the Project
   */
  public Project whatProject() {
    return theProject;
  }
  
  /**
   * Clears the text of all fields.
   */
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
