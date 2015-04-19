package package1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;


/**
 * Class for the entire storage of our Project. 
 * @author  Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 *
 */

public class ProjectModel extends AbstractTableModel implements Serializable {

  private static final long serialVersionUID = 1L;
  private ArrayList<Project> myArray; 
  private ArrayList<Project> refreshArray;
  private Project project;
  private String[] columnNames = {"Project Name", "Sub-Projects", "Due Date", "Reminders", "Notes"};

  /** counter for overdue projects. */
  public int overdue;

  /**
   * Initializing the Project object myArray.
   */
  public ProjectModel() {
    myArray = new ArrayList<Project>();
    refreshArray = new ArrayList<Project>();
  }

  /**
   * returns the columnNames length.
   * @return columnNames length
   */
  public int getColumnCount() {
    return columnNames.length;
  }

  /**
   * Returns the values for filling in the GUI table.
   * Object val holds the information to send.
   * @param row is the row that the information goes on.
   * @param col is the column that the information goes on
   * @return val.
   */
  public Object getValueAt(int row, int col) {
    Object val = null;
    switch (col) {
      case 0:
        val = myArray.get(row).getName();
        return val;
      case 1:
        if (myArray.get(row).getSubName() != null) {
          val = myArray.get(row).getSubName();
        } else {
          val = "";
        }
        return val;
      case 2:
        val = Utilities.gtoString(myArray.get(row).getDueDate());
        return val;
      case 3:
        val = myArray.get(row).getReminder();
        return val;
      case 4:
        val = myArray.get(row).getNotes();
        return val;
      default:
        return val;
    }

  }

  /**
   * retrieves the index of myArray.
   * @param index is the position in myArray that is needed
   * @return the myArray at the index
   */
  public Project get(int index) {
    return myArray.get(index);
  }

  /**
   * Checks which projects have a current reminder and prints it out.
   */
  public void checkingReminders() {
    for (int i = 0; i < myArray.size(); i++) {
      GregorianCalendar today = new GregorianCalendar();
      today.set(GregorianCalendar.HOUR_OF_DAY, 0);
      today.set(GregorianCalendar.MINUTE, 0);
      today.set(GregorianCalendar.SECOND, 0);
      today.set(GregorianCalendar.MILLISECOND, 0);
      if (myArray.get(i).getReminder() != 0) {
        int dd = Utilities.daysLapsed(today, myArray.get(i).getDueDate());
        if (dd == myArray.get(i).getReminder()) {
          if (myArray.get(i).getSubName() == null) {
            JOptionPane.showMessageDialog(null, "Reminder for Project: "
                + myArray.get(i).getName() + "\nDue in " + dd + " day(s).");
          } else {
            JOptionPane.showMessageDialog(null, "Reminder for Sub-Project: "
                + myArray.get(i).getSubName() + " (" + myArray.get(i).getName()  
                + ")" + "\nDue in " + dd + " day(s).");
          }
        }
      }
    }
  }

  /**
   * Finds the index of a particular Project.
   * @param a is the Project it is asking for
   * @return the index of myArray at a
   */
  public int indexOf(Project proj) {
    return myArray.indexOf(proj);
  }

  /**
   * Returns the myArray size
   * @return myArray.size() of the project
   * */
  public int getSize() {
    return myArray.size();
  }

  /**
   * Returns an element from myArray
   * @param index is the spot in the array needed.
   * @return myArray.get(index) is the element needed 
   */
  public Object getElementAt(int index) {
    return myArray.get(index);
  }

  /**
   * Adding an element to myArray with p being passed into it 
   * @param p is the Project object being passed into it. 
   */
  public void add(Project proj) {
    myArray.add(proj);
    fireTableRowsInserted(0, myArray.size());
  }

  /**
   * Deleting a certain element in the myArray.
   * @param index is the position being passed in
   */
  public void delete(int index) {
    Object obj = myArray.remove(index);
    fireTableRowsDeleted(0, myArray.size());
    return;
  }

  /**
   * retrieves the index for a particular Project.
   * @param a is the project.
   */
  public void remove(Project rem) {
    delete(indexOf(rem));
  }

  public void upDate(String n1, String n2) {
    for (int i = 0; i < myArray.size(); i++) {
      if (myArray.get(i).getName().equals(n1)) {
        myArray.get(i).setName(n2);
        fireTableRowsUpdated(0, myArray.size());
      }
    }

    return;
  }
  /**
   * Returns the columnNames that will fill the table in the GUI.
   * @param col sets the position for the columnNames on the table
   * @return columnNames and positions
   */
  public String getColumnName(int col) {
    return columnNames[col];
  }

  public void sortByDate() {
    if (myArray.size() > 1) {
      Collections.sort(myArray, new ProjectDateComparator());
      fireTableRowsUpdated(0, myArray.size());
    }
  }
  /**
   * Sorts the listSites by Name and updates the GUI table.
   */
  public void sortByName() {
    if (myArray.size() > 1) {
      Collections.sort(myArray, new NameComparator());
      fireTableRowsUpdated(0, myArray.size() - 1);
    }
  }
  
  public void sortByComplete() {
    if (myArray.size() > 1) {
      Collections.sort(myArray, new CompleteComparator());
      Collections.sort(myArray, new CompleteComparator());
      fireTableRowsUpdated(0, myArray.size() - 1);
    }
  }
  
  

  /**
   * Saving the file of the program
   * @param file being passed into it.
   */
  public void save(File file) {
    try {
      FileOutputStream fos = new FileOutputStream(file);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(myArray);
      oos.close();
      bos.close();
      fos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Loading the file of the program
   * @param file being passed into it.
   */
  /**
   * @param file being passed into it.
   */
  public void load(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      ObjectInputStream ois = new ObjectInputStream(bis);
      myArray = (ArrayList<Project>) ois.readObject();
      ois.close();
      bis.close();
      fis.close();
      fireTableRowsInserted(myArray.size() - 1, myArray.size() - 1);
    } catch (FileNotFoundException f) {
      save(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
    overdue = 0;
    for (int i = 0; i < myArray.size(); i++) {
      GregorianCalendar today = new GregorianCalendar();
      today.set(GregorianCalendar.HOUR_OF_DAY, 0);
      today.set(GregorianCalendar.MINUTE, 0);
      today.set(GregorianCalendar.SECOND, 0);
      today.set(GregorianCalendar.MILLISECOND, 0);
      if (myArray.get(i).getDueDate().before(today) & overdue < 1) {
        JOptionPane.showMessageDialog(null, "You have overdue projects.");
        overdue++;
      }

    }
  }

  @Override
  public int getRowCount() {
    return myArray.size();
  }

  public void refresh() {
    fireTableDataChanged();
  }

  public void refresh(int hi) {
    fireTableRowsUpdated(0,hi);
  }

  public void refreshCell(int row, int column) {
    fireTableCellUpdated(row, column);
  }

  public Class getColumnClass(int index) {
    switch (index) {
      case 0:
      case 1:
      case 2:
        return String.class;
      case 3:
        return Integer.class;
      case 4:
        return String.class;
      case 5:
        return Boolean.class;
      default:
        return null;

    }
  }

  public boolean isCellEditable(int row, int column) {
    if (column == 5) {
      return true;
    } else {
      return false;
    }

  }

}
