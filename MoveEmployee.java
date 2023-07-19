package enh.utilities;
import java.io.BufferedStreamReader;
import java.io.InputStreamReader;


public class ModifyEmployee implements Executor{

	HrUseCases usecases = new HrUseCases();
	HrLoginPanel panel = new HrLoginPanel();
	public  void execute()
	{

		EmployeeModifier modifier = new EmployeeModifier();
		System.out.println("Choose the Operation You Want To Perform");
		System.out.println("1.Modify Employee's Designation");
		System.out.println("2.Modify Employee's firstname or lastname");
		System.out.println("3.Other HR Operations");
		System.out.println("4.LOGOUT");
		BufferedStreamReader reader = new BufferedStreamReader(new InputStreamReader(System.in));
		String userchoice = reader.nextLine();
		String userid="";

		switch(userchoice)
		{
		case "1" : System.out.println("Enter User ID to Continue");
					userid=reader.readLine();
					modifier.modifyDesignation(userid);
					break;
		case "2" :System.out.println("Enter User ID to Continue");
					userid=reader.readLine();
					 modifier.modifyName();break;
		case "3" : System.out.println("Logout Successfull | See You Again :-)");
					usecases.executeHrOps();break;

		case "4" : panel.hrLogin();

		}
	}

}