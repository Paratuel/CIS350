package package1;

import static org.junit.Assert.*;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTests {

	Insets tests;
	@Test
	public void insertTest() {
		String a = "02/01/2015";
		GregorianCalendar b = Utilities.strToGregCalendar(a);
		Project p1 = new Project("Test1", b, 1, "Note", new ArrayList<Project>());
		Assert.assertEquals("Test1 null 02/01/2015 1 Note",
				p1.toString());
	}
	@Test
	public void insertTest2() {
		String a = "01/01/2015";
		GregorianCalendar d = Utilities.strToGregCalendar(a);
		Project p1 = new Project("Test1", d, 2, "Fire", new ArrayList<Project>());
		Project p2 = new Project("Test2", d, 3, null, new ArrayList<Project>());

		String test = p1.toString() + "\n" + p2.toString();
		Assert.assertEquals("Test1 null 01/01/2015 2 Fire\n" + 	
				"Test2 null 01/01/2015 3 null", test);
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
//	  @Test
//	  public void insertTest4(){
//		  Project p = new Project("Hello", new GregorianCalendar(2015,1,1), "Notes");
//		  Assert.assertEquals("Hello", p.getName());
//		  Assert.assertEquals("02/01/2015", Utilities.gToString(p.getDueDate()));
//		  Assert.assertEquals("Notes", p.getNotes());
//		  Project q = new Project("Hello2", new GregorianCalendar(2015,0,1), "Note", new ArrayList<Project>());
//		  Assert.assertEquals("Hello2", q.getName());
//		  Assert.assertEquals("01/01/2015", Utilities.gToString(q.getDueDate()));
//		  Assert.assertEquals("Note", q.getNotes());
//		  Assert.assertEquals(new ArrayList<Project>(), q.getSubtasks());
//	  }
//	  @Test
//	  public void insertTest5() {
//		  
//	    GregorianCalendar d = new GregorianCalendar();
//	    d.set(Calendar.MONTH, 4);
//	    d.set(Calendar.DAY_OF_MONTH, 4);
//	    d.set(Calendar.YEAR, 2015);
//	    Project p1 = new Project("Project4", d, "Note4", new ArrayList<Project>());
//	    Assert.assertEquals("Project4 4/4/2015 Note4",
//	    		p1.toString());
//	  }
//	   @Test
//	  public void insertTest6() {
//		  
//		  GregorianCalendar d = new GregorianCalendar();
//		  d.set(Calendar.MONTH, 7);
//		  d.set(Calendar.DAY_OF_MONTH, 7);
//		  d.set(Calendar.YEAR, 2015);
//		  Project p = new Project("Assignment", d, "Math", new ArrayList<Project>());
//		  Project p1 = new Project("Test1", d, "", new ArrayList<Project>());
//		  
//		  String test7 = p.toString() + "\n" + p1.toString();
//		  Assert.assertEquals("Assignment 7/7/2015 Math\nTest1 7/7/2015 ", test7);
//	  } 
	
	@Test
	public void insertTest7() {
		String d = "04/20/2015";
		Project p1 = new Project("Test1", "Test2", Utilities.strToGregCalendar(d), "Test", 1, false);
		Assert.assertEquals("Test1 Test2 04/20/2015 1 Test",
				p1.toString());
	}

	@Test
	public void insertTest8() {
		String a = "04/30/2015";
		String b = "04/25/2015";
		Project p1 = new Project("CIS 350 Ethics Paper", null, Utilities.strToGregCalendar(a), "Fire", 2, false);
		Project p2 = new Project("CIS 350 Ethics Paper", "one", Utilities.strToGregCalendar(b), "Read", 1,false);

		String test = p1.toString() + "\n" + p2.toString();
		Assert.assertEquals("CIS 350 Ethics Paper null 04/30/2015 2 Fire\n"
				+ "CIS 350 Ethics Paper one 04/25/2015 1 Read", test);
	}

	@Test
	public void insertTest9(){
		String a = "05/01/2015";
		GregorianCalendar today = new GregorianCalendar();
		today.set(GregorianCalendar.HOUR_OF_DAY, 0);
		today.set(GregorianCalendar.MINUTE, 0);
		today.set(GregorianCalendar.SECOND, 0);
		today.set(GregorianCalendar.MILLISECOND, 0);
		assertTrue(Utilities.daysLapsed(today, Utilities.strToGregCalendar(a)) == 13);		
	}
	@Test
	public void insertTest10(){
		String a = "05/21/2015";
		GregorianCalendar today = new GregorianCalendar();
		today.set(GregorianCalendar.HOUR_OF_DAY, 0);
		today.set(GregorianCalendar.MINUTE, 0);
		today.set(GregorianCalendar.SECOND, 0);
		today.set(GregorianCalendar.MILLISECOND, 0);
		assertTrue(Utilities.daysLapsed(today, Utilities.strToGregCalendar(a)) == 33);		
	}
	@Test
	public void insertTest11(){
		String a = "05/21/2015";
		GregorianCalendar b = Utilities.strToGregCalendar(a);
		String c = Utilities.gtoString(b);
		assertTrue(c.equals(a));		
	}
	@Test
	public void insertTest12(){
		String a = "06/21/2016";
		GregorianCalendar b = Utilities.strToGregCalendar(a);
		String c = Utilities.gtoString(b);
		assertTrue(c.equals(a));		
	}
	@Test
	public void insertTest13(){
		String a = "05/21/2015";
		GregorianCalendar b = Utilities.strToGregCalendar(a);
		String c = "03/20/2015";
		GregorianCalendar d = Utilities.strToGregCalendar(c);
		assertTrue(Utilities.beforeAfter(d,b) == true);		
	}
	@Test
	public void insertTest14(){
		String a = "05/21/2015";
		GregorianCalendar b = Utilities.strToGregCalendar(a);
		String c = "03/20/2015";
		GregorianCalendar d = Utilities.strToGregCalendar(c);
		assertTrue(Utilities.beforeAfter(b,d) == false);		
	}
	@Test
	public void insertTest15(){
		String a = "04/30/2015";
		String b = "04/25/2015";
		Project p1 = new Project("CIS 350 Ethics Paper", null, Utilities.strToGregCalendar(a), "Fire", 2, false);
		Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(b), "Read", 1,false);
		assertTrue(p2.getName() == "CHM 116");	
	}
	@Test
	public void insertTest16(){
		String a = "04/30/2015";
		String b = "04/25/2015";
		Project p1 = new Project("CIS 350 Ethics Paper", null, Utilities.strToGregCalendar(a), "Fire", 2, false);
		Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(b), "Read", 1,false);
		assertTrue(p2.getSubName() == "one");	
	}
	@Test
	public void insertTest17(){
		String a = "04/30/2015";
		String b = "04/25/2015";
		Project p1 = new Project("CIS 350 Ethics Paper", null, Utilities.strToGregCalendar(a), "Fire", 2, false);
		Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(b), "Read", 1,false);
		assertTrue(p2.getReminder() == 1);	
	}
	
	
	
	
	
}
