package package1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

public class ProjectModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Project> myArray;
	private Project project;
	private String[] columnNames = {"Project Name", "Due Date", "Reminders", "Notes"};

	/*
	 * Initializing the Project object myArray
	 */
	public ProjectModel() {
		myArray = new ArrayList<Project>();
	}

	/**
	 * returns the columnNames length
	 * @return columnNames length
	 */
	public int getColumnCount(){
		return columnNames.length;
	}
	
	/**
	 * Returns the values for filling in the GUI table.
	 * Object val holds the information to send.
	 * @param row is the row that the information goes on.
	 * @param col is the column that the information goes on
	 * @return val.
	 */
	public Object getValueAt(int row, int col){
		Object val = null;
		switch(col){
		case 0:
			val = myArray.get(row).getName();
		case 1:
			val = Utilities.gToString(myArray.get(row).getDueDate());
		case 2:
			val = myArray.get(row).getReminder();
		case 3:
			val = myArray.get(row).getNotes();	
		}
		return val;
	}
	
	/**
	 * retrieves the index of myArray
	 * @param index is the position in myArray that is needed
	 * @return the myArray at the index
	 */
	public Project get(int index){
		return myArray.get(index);
	}
	
	/**
	 * Finds the index of a particular Project
	 * @param a is the Project it is asking for
	 * @return the index of myArray at a
	 */
	public int indexOf(Project a){
		return myArray.indexOf(a);
	}
	
	/*
	 * Returns the myArray size
	 * @return myArray.size() of the project
	 * */
	public int getSize() {
		return myArray.size();
	}

	/*
	 * Returns an element from myArray
	 * @param index is the spot in the array needed.
	 * @return myArray.get(index) is the element needed 
	 */
	public Object getElementAt(int index) {
		return myArray.get(index);
	}

	/*
	 * Adding an element to myArray with p being passed into it 
	 * @param p is the Project object being passed into it. 
	 */
	public void add(Project p) {
		myArray.add(p);
		fireTableRowsInserted(myArray.size() - 1, myArray.size() - 1);
	}

	/*
	 * Deleting a certain element in the myArray
	 * @param index is the position being passed in
	 */
	public void delete(int index) {
		Object obj = myArray.remove(index);
		fireTableRowsDeleted(index, index);
		return;
	}
	
	/**
	 * retrieves the index for a particular Project.
	 * @param a is the project.
	 */
	public void remove(Project a){
		delete(indexOf(a));
	}
	
	/**
	 * returns the columnNames that will fill the table in the GUI
	 * @param col sets the position for the columnNames on the table
	 * @return columnNames and positions
	 */
	public String getColumnName(int col){
		return columnNames[col];
	}
	
	public void sortByDate(){
		if (myArray.size() > 1){
			Collections.sort(myArray, new ProjectDateComparator());
			this.fireTableRowsUpdated(0, myArray.size() -1);
		}
	}

	/*
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Loading the file of the program
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		return myArray.size();
	}
}
