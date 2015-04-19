package package1;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Project Management is a program that helps with the organization 
 * of Projects needed to be done with the added benefit of subgroups.
 * Created by Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 */
public class Project implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name; 
  private String subName;
  private GregorianCalendar dueDate;
  private int reminder;
  private String notes;
  private ArrayList<Project> subTasks;
  private boolean done;
  private boolean sub;
  private Color color;


  /**
   * Initializing needed variables.
   */
  public Project() {
    name = "Untitled";
    dueDate = new GregorianCalendar(2015,1,1);
    notes = "";
    done = false;
    subTasks = new ArrayList<Project>();
  }

  /**
   * Initializing needed variables that are passed into the construct.
   * @param n is the name of the Project
   * @param dd is the due date of the Project
   * @param nt notes for the Project
   */
  public Project(String n, String s, GregorianCalendar dd, String nt, 
      int rr, boolean b, ArrayList<Project> sub) {
    this.name = n;
    this.subName = s;
    this.dueDate = dd;
    this.reminder = rr;
    this.notes = nt;
    this.subTasks = sub;
  }

  /**
   * Initializing the variables that are passed into the construct.
   * @param n is the project name
   * @param s is the sub name
   * @param dd is the due date
   * @param nt is the note
   * @param rr is the reminder
   * @param b is the project state
   */
  public Project(String n, String s, GregorianCalendar dd, String nt, 
      int rr, boolean b) {
    this.name = n;
    this.subName = s;
    this.dueDate = dd;
    this.reminder = rr;
    this.notes = nt;
    this.done = b;
    if (subName == null) {
      sub = false;
    } else {
      sub = true;
    }
  }

  /**
   * Setting up the string that will print.
   * @return all project information as a String
   */  
  public String toString() {
    return "Subtask: " + name + "\nDue Date: " + dueDate.get(Calendar.MONTH) + "/" 
        + dueDate.get(Calendar.DAY_OF_MONTH) + "/" 
        + dueDate.get(Calendar.YEAR) + "\nNotes: " + notes;
  }

  /**
   * Returns the name of the Project.
   * @return name of the Project
   */
  protected String getName() {
    return name;
  }

  /**
   * Returns the subName of the subGroup.
   * @return subName of the subGroup
   */
  protected String getSubName() {
    return subName;
  }

  /**
   * sets the name of the subGroup with the name being passed into it.
   * @param n is the name for the subName
   */
  protected void setSubName(String sname) {
    this.subName = sname;
  }

  /**
   * Sets the name of the Project object with the name being passed into it.
   * @param name is the Project name.
   */
  protected void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the due date of the Project.
   * @return dueDate of the Project
   */
  protected GregorianCalendar getDueDate() {
    return dueDate;
  }

  /**
   * Sets the dueDate of the Project object with the dueDate passing into it.
   * @param dueDate is the due date of the Project
   */
  protected void setDueDate(GregorianCalendar dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Returns the reminder for the Project.
   * @return reminder of the Project
   */
  protected int getReminder() {
    return reminder;
  }

  /**
   * Sets the reminder of the Project object with the reminder passing into it.
   * @param reminder is the reminder for the Project
   */
  protected void setReminder(int reminder) {
    this.reminder = reminder;
  }

  /**
   * Returns the notes of of the Project.
   * @return notes is the notes taken for the Project
   */
  protected String getNotes() {
    return notes;
  }

  /**
   * Sets up the notes of the Project object with the notes passing into it.
   * @param notes is the notes of the Project
   */
  protected void setNotes(String notes) {
    this.notes = notes;
  }

  protected boolean getDone() {
    return done;
  }

  protected void setDone(boolean done) {
    this.done = done;
  }

  /**
   * Checking if this is a subGroup or not.
   * @return sub = true if it is a subGroup
   */
  protected boolean getSub() {
    return sub;
  }

  /**
   * Setting whether this is a subGroup or not.
   * @param a is the boolean for sub
   */
  protected void setSub(boolean sub) {
    this.sub = sub;
  }

  /**
   * Adds subgroups into the subTasks vector with the subgroup being passed into it.
   * @param p is the subgroup of the Project
   */
  protected void addItems(Project proj) {
    subTasks.add(proj);
  }

  /**
   * Returns the Subtasks of the Project.
   * @return subTasks are the subgroups of the Project
   */
  protected ArrayList<Project> getSubtasks() {
    return subTasks;
  }
  
  protected Color getColor() {
    GregorianCalendar today =  new GregorianCalendar();
    today.set(GregorianCalendar.HOUR_OF_DAY, 0);
    today.set(GregorianCalendar.MINUTE, 0);
    today.set(GregorianCalendar.SECOND, 0);
    today.set(GregorianCalendar.MILLISECOND, 0);
    System.out.println(Utilities.daysLapsed(getDueDate(), today) + "");
    if (getDueDate().before(today)) {
      color = Color.GRAY;
      return color;
    }
    if (getDone()) {
      color = Color.CYAN;
      return color;
    }
    if (Utilities.daysLapsed(getDueDate(), today) > 5) {
      color = Color.GREEN;
      return color;
    } 
    if (Utilities.daysLapsed(getDueDate(), today) <= 5 
        && (Utilities.daysLapsed(getDueDate(),today)) > 2) {
      color = Color.YELLOW;
      return color;
    } else {
      color = Color.RED;
      return color;
    }
  }
}
