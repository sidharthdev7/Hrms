package enh.utilities;
import enh.userutil.EmployeeModifier;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;


public class ModifyEmployee implements Executor{

	
	public   void execute()
	{
		HrUseCases usecases = new HrUseCases();
		HrLoginPanel panel = new HrLoginPanel();
		EmployeeModifier modifier = new EmployeeModifier();
		System.out.println("Choose the Operation You Want To Perform");
		System.out.println("1.Modify Employee's Designation");
		System.out.println("2.Modify Employee's firstname");
		System.out.println("3.Modify Employee's Manager");
		System.out.println("4.Other HR Operations");
		System.out.println("5.LOGOUT");
		int option_valid=1;

	while(option_valid==1)	
	{
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userchoice = reader.readLine();
		String userid="";

		switch(userchoice)
		{
		case "1" : userid=readId();
					if(validateUserId(userid))
					{
						modifier.modifyDesignation(userid);
					}
					option_valid=0;
					break;
		case "2" : userid=readId();
					if(validateUserId(userid))
					{modifier.modifyName(userid);}
					option_valid=0;
					break;
		case "3" : userid=readId();
					if(validateUserId(userid))
					{modifier.modifyManager(userid);}
					option_valid=0;
					break;
		case "4" : usecases.executeHrOps();option_valid=0;break;

		case "5" :	System.out.println("Logout Successfull | See You Again :-)"); 
					panel.hrLogin();option_valid=0;break;
		
		default : System.out.println("Please Choose A Valid Option");

		}
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}	
	}
	}
public static boolean validateUserId(String employeeId)
{
	boolean valid=false;
	boolean user_available=false;
	try {
		            // Parse the XML file
		            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document document = docBuilder.parse(new File("xmlfiles/EmployeeMasterData.xml"));
		            
		            // Find the employee element with the given ID
		            NodeList employeeList = document.getElementsByTagName("employee");
		            for (int i = 0; i < employeeList.getLength(); i++) {
		                Node employeeNode = employeeList.item(i);
		                if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
		                    Element employeeElement = (Element) employeeNode;
		                    String id = employeeElement.getAttribute("id");
		                    
		                    if (id.equals(employeeId)) {
		                    	user_available=true;
		                    	 NodeList usernameList = employeeElement.getElementsByTagName("username");
			                     Element usernameElement = (Element) usernameList.item(0);
			                     String usernameString = usernameElement.getTextContent();
			                     Additionals check = new Additionals();
		                    	if(check.userIsActive(usernameString))//Also check if the user is active or inactive
		                    		
		                    		{valid= true;}
		                    }
		                   
		                }
		            }
		        }
	catch(Exception e)
	{
		System.out.println(e);
	}
	if(user_available==false)
	{
		System.out.println("User Doesn't Exist with ID : "+employeeId);
	}
	else if(valid==false)
	{
		System.out.println("User is inactive ");
	}
	return valid;
}


public  String readId()
{
	int useridvalid=1;
	String userid="";
	System.out.println("Enter User ID to Continue");
	try{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(useridvalid==1)
		{ 
		userid=reader.readLine();
		if(validateUserId(userid))
		{
		  useridvalid=0;
			
		}
		else if(userid.equalsIgnoreCase("back"))
		{
			this.execute();
		}
		else{
				System.out.println(" Enter Valid Id OR TYPE 'BACK' to Go Back");
			
		}
	} 
}
catch(Exception e)
{
	System.out.println(e);
}return userid;
}}