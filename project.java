import java.util.Date;

public class project {

		String category;
		String pname;		
	    Date dueDate;		
	    String notes;		
						
	    project(){		
	    category = "Untitled";
	    pname = "Untitled";		
	    dueDate = new Date(1,1,2015);
	    notes = "";		
	   }		
			
	    project(String n, Date dd, String c, String nt){		
	        pname = n;		
	        dueDate = dd;	
	        notes = nt;		
	    }
	
	
}

