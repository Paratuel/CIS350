package package1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

/**
 * Project Management is a program that helps with the organization 
 * of Projects needed to be done with the added benefit of subgroups.
 * Created by Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private GregorianCalendar dueDate;
	private int reminder;
	private String notes;
	private ArrayList<Project> subTasks;
	private boolean done;

	//ArrayList<Project> Projects = new ArrayList<Project>();
	
	/**
	 * Initializing needed variables.
	 */
	public Project() {
		name = "Untitled";
		dueDate = new GregorianCalendar(2015,1,1);
		reminder = 0;
		notes = "";
		done = false;
		subTasks = new ArrayList<Project>();
	}

	/**
	 * Initializing needed variables that are passed into the construct.
	 * @param n is the name of the Project
	 * @param dd is the due date of the Project
	 * @param c is the category for the Project
	 * @param nt notes for the Project
	 */
	public Project(String n, GregorianCalendar dd, String nt, int rr, ArrayList<Project> sub) {
		this.name = n;
		this.dueDate = dd;
		this.reminder = rr;
		this.notes = nt;
		subTasks = sub;
		done = false;
	}
	
	public Project(String n, GregorianCalendar dd, int rr, String nt) {
		this.name = n;
		this.dueDate = dd;
		this.reminder = rr;
		this.notes = nt;
		done = false;
		subTasks = new ArrayList<Project>();
	}
	
	

	/**
	 * Setting up the string that will print.
	 * @return 
	 */  
	public String toString() {
		return "Subtask: " + name + "\nDue Date: " + dueDate.get(Calendar.MONTH) + "/" + 
				dueDate.get(Calendar.DAY_OF_MONTH) + "/" + 
				dueDate.get(Calendar.YEAR) + "\nNotes: " + notes;
	}

	/**
	 * Returns the name of the Project
	 * @return name of the Project
	 */
	protected String getName() {
		return name;
	}

	/**
	 * Sets the name of the Project object with the name being passed into it.
	 * @param name is the Project name.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the due date of the Project
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
	 * Returns the reminder for the Project
	 * @return reminder of the Project
	 */
	protected int getReminder(){
		return reminder;
	}
	
	/**
	 * Sets the reminder of the Project object with the reminder passing into it.
	 * @param reminder is the reminder for the Project
	 */
	protected void setReminder(int reminder){
		this.reminder = reminder;
	}

	/**
	 * Returns the notes of of the Project
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
	
	protected boolean swapDone() {
		if (done) {
			done = false;
			return done; 
			} else {
			done = true;
			return done; 
			}
	}

	/**
	 * Adds subgroups into the subTasks vector with the subgroup being passed into it.
	 * @param p is the subgroup of the Project
	 */
	protected void addItems(Project p) {
		subTasks.add(p);
	}

	/**
	 * Returns the Subtasks of the Project
	 * @return subTasks are the subgroups of the Project
	 */
	protected ArrayList<Project> getSubtasks() {
		return subTasks;
	}
}
