package package1;

import java.util.Comparator;

public class CompleteComparator implements Comparator<Project> {

  /**
   * Compares the dates.
   * @param p1 is the first date
   * @param p2 is the second date
   * @return the results
   */
  @Override
  public int compare(Project p1, Project p2) {
    if (p1.getDone() == true & p2.getDone() == false) {
      return 1;
    } else {
      return -1;
    }
  }
}
