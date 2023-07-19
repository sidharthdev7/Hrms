package enh.utilities;
import enh.userutil.Searcher;
import enh.userutil.Encrypter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;


public class SearchEmployee implements Executor{

	public void execute()
	{
		ModifyEmployee idreader = new ModifyEmployee();
		Searcher search = new Searcher();
		System.out.println("Serach For Employee Using");
		System.out.println("1.Employee ID");
		//System.out.println("2.Aadhar ID");
		int option_valid=1;
		while(option_valid==1)	
	{
		try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userchoice = reader.readLine();
		String input="";

		switch(userchoice)
		{
		case "1" : input=readId();
					if(validateUserId(input))
					{
						search.getDataOfId(input);//search with id
					}
					option_valid=0;
					break;
		// case "2" : input=readAadhar();
		// 			if(validateAadharId(input))
		// 			{
		// 				search.getDataOfAadhar(input);
		// 			}
		// 			option_valid=0;
		// 			break;
			
		default : System.out.println("Please Choose A Valid Option");

		}
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}	
	}

	}


	public String readId()
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
	}

	public boolean validateUserId(String userid)
	{
		
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
		                    
		                    if (id.equals(userid)) {
		                    	user_available=true;
		                    	 
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
		System.out.println("User Doesn't Exist with ID : "+userid);
	}
	
	return user_available;

	}
	// }

	
}