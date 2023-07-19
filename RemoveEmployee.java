package enh.utilities;
import enh.userutil.Searcher;
import enh.userutil.Encrypter;
import enh.userutil.XmlUpdater;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import java.time.LocalDate;

public class RemoveEmployee implements Executor{

	public void execute()
	{
		
		SearchEmployee idreaders =  new SearchEmployee();
		System.out.println("Remove Employee Using");
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
					if(idreaders.validateUserId(input))
					{
						removeEmp(input);//search with id
					}
					option_valid=0;
					break;
		
			
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
		SearchEmployee idreaders =  new SearchEmployee();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(useridvalid==1)
		{ 
		userid=reader.readLine();
		if(idreaders.validateUserId(userid))
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


public void removeEmp(String employee_id)
{
	HrCredentialValidator validator = new HrCredentialValidator();
	Rehire getter = new Rehire();	 
	
		boolean active = validator.userIsActive(getter.getUserName(employee_id));
		if(!active)
		{
			System.out.println("Employee is Already inActive Cannot Remove");
			this.execute();
		}
		else
			try{DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document document = docBuilder.parse(new File("xmlfiles/EmployeeMasterData.xml"));
		            
		            // Find the employee element with the given ID
		            NodeList employeeList = document.getElementsByTagName("employee");
		            for (int i = 0; i < employeeList.getLength(); i++) {
		                Node employeeNode = employeeList.item(i);
		                if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
		                    Element employeeElement = (Element) employeeNode;
		                    String id = employeeElement.getAttribute("id");
		                    
		                    if (id.equalsIgnoreCase(employee_id)) {

		                    	//Change Status of employee to Inactive
		                    	NodeList statusList = employeeElement.getElementsByTagName("status");
		                        Element statusElement = (Element) statusList.item(0);
		                        statusElement.setTextContent("Inactive");
			                   
			                   //Modify Date of Termination
		                        NodeList dotList = employeeElement.getElementsByTagName("dateoftermination");
		                        Element dotElement = (Element) dotList.item(0);
		                        LocalDate today = LocalDate.now();
		                        String today_date = today.toString();
		                        dotElement.setTextContent(today_date);

			                   //Save The Modified File
			                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		                        Transformer transformer = transformerFactory.newTransformer();
		                        DOMSource source = new DOMSource(document);
		                        StreamResult result = new StreamResult(new File("xmlfiles/EmployeeMasterData.xml"));
		                        transformer.transform(source, result);
		                        
		                        //Department of the employee
		                        NodeList departmentList = employeeElement.getElementsByTagName("department");
		                        Element departmentElement = (Element)departmentList.item(0);
		                        String department = departmentElement.getTextContent();

		                        //Firstname of the employee to refer it to manager file
		                        NodeList firstnameList = employeeElement.getElementsByTagName("firstname");
		                        Element firstnameElement = (Element) firstnameList.item(0);
		                        String firstnameofemployee = firstnameElement.getTextContent();

		                        //Get Designation of the Employee : Remove from Manager File If Designation is Empty
		                        //Allot Employees With Default Manager 
		                        NodeList designationList = employeeElement.getElementsByTagName("designation");
		                        Element designationElement = (Element) designationList.item(0);
		                        if(designationElement.getTextContent().equalsIgnoreCase("manager"))
		                        {	String firstnameofmanager = firstnameofemployee;
		                        	XmlUpdater update =  new XmlUpdater();
		                        	String defaultname = getDefaultManagerName();//default manager in managers xml file
		                        	update.removeManagerFromXML(department,firstnameofmanager);
		                        	update.replaceManagerOf(firstnameofmanager,defaultname);

		                        }

		                        System.out.println("User Removed : Status Deactivated");
		                        HrUseCases usecases = new HrUseCases();
		                         usecases.executeHrOps();


			               		}      		
		                  }
		             }}
		             catch(Exception e)
		             {
		             	System.out.println(e);
		             }
}  

public String getDefaultManagerName()
{
	String managername="";
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Managers.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the MANAGER nodes
            NodeList managerNodes = rootElement.getElementsByTagName("DEFAULT");

            // Check if the MANAGER exists
            
            for (int i = 0; i < managerNodes.getLength(); i++) {
                Node managerNode = managerNodes.item(i);
                 managername = managerNode.getTextContent();
                
                }
             
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          return managername;
     
}   }