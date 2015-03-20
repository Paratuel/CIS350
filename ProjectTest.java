package package1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
	
	//insert tests
  @Test
  public void insertTest() {
	  
    GregorianCalendar d = new GregorianCalendar();
    d.set(Calendar.MONTH, 1);
    d.set(Calendar.DAY_OF_MONTH, 1);
    d.set(Calendar.YEAR, 2015);
    Project p1 = new Project("Test1", d, "Note", new ArrayList<Project>());
    Assert.assertEquals("Test1 1/1/2015 Note",
    		p1.toString());
  }
  
  @Test
  public void insertTest2() {
	  
	  GregorianCalendar d = new GregorianCalendar();
	  d.set(Calendar.MONTH, 1);
	  d.set(Calendar.DAY_OF_MONTH, 1);
	  d.set(Calendar.YEAR, 2015);
	  Project p1 = new Project("Test1", d, "Fire", new ArrayList<Project>());
	  Project p2 = new Project("Test2", d, "", new ArrayList<Project>());
	  
	  String test = p1.toString() + "\n" + p2.toString();
	  Assert.assertEquals("Test1 1/1/2015 Fire\nTest2 1/1/2015 ", test);
  }
  public void insertTest3(){
	  
  }
}
