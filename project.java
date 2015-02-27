package package1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Project Management is a program that helps with the organization of projects needed
 * 		to be done with the added benefit of subgroups.
 * Created by Patrick Dishaw, Laura Young, Viet Duong, Nicholas Bushen
 */
public class Project implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
  	private Date dueDate;
    	private String category;
    	private String notes;
    	private Vector<Project> subTasks;

    ArrayList<Project> Projects = new ArrayList<Project>();

    /**
     * Initializing needed variables.
     */
    Project(){
     name = "Untitled";
     dueDate = new Date(1,1,2015);
     category = "Untitled";
     notes = "";
     subTasks = new Vector<Project>();
    }

    /**
     * Initializing needed variables that are passed into the construct
     * @param n is the name of the project
     * @param dd is the due date of the project
     * @param c is the category for the project
     * @param nt notes for the project
     */
    Project(String n, Date dd, String c, String nt){
        name = n;
        dueDate = dd;
        category = c;
        notes = nt;
        subTasks = new Vector<Project>();
    }
  
    /**
     * Setting up the string that will print
     */
    public String toString(){
    	return name + " "+ dueDate.toString() + " " + category + " " + 
    notes;
    }

    /**
     * Returns the name of the project
     * @return name of the project
     */
	protected String getName() {
		return name;
	}

	/**
	 * Sets the name of the Project object with the name being passed into it.
	 * @param name is the project name.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the due date of the project
	 * @return dueDate of the project
	 */
	protected Date getDueDate() {
		return dueDate;
	}

	/**
	 * Sets the dueDate of the Project object with the dueDate passing into it.
	 * @param dueDate is the due date of the project
	 */
	protected void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Returns the category of the project
	 * @return category is the category of the project
	 */
	protected String getCategory() {
		return category;
	}

	/**
	 * Sets up the category of the Project object with the category passing into it.
	 * @param category is the category of the project
	 */
	protected void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Returns the notes of of the project
	 * @return notes is the notes taken for the project
	 */
	protected String getNotes() {
		return notes;
	}

	/**
	 * Sets up the notes of the Project object with the notes passing into it.
	 * @param notes is the notes of the project
	 */
	protected void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Adds subgroups into the subTasks vector with the subgroup being passed into it.
	 * @param p is the subgroup of the project
	 */
	protected void addItems(Project p){
		subTasks.addElement(p);
	}

	/**
	 * Returns the Subtasks of the project
	 * @return subTasks are the subgroups of the project
	 */
	protected Vector<Project> getSubtasks(){
		return subTasks;
	}
}
