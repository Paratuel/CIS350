package package1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Paratuel on 2/9/2015.
 */
public class Project implements Serializable {
	


	private static final long serialVersionUID = 1L;
	private String name;
    private Date dueDate;
    private String category;
    private String notes;
    private Vector<Project> subTasks;

    ArrayList<Project> Projects = new ArrayList<Project>();

    Project(){
     name = "Untitled";
     dueDate = new Date(1,1,2015);
     category = "Untitled";
     notes = "";
     subTasks = new Vector<Project>();
    }

    Project(String n, Date dd, String c, String nt){
        name = n;
        dueDate = dd;
        category = c;
        notes = nt;
        subTasks = new Vector<Project>();
    }

    
    public String toString(){
    	return name + " "+ dueDate.toString() + " " + category + " " + 
    notes;
    }

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Date getDueDate() {
		return dueDate;
	}

	protected void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	protected String getCategory() {
		return category;
	}

	protected void setCategory(String category) {
		this.category = category;
	}

	protected String getNotes() {
		return notes;
	}

	protected void setNotes(String notes) {
		this.notes = notes;
	}
	
	protected void addItems(Project p){
		subTasks.addElement(p);
	}

	protected Vector<Project> getSubtasks(){
		return subTasks;
	}
}
