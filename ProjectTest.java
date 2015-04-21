package packageproj;

import java.awt.Insets;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;


public class ProjectTests {

  Insets tests;
  
  @Test
  public void insertTest() {
    String aa = "02/01/2015";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    Project p1 = new Project("Test1", null, bb, "Note", 1, false);
    Assert.assertEquals("Test1 null 1/1/2015 1 Note",
        p1.toString());
  }
  
  @Test
  public void insertTest1() {
    String aa = "05/31/2015";
    String bb = "04/25/2015";
    Project p1 = new Project("WRT 350", null, Utilities.strToGregCalendar(aa), "Fire", 2, false);
    Project p2 = new Project("CIS 263", "one", Utilities.strToGregCalendar(bb), "Read", 1,false);
    assertTrue(p2.getReminder() == 1);
  }

  @Test
  public void insertTest2() {
    String aa = "01/01/2015";
    GregorianCalendar dd = Utilities.strToGregCalendar(aa);
    Project p1 = new Project("Test1", null, dd, "Fire", 2, false);
    Project p2 = new Project("Test2", null, dd, null, 3, false);

    String test = p1.toString() + "\n" + p2.toString();
    Assert.assertEquals("Test1 null 0/1/2015 2 Fire\n" 
        + "Test2 null 0/1/2015 3 null", test);
  } 
  
  @Test
  public void insertTest3() {
    GregorianCalendar test1 = new GregorianCalendar();
    test1.set(Calendar.MONTH, 1);
    test1.set(Calendar.DAY_OF_MONTH, 1);
    test1.set(Calendar.YEAR, 2014);
    GregorianCalendar test2 = new GregorianCalendar();
    test2.set(Calendar.MONTH, 3);
    test2.set(Calendar.DAY_OF_MONTH, 9);
    test2.set(Calendar.YEAR, 2014);
    Assert.assertEquals(1, test2.compareTo(test1));
  }
  
  @Test
  public void insertTest4() {
    String aa = "04/20/2015";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = Utilities.gtoString(bb);
    assertTrue(cc.equals(aa));	

  }

  @Test
  public void insertTest5() {
    String aa = "05/21/2016";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = "02/12/2015";
    GregorianCalendar dd = Utilities.strToGregCalendar(cc);
    assertTrue(Utilities.beforeAfter(dd, bb) == true);
  }



  @Test
  public void insertTest7() {
    String dd = "04/20/2015";
    Project p1 = new Project("Test1", "Test2", Utilities.strToGregCalendar(dd), "Test", 1, false);
    Assert.assertEquals("Test1 Test2 3/20/2015 1 Test",
        p1.toString());
  }

  @Test
  public void insertTest8() {
    String aa = "04/30/2015";
    String bb = "04/25/2015";
    Project p1 = new Project("CIS 350 Ethics Paper", null, 
        Utilities.strToGregCalendar(aa), "Fire", 2, false);
    Project p2 = new Project("CIS 350 Ethics Paper", "one", 
        Utilities.strToGregCalendar(bb), "Read", 1,false);

    String test = p1.toString() + "\n" + p2.toString();
    Assert.assertEquals("CIS 350 Ethics Paper null 3/30/2015 2 Fire\n"
        + "CIS 350 Ethics Paper one 3/25/2015 1 Read", test);
  }

  @Test
  public void insertTest9() {
    GregorianCalendar today = new GregorianCalendar(2015, 03, 18);
    today.set(GregorianCalendar.HOUR_OF_DAY, 0);
    today.set(GregorianCalendar.MINUTE, 0);
    today.set(GregorianCalendar.SECOND, 0);
    today.set(GregorianCalendar.MILLISECOND, 0);
    String aa = "05/01/2015";
    assertTrue(Utilities.daysLapsed(today, Utilities.strToGregCalendar(aa)) == 13);
  }
  
  @Test
  public void insertTest10() {
    GregorianCalendar today = new GregorianCalendar(2015, 03, 18);
    today.set(GregorianCalendar.HOUR_OF_DAY, 0);
    today.set(GregorianCalendar.MINUTE, 0);
    today.set(GregorianCalendar.SECOND, 0);
    today.set(GregorianCalendar.MILLISECOND, 0);
    String aa = "05/21/2015";
    assertTrue(Utilities.daysLapsed(today, Utilities.strToGregCalendar(aa)) == 33);
  }
  
  @Test
  public void insertTest11() {
    String aa = "05/21/2015";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = Utilities.gtoString(bb);
    assertTrue(cc.equals(aa));
  }
  
  @Test
  public void insertTest12() {
    String aa = "06/21/2016";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = Utilities.gtoString(bb);
    assertTrue(cc.equals(aa));
  }
  
  @Test
  public void insertTest13() {
    String aa = "05/21/2015";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = "03/20/2015";
    GregorianCalendar dd = Utilities.strToGregCalendar(cc);
    assertTrue(Utilities.beforeAfter(dd, bb) == true);
  }
  
  @Test
  public void insertTest14() {
    String aa = "05/21/2015";
    GregorianCalendar bb = Utilities.strToGregCalendar(aa);
    String cc = "03/20/2015";
    GregorianCalendar dd = Utilities.strToGregCalendar(cc);
    assertTrue(Utilities.beforeAfter(bb, dd) == false);
  }
  
  @Test
  public void insertTest15() {
    String aa = "04/30/2015";
    String bb = "04/25/2015";
    Project p1 = new Project("CIS 350 Ethics Paper", null, 
        Utilities.strToGregCalendar(aa), "Fire", 2, false);
    Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(bb), "Read", 1,false);
    assertTrue(p2.getName() == "CHM 116");
  }
  
  @Test
  public void insertTest16() {
    String aa = "04/30/2015";
    String bb = "04/25/2015";
    Project p1 = new Project("CIS 350 Ethics Paper", null, 
        Utilities.strToGregCalendar(aa), "Fire", 2, false);
    Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(bb), "Read", 1,false);
    assertTrue(p2.getSubName() == "one");
  }
  
  @Test
  public void insertTest17() {
    String aa = "04/30/2015";
    String bb = "04/25/2015";
    Project p1 = new Project("CIS 350 Ethics Paper", null, 
        Utilities.strToGregCalendar(aa), "Fire", 2, false);
    Project p2 = new Project("CHM 116", "one", Utilities.strToGregCalendar(bb), "Read", 1,false);
    assertTrue(p2.getReminder() == 1);
  }





}
