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
  @Test
  public void insertTest3(){
	  GregorianCalendar Test1 = new GregorianCalendar();
	  Test1.set(Calendar.MONTH, 1);
	  Test1.set(Calendar.DAY_OF_MONTH, 1);
	  Test1.set(Calendar.YEAR, 2014);
	  GregorianCalendar Test2 = new GregorianCalendar();
	  Test2.set(Calendar.MONTH, 3);
	  Test2.set(Calendar.DAY_OF_MONTH, 9);
	  Test2.set(Calendar.YEAR, 2014);
	  Assert.assertEquals(1, Test2.compareTo(Test1));
  }
  @Test
  public void insertTest4(){
	  Project p = new Project("Hello", new GregorianCalendar(2015,1,1), "Notes");
	  Assert.assertEquals("Hello", p.getName());
	  Assert.assertEquals("02/01/2015", Utilities.gToString(p.getDueDate()));
	  Assert.assertEquals("Notes", p.getNotes());
	  Project q = new Project("Hello2", new GregorianCalendar(2015,0,1), "Note", new ArrayList<Project>());
	  Assert.assertEquals("Hello2", q.getName());
	  Assert.assertEquals("01/01/2015", Utilities.gToString(q.getDueDate()));
	  Assert.assertEquals("Note", q.getNotes());
	  Assert.assertEquals(new ArrayList<Project>(), q.getSubtasks());
  }
  @Test
  public void insertTest5() {
	  
    GregorianCalendar d = new GregorianCalendar();
    d.set(Calendar.MONTH, 4);
    d.set(Calendar.DAY_OF_MONTH, 4);
    d.set(Calendar.YEAR, 2015);
    Project p1 = new Project("Project4", d, "Note4", new ArrayList<Project>());
    Assert.assertEquals("Project4 4/4/2015 Note4",
    		p1.toString());
  }
   @Test
  public void insertTest7() {
	  
	  GregorianCalendar d = new GregorianCalendar();
	  d.set(Calendar.MONTH, 7);
	  d.set(Calendar.DAY_OF_MONTH, 7);
	  d.set(Calendar.YEAR, 2015);
	  Project p = new Project("Assignment", d, "Math", new ArrayList<Project>());
	  Project p1 = new Project("Test1", d, "", new ArrayList<Project>());
	  
	  String test7 = p.toString() + "\n" + p1.toString();
	  Assert.assertEquals("Assignment 7/7/2015 Math\nTest1 7/7/2015 ", test7);
  } 
}
