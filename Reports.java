package enh.utilities;
import enh.userutil.ReportData;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Reports implements Executor{

	public void execute()
	{
		System.out.println("| ENH-REPORTS - CHOOSE REPORT CATEGORY");
		System.out.println("1.MANAGERS REPORT");
		System.out.println("2.EMPLOYEES REPORT");
		System.out.println("3.ORGANISATION MASTER DATA");
		System.out.println("4.SPECIFIC USER DATA");
		System.out.println("5.OTHER HR OPERATIONS");

		int option_valid=1;

				while(option_valid==1)	
				{
					try{
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String userchoice = reader.readLine();
					ReportData report = new ReportData();

					switch(userchoice)
					{
					case "1" : 	report.getManagersData();
								option_valid=0;
								break;
					case "2" : 	report.getEmployeesData();
								option_valid=0;
								break;
					case "3" : 	report.getMasterData();
								option_valid=0;
								break;
					case "4" :  SearchEmployee empreport = new SearchEmployee();
								empreport.execute();

					case "5" :	HrUseCases usecases = new HrUseCases();
								usecases.executeHrOps();option_valid=0;break;
					default : System.out.println("Please Choose A Valid Option");

					}
				}
				catch(Exception e)
				{
					System.out.println(e);
					
				}	
				}
		}
	}
