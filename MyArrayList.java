package package1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class MyArrayList<E> extends AbstractListModel {

	
	private static final long serialVersionUID = 1L;
	private ArrayList<Project> myArray;
	
	public MyArrayList(){
		myArray = new ArrayList<Project>();
	}
	
	public int getSize() {
		return myArray.size();
	}

	@Override
	public Object getElementAt(int index) {
		return myArray.get(index);
	}
	
	public void add(Project p){
		myArray.add(p);
		fireIntervalAdded(this, 0, myArray.size());
	}
	public Object delete(int index){
		Object obj = myArray.remove(index);
		fireContentsChanged(this, 0, myArray.size());
		return obj;
		
	}
	public void save(File file){
		try{
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(myArray);
		fos.close();
		bos.close();
		oos.close();
	}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void load(File file){
		try{
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			myArray = (ArrayList<Project>) ois.readObject();
			fis.close();
			bis.close();
			ois.close();
			fireContentsChanged(this, 0, myArray.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
