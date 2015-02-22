import java.util.Date;

public class sub {

	String project;
	String sname;
	Date dueDate;
	String notes;
	
	sub(){
		project = "Untitled";
		sname = "Untitled";
		dueDate = new Date(1,1,2015);
		notes = "";
	}
	
	sub(String p, String sn, Date dd, String n){
		project = p;
		sname = sn;
		dueDate = dd;
		notes = n;
	}
	
}

