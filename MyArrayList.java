package package1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.AbstractListModel;

public class MyArrayList<E> extends AbstractListModel {

  private static final long serialVersionUID = 1L;
  private ArrayList<project> myArray;
  //private ArrayList<project> secondArray;

  
  /**
  * Initializing the Project object myArray
  */
  public MyArrayList() {
    myArray = new ArrayList<project>();
    //secondArray = new ArrayList<project>();
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
  @Override
  public Object getElementAt(int index) {
    return myArray.get(index);
  }
  
  /**
  * Adding an element to myArray with p being passed into it 
  * @param p is the Project object being passed into it. 
  */
  public void add(project p) {
    myArray.add(p);
    fireIntervalAdded(this, 0, myArray.size());
  }
  
  /**
  * Deleting a certain element in the myArray
  * @param index is the position being passed in
  * @return obj is the object being removed.
  */
  public Object delete(int index) {
    Object obj = myArray.remove(index);
    fireContentsChanged(this, 0, myArray.size());
    return obj;
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
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
  * Loading the file of the program
  * @param file being passed into it.
  */
  public void load(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      ObjectInputStream ois = new ObjectInputStream(bis);
      myArray = (ArrayList<project>) ois.readObject();
      ois.close();
      bis.close();
      fis.close();
      fireContentsChanged(this, 0, myArray.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void evaluateByDate(GregorianCalendar givenDate){
	  //SimpleDateFormat format = new SimpleDateFormat("MM/dd/YYYY");
	  ArrayList<project> secondArray = new ArrayList<project>();
	  for(int k = 0; k < myArray.size(); k++)
		  secondArray.add(myArray.get(k));
	  myArray.clear();
	   for (int i = 0; i < secondArray.size(); i++){
		   if(secondArray.get(i).getSubtasks().size() == 0){
			   if(secondArray.get(i).getDueDate().after
					   (new GregorianCalendar()) && secondArray.get(i).
					   getDueDate().before(givenDate)){
				   myArray.add(secondArray.get(i));
			   }
		   }
		   else{
			   for(int j = 0; j < secondArray.get(i).getSubtasks().size(); j++){
				   if(secondArray.get(i).getSubtasks().get(j).
						   getDueDate().after(new GregorianCalendar())
						   && secondArray.get(i).getSubtasks().get(j).
						   getDueDate().before(givenDate)){
					   myArray.add(secondArray.get(i).getSubtasks().get(j));
				   }
			   }
		   }
	   }
	   fireContentsChanged(this, 0, myArray.size());
	   for(int l = 0; l < secondArray.size(); l++)
			  myArray.add(secondArray.get(l));
  }
  public void reset(){
	  fireContentsChanged(this, 0, myArray.size());
  }
  public void showReminder(){
	  
  }
}
