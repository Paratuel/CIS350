package package1;

import java.util.Comparator;

public class ProjectDateComparator implements Comparator<project>{
	
	/**
	 * compares the dates
	 * @param p1 is the first date
	 * @param p2 is the second date
	 * @return the results
	 */
	@Override
	public int compare(project p1, project p2){
		return p1.getDueDate().compareTo(p2.getDueDate());
	}
}
