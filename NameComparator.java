package package1;

import java.util.Comparator;

public class NameComparator implements Comparator<Project> {
	/**
	 * compares the names
	 * @param s1 is a name
	 * @param s2 is a name
	 * @return returns results
	 */
	
	public int compare(Project p1, Project p2) {
		if(p1.getName().equals(p2.getName()) && p1.getSubName() == null){
			return -1;
		}
		if(p1.getName().equals(p2.getName()) && p2.getSubName() == null){
			return 1;
		}
		return p1.getName().compareTo(p2.getName());
	}

}
