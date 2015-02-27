package package1;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTests {
	
	//insert tests
  @Test
  public void insertTest() {
	  
    Date d = new Date(1,1,2015);
    Project p1 = new Project("Test1", d, "Test", "Note");
    Assert.assertEquals("Test1 Thu Jan 01 00:00:00 EST 2015 Test Note",
    		p1.toString());
  }
  
  @Test
  public void insertTest2() {
	  
      Date d = new Date(1,1,2015);
	  Project p1 = new Project("Test1", d, "CIS 163", "Fire");
	  Project p2 = new Project("Test2", d, "CIS 350", "");
	  
	  String test = p1.toString() + "'\n'" + p2.toString();
	  Assert.assertEquals("Test1 Thu Jan 01 00:00:00 EST 2015 CIS 163 Fire\n"
	  		+ "Test2 Thu Jan 01 00:00:00 EST 2015 CIS 350 ", test);
  }
  
}
