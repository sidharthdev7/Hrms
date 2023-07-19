package enh.utilities;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import enh.utilities.HrCredentialValidator;

public class HrLoginPanel{

	public static void hrLogin()
	{
		System.out.println("Welcome To ENH-iSecure HR Login Panel");
		
		HrCredentialValidator validator = new HrCredentialValidator();
			System.out.println("Please Enter Your User Name");
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				int datavalid_status=1;
			
				String username = reader.readLine();
				while(datavalid_status==1)
				{
				
				if(validator.validateUsername(username)&&validator.userIsActive(username))
				{ 
					
					datavalid_status=0;
					int datavalid_status2=1;
					System.out.println("Please Enter Your Password Mr/Mrs."+username);
						String password = reader.readLine();
						while(datavalid_status2==1)
						{
						if(validator.validatePassword(username,password))
						{ datavalid_status2=0;

							System.out.println("Welcome "+username+", Login Successfull");
							HrUseCases usecase = new HrUseCases();
							usecase.executeHrOps();

						}
						else{
							System.out.println("Enter Valid Password OR ENTER 0 to EXIT");
							String checkuserchoice=reader.readLine();
							if(checkuserchoice.equals("0"))
							{	datavalid_status2=0;
								System.exit(0);

							}
							else{
							password=checkuserchoice;
							datavalid_status2=1;}
						}
					}
					
				}
				else{
					if(validator.validateUsername(username)==false)
					{System.out.println("Please Enter Valid Username Or Enter 0 to EXIT");}
					else if(validator.userIsActive(username)==false)
					{System.out.println("Access Denied User is Inactive : Enter Active User Or Enter 0 to EXIT");}
					String checkuserchoiceforusername=reader.readLine();
							if(checkuserchoiceforusername.equals("0"))
							{
								datavalid_status=0;
								System.exit(0);

							}
							else{
							username=checkuserchoiceforusername;
							datavalid_status=1;}

				}


			}

			}
			catch(Exception e)
			{
				System.out.println(e);
			}		
	}
}