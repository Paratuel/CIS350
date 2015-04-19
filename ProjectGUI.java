package package1;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Creates the main GUI of the program.
 * @author Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */
public class ProjectGUI extends JFrame implements ActionListener {

  /**
   * serialVersionUID. 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Main frame of the GUI.
   */
  private JFrame frame;

  /**
   * The scrollPane to contain the JTable.
   */
  private JScrollPane scrollPane;

  /**
   * JTable for displaying all information.
   */
  private JTable table;

  /**
   * Button for displaying all projects.
   * (To be implemented later)
   */
  private JButton allButton;

  /**
   * Button for sorting projects by date.
   */
  private JButton dateButton;

  /**
   * Button to add a new project.
   */
  private JButton newItem;

  /**
   * Panel for buttons at the top of the GUI.
   */
  private JPanel buttonPanel;

  /**
   * Panel for buttons at the bottom of the GUI.
   */
  private JPanel southPanel;

  /** Menu for containing exit, save, and load. */
  private JMenu fileMenu; 

  private JMenuItem legendItem;

  private JMenuItem clearcompleteItem;

  private JMenuItem clearallItem;

  private JMenuItem exitItem;

  /** Allows user to load previously saved data. */
  private MyCellRenderer pleaseWork;

  /** Creates the ProjectModel for the program. */
  private ProjectModel model;

  /**
   * Creates a newProject for the program.
   */
  private CreateGUI newProject;

  private int arraySize;

  /**
   * Instantiates the GUI.
   */
  public ProjectGUI() {
    setupFrame();
    model.checkingReminders();
    setDefaultLookAndFeelDecorated(true);
  }

  /**
   * Sets up the panel for the parent GUI.
   */
  public final void setupFrame() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    frame.setTitle("Project Management");

    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    fileMenu = new JMenu("File");

    legendItem = new JMenuItem("Color Legend");
    legendItem.addActionListener(this);
    clearcompleteItem = new JMenuItem("Clear Complete");
    clearcompleteItem.addActionListener(this);
    clearallItem = new JMenuItem("Clear All");
    clearallItem.addActionListener(this);
    exitItem = new JMenuItem("Exit");
    exitItem.addActionListener(this);
    fileMenu.add(legendItem);
    fileMenu.add(clearcompleteItem);
    fileMenu.add(clearallItem);
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);


    model = new ProjectModel();
    model.load(new File("src/package1/file.ser"));
    pleaseWork = new MyCellRenderer(model);
    table = new JTable(model);
    for (int i = 0; i < 5; i++) {
      table.getColumnModel().getColumn(i).setCellRenderer(pleaseWork);
    }



    scrollPane = new JScrollPane(table);

    scrollPane.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    table.setToolTipText("");
    ListSelectionModel listMod = table.getSelectionModel();
    listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.addMouseListener(new MouseAdapter(){
      public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
          JTable target = (JTable)event.getSource();
          int row = target.getSelectedRow();
          edit(row);
        }
      }
    } );

    scrollPane.setViewportView(table);

    frame.add(scrollPane, BorderLayout.CENTER);
    JPanel panel;
    panel = setupNorthPanel();
    frame.add(panel, BorderLayout.NORTH);

    panel = setupSouthPanel();
    frame.add(panel, BorderLayout.SOUTH);

    frame.setJMenuBar(menuBar);
    frame.pack();
    frame.setVisible(true);
    frame.setSize(1000, 500);
    frame.setLocationRelativeTo(null);
  }

  /**
   * Setting up bottom panel.
   * @return southPanel
   */
  public final JPanel setupSouthPanel() {
    southPanel = new JPanel(new GridLayout(1,1));


    String name = "Serif"; 

    int style = Font.BOLD; 
    int size = 18; 

    newItem = new JButton("+");
    newItem.setFont(new Font(name, style, size));
    newItem.setHorizontalAlignment(SwingConstants.CENTER);
    newItem.addActionListener(this);
    southPanel.add(newItem);
    return southPanel;
  }

  /**
   * Setting up top panel.
   * @return buttonPanel
   */
  public final JPanel setupNorthPanel() {
    buttonPanel = new JPanel(new FlowLayout());

    allButton = new JButton("All Projects");
    allButton.addActionListener(this);

    dateButton = new JButton("Sort by Date");
    dateButton.addActionListener(this);

    buttonPanel.add(allButton);
    buttonPanel.add(dateButton);


    return buttonPanel;
  }
  /**
   * Assigns actions to buttons and JMenuItems.
   * @param event determines what is clicked and what to do
   */
  public final void actionPerformed(final ActionEvent event) {
    if (event.getSource() == exitItem) {
      model.save(new File("src/package1/file.ser"));
      System.exit(0);
    }

    if (event.getSource() == legendItem) {
      JOptionPane.showMessageDialog(null, "Cyan: Completed Project."
          + "\nGreen: Project is due in more than 5 days."
          + "\nYellow: Project is due within 3-5 days."
          + "\nRed: Project is due within 2 days."
          + "\nGray: Project is overdue.", "Color Legend", 1);
    }

    if (event.getSource() == clearcompleteItem) {
      if (JOptionPane.showConfirmDialog(null, 
          "Are you sure you would like to delete all completed projects?",
          null, JOptionPane.OK_CANCEL_OPTION) != 0) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      } else {
        arraySize = model.getRowCount();
        for (int i = 0; i < arraySize; i++) {
          if (model.get(i).getDone()) {
            model.remove(model.get(i));
            i--;
            arraySize--;
          }
        }
      }
    }

    if (event.getSource() == clearallItem) {
      if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to clear ALL projects?",
          null, JOptionPane.OK_CANCEL_OPTION) != 0) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      } else {
        arraySize = model.getRowCount();
        for (int i = 0; i < arraySize; i++) {
          model.remove(model.get(0));
        }
      }
    }



    if (event.getSource() == newItem) {
      newProject = new CreateGUI(this);

      if (newProject.isOkPressed()) {

        Project proj = new Project(newProject.getName(), null, 
            newProject.getDueDate(), 
            newProject.getNotes(), 
            newProject.getReminder(), false);
        model.add(proj);
        model.sortByName();
      }
    }
    if (event.getSource() == allButton) {
      model.sortByName();
    }
    if (event.getSource() == dateButton) {
      model.sortByDate();
    }
  }

  /**
   * 
   * @param index
   */
  public void edit(int index) {
    if (model.get(index).getSubName() == null) {
      newProject = new CreateGUI(this, 
          model.get(index).getName(),
          model.get(index).getDueDate(),
          model.get(index).getReminder(),
          model.get(index).getNotes());
    } else {
      newProject = new CreateGUI(this,
          model.get(index).getName(),
          model.get(index).getSubName(),
          model.get(index).getDueDate(),
          model.get(index).getReminder(),
          model.get(index).getNotes());
    }


    if (newProject.isOkPressed()) {
      Project sub = new Project(newProject.getName(),
          newProject.getSub(), newProject.getDueDate(), 
          newProject.getNotes(),
          newProject.getReminder(), false);
      if (model.get(index).getName() 
          != newProject.getName()) {
        model.upDate(model.get(index).getName(), 
            newProject.getName());
      }
      model.remove(model.get(index));
      model.add(sub);
      model.sortByName();
    }

    if (newProject.isDeletePressed()) {
      if (model.get(index).getSubName() != null) {
        if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this "
            + "sub-project?", null, JOptionPane.OK_CANCEL_OPTION) != 0) {
          setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        } else {
          model.remove(model.get(index));
        }
      } else {
        int count = 0;
        for (int j = 0; j < model.getSize(); j++) {
          if (model.get(index).getName().equals(model.get(j).getName())) {
            count++;
          }
        }
        if (count == 1) {
          if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this "
              + "project?", null, JOptionPane.OK_CANCEL_OPTION) != 0) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          } else {
            model.remove(model.get(index));
          }
        } else {
          if (JOptionPane.showConfirmDialog(null, "Error: You cannot delete this project."  
              + "\nThere is a sub-project attached to it.",
              null, JOptionPane.OK_CANCEL_OPTION) != 0) {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
          }
        }
      }
    }
    if (newProject.isCompletePressed()) {
      if (model.get(index).getDone() == false) {
        model.get(index).setDone(true);
      } else {
        model.get(index).setDone(false);
      }
      model.refreshCell(index, 6);
    }
    if (newProject.isSubPressed()) {
      newProject = new CreateGUI(this,
          model.get(index).getName(),
          model.get(index).getSubName(),
          model.get(index).getDueDate(),
          model.get(index).getReminder(),
          model.get(index).getNotes());

      if (newProject.isOkPressed()) {
        Project proj = new Project(newProject.getName(), 
            newProject.getSub(), newProject.getDueDate(), 
            newProject.getNotes(), 
            newProject.getReminder(), false);

        model.add(proj);

      }
    }
    model.refresh(index);
  }
}
