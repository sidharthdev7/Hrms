import enh.utilities.HrLoginPanel;
import java.io.InputStreamReader;
import java.io.BufferedReader;
public class Homepage{
	public static void main(String[] args) {

		int user_choice;
		int datavalid_status=1;
		System.out.println("Welcome To ENH - ISECURE : HUMAN RESOURCE MANAGEMENT SYSTEM");
		System.out.println("Enter 1 To Proceed With Login If You Are HR-MANAGER");
		System.out.println("Enter Any Other Number To Exit ");

		while(datavalid_status==1)
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			user_choice=Integer.parseInt(reader.readLine());
			
			if(user_choice==1)
			{
				//Object Instantiation For HrPanel Class
				HrLoginPanel loginpanel = new HrLoginPanel();
				loginpanel.hrLogin();

			}
			else
			{
				System.out.println("Good Bye :-) ");
				System.exit(0);
			}
			datavalid_status=0;
		}
		catch(Exception e)
		{
			System.out.println("Please Enter Valid Choice");
			datavalid_status=1;
		}

	}
}