package package1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProjectGUI extends JDialog implements ActionListener {

  private static final long serialVersionUID = 1L;
  
  private JButton week1Button; 
  private JButton week2Button; 
  private JButton week4Button;
  private JButton allButton;

  private JPanel buttonPanel;
  private JPanel projectPanel;

  private JMenuBar theMenuBar;

  private JMenu fileMenu;
  private JMenu helpMenu;

  private JMenuItem exitItem; 
  private JMenuItem aboutItem; 
  private JMenuItem newItem; 
  private JMenuItem deleteItem;
  private JMenuItem editItem; 
  private JMenuItem saveItem; 
  private JMenuItem loadItem; 
  private JMenuItem evaluate;

  private int WIDTH; 
  private int HEIGHT;

  private JList<Project> projectList;

  private MyArrayList<Project> projectArray;

  private ListSelectionListener listener;
  //working on it

  /*
  * Sets up the panel for the parent GUI
  */
  public ProjectGUI() {
    WIDTH = 500;
    HEIGHT = 500;
    week1Button = new JButton("1 Week");
    week1Button.addActionListener(this);
    week2Button = new JButton("2 Week");
    week2Button.addActionListener(this);
    week4Button = new JButton("4 Week");
    week4Button.addActionListener(this);
    allButton = new JButton("All Projects");
    allButton.addActionListener(this);
    buttonPanel = new JPanel();
    projectPanel = new JPanel();
    theMenuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    helpMenu = new JMenu("Help");
    exitItem = new JMenuItem("Exit");
    exitItem.addActionListener(this);
    aboutItem = new JMenuItem("About");
    aboutItem.addActionListener(this);
    newItem = new JMenuItem("New Project");
    newItem.addActionListener(this);
    deleteItem = new JMenuItem("Delete Project");
    deleteItem.addActionListener(this);
    editItem = new JMenuItem("Edit Project");
    editItem.addActionListener(this);
    saveItem = new JMenuItem("Save");
    saveItem.addActionListener(this);
    loadItem = new JMenuItem("Load");
    loadItem.addActionListener(this);
    evaluate = new JMenuItem("Evaluate");
    evaluate.addActionListener(this);
    projectArray = new MyArrayList<Project>();
    projectList = new JList<Project>(projectArray);
    projectList.addListSelectionListener(listener);

    fileMenu.add(newItem);
    fileMenu.add(editItem);
    fileMenu.add(deleteItem);
    fileMenu.addSeparator();
    fileMenu.add(saveItem);
    fileMenu.add(loadItem);
    fileMenu.addSeparator();
    fileMenu.add(evaluate);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);
    helpMenu.add(aboutItem);

    theMenuBar.add(fileMenu);
    theMenuBar.add(helpMenu);

    buttonPanel.add(week1Button);
    buttonPanel.add(week2Button);
    buttonPanel.add(week4Button);
    buttonPanel.add(allButton);

    projectPanel.add(projectList);

    add(buttonPanel);
    add(projectPanel);
    setJMenuBar(theMenuBar);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setVisible(true);
    setSize(WIDTH,HEIGHT);
    setLocationRelativeTo(null);
    setLayout(new FlowLayout());
    setTitle("Project Manager");
  }

  /*
  * Assigns actions to buttons and JMenuItems 
  */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == exitItem) {
      System.exit(0);
    }

    if (e.getSource() == aboutItem) {
      JOptionPane.showMessageDialog(null, "Hello!");
    }
    if (e.getSource() == newItem) {
      Project p = new Project();
      CreateGUI x = new CreateGUI(this, p);
      if (x.isOkPressed()) {
        p = x.whatProject();
        projectArray.add(p);
      }
    }
    if (e.getSource() == deleteItem) {
      if (projectArray.getSize() == 0) {
        JOptionPane.showMessageDialog(null, "Error: Nothing to"
            + " delete.");
      } else {
        projectArray.delete(
            projectList.getSelectedIndex());
      }
    }
    if (e.getSource() == editItem) {
      if (projectArray.getSize() == 0) {
        JOptionPane.showMessageDialog(null, "Error: Nothing to"
            + " edit.");
      } else {
        Project p = (Project) projectArray.getElementAt(
            projectList.getSelectedIndex());
        CreateGUI x = new CreateGUI(this, p);
        if (x.isOkPressed()) {
          p = x.whatProject();
          projectArray.delete(
              projectList.getSelectedIndex());
          projectArray.add(p);
        }
      }
    }
    if (e.getSource() == saveItem) {
      JFileChooser choose = new JFileChooser();
      FileNameExtensionFilter filter = new 
          FileNameExtensionFilter("Serialized Files", "ser");
      choose.setFileFilter(filter);
      choose.setSelectedFile(new File("file.ser"));
      int value = choose.showSaveDialog(this);
      if (value == JFileChooser.APPROVE_OPTION) {
        projectArray.save(choose.getSelectedFile());
      }
    }
    if (e.getSource() == loadItem) {
      JFileChooser choose = new JFileChooser();
      FileNameExtensionFilter filter = new 
          FileNameExtensionFilter("Serialized Files", "ser");
      choose.setFileFilter(filter);
      int value = choose.showOpenDialog(this);
      if (value == JFileChooser.APPROVE_OPTION) {
        projectArray.load(choose.getSelectedFile());
      }
    }
    if (e.getSource() == evaluate) {
      Project proj = (Project) projectArray.getElementAt(
          projectList.getSelectedIndex());
      String subString = "SubProjects:\n";
      for (int i = 0; i < proj.getSubtasks().size(); i++) {
        subString += proj.getSubtasks().elementAt(i) + "\n";
      }
      JOptionPane.showMessageDialog(null, subString);
    }
    if (e.getSource() == week1Button) {
      
    }
  }
}
