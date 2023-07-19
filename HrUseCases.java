package enh.utilities;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class HrUseCases{

 public static void executeHrOps()  
{
   	System.out.println("Choose The Operation You Want To Perform");
	System.out.println("1.Add New Employee");
	System.out.println("2.Modify Employee Data");
	System.out.println("3.Search for Employee Data");
	System.out.println("4.Delete Employee");
	System.out.println("5.RE-Hire Employee");
	System.out.println("6.Reports");
	System.out.println("7.Logout");
	
	 int option_valid=0;
	
    while(option_valid==0)
	{

	try{
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	

	int operation=Integer.parseInt(reader.readLine());
	
	switch(operation)
	{
	case 1: Executor addingemployee = new AddEmployee();
			addingemployee.execute();
			option_valid=1;
			break;
	case 2: Executor movingemployee = new ModifyEmployee();
			movingemployee.execute();
			option_valid=1;
			break;
	case 3:Executor searchemployee = new SearchEmployee();
			searchemployee.execute();
	case 4: Executor removingemployee = new RemoveEmployee();
			removingemployee.execute();
			option_valid=1;
			break;
	case 5: Executor rehireemployee= new Rehire();
			rehireemployee.execute();
			option_valid=1;
			break;
	case 6:Executor reportsofemployee= new Reports();
			reportsofemployee.execute();
			option_valid=1;
	case 7: System.out.println("Good Bye :-) | See You Again");
			option_valid=1;
			HrLoginPanel panel = new HrLoginPanel();
			panel.hrLogin();
	default: System.out.println("Please Enter Valid Choice");
            break;
	}
	}
    
    catch(Exception e)
    {
    	option_valid=0;
    	System.out.println("Please Enter Valid Choice");
    	
    }
	}
}
	
}
