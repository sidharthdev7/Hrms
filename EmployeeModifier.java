package enh.userutil;
import enh.utilities.HrUseCases;
import enh.utilities.ModifyEmployee;
import enh.utilities.Additionals;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import enh.userutil.Encrypter;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.File;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EmployeeModifier{


public static void modifyDesignation(String employeeId)
{
		String department = getDepartment(employeeId);
		String newdesignation = getDes(employeeId,department);
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
		                        // Update the designation
		                        NodeList designationList = employeeElement.getElementsByTagName("designation");
		                        Element designationElement = (Element) designationList.item(0);
		                        designationElement.setTextContent(newdesignation);

		                        // Update the salary based on designation
		                        NodeList salaryList = employeeElement.getElementsByTagName("salary");
		                        Element salaryElement = (Element) salaryList.item(0);
		                        Salary salaryobj = new Salary();
		                        String newsalary = (salaryobj.calculate(department,newdesignation));
		                        salaryElement.setTextContent(newsalary);
		                        

		                        //add the employee in manager xml if designation is manager
		                        if(newdesignation.equalsIgnoreCase("manager"))
							    {
							        //Get Employee's First Name
							        NodeList firstnameList = employeeElement.getElementsByTagName("firstname");
			                        Element firstnameElement = (Element) firstnameList.item(0);
			                       	String firstname = firstnameElement.getTextContent();
							        Additionals creator = new Additionals();
							        creator.createManager(firstname,department);
							        System.out.println("Employee Identified As Manager: Registered as Manager");

							    }

							    //ADD EMPLOYEE CREDENTIALS IF THE EMPLOYEE IS DESIGNATED AS MANAGER IN HR DEPT
							    if(department.equalsIgnoreCase("hr")&&newdesignation.equalsIgnoreCase("manager"))
							    {	//get username from the XML of the ID provided
							        NodeList usernameList = employeeElement.getElementsByTagName("username");
			                        Element usernameElement = (Element) usernameList.item(0);
			                       	String usernameString = usernameElement.getTextContent();
							       
							       //get password from the XML of the ID provided
			                       	NodeList passwordList = employeeElement.getElementsByTagName("encryptedpassword");
			                        Element passwordElement = (Element) passwordList.item(0);
			                       	String encryptedPassword = passwordElement.getTextContent();
			                       	//Password is stored in encrypted format we need to decrypt it
			                       	Decrypter decrypter = new Decrypter();
			                       	String password = decrypter.decryptPass(encryptedPassword);//plain password
							        Additionals hrCreator = new Additionals();
							        hrCreator.createHr(usernameString,password);
							        System.out.println("Employee Identified As HR : Access to the HRMS has been Approved");
							        System.out.println("Please Note HR Credentials");
							        System.out.println("Username : "+usernameString);
							        System.out.println("Password : "+password);

							    }
		                        // Save the modified XML back to the file
		                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		                        Transformer transformer = transformerFactory.newTransformer();
		                        DOMSource source = new DOMSource(document);
		                        StreamResult result = new StreamResult(new File("xmlfiles/EmployeeMasterData.xml"));
		                        transformer.transform(source, result);
		                        
		                        System.out.println("Designation modified successfully");
		                        HrUseCases usecases = new HrUseCases();

		                         usecases.executeHrOps();

		                    }
		                }
		            }
		            
		            System.out.println("Employee ID not found.");
		        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
		            e.printStackTrace();
		        }	
}

public static String getDepartment(String employeeId)
{
	String department="";
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
		                        //retrive the department of the user
		                        NodeList departmentList = employeeElement.getElementsByTagName("department");
		                        Element departmentElement = (Element) departmentList.item(0);
								department = departmentElement.getTextContent(); 
		                                             
		                     }
		                }
		            }
		            		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	return department;
}

public static String getDes(String employeeId,String departmenttocheck)
{
	Validator validator =  new Validator();
	DropDown drop = new DropDown();
	String designationtoread ="";
	try{
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Enter New Designation");
	drop.listDes(departmenttocheck);
	int designvalid=1;
	
	while(designvalid==1)
	{	 designationtoread = reader.readLine();
		if(validator.validateDesignation(departmenttocheck, designationtoread))
		{
			designvalid=0;

		}
		else{
			System.out.println("Enter Valid Designation");
			//drop.listDes(department);
		}
	}}
	catch(Exception e)
	{
		System.out.println(e);
	}
	return designationtoread;
}

public static String getExistingDes(String employeeId)
{
	String department="";
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
		                        //retrive the department of the user
		                        NodeList departmentList = employeeElement.getElementsByTagName("designation");
		                        Element departmentElement = (Element) departmentList.item(0);
								department = departmentElement.getTextContent(); 
		                                             
		                     }
		                }
		            }
		            		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }	return department;
}


public static void modifyName(String employeeId)
{
	System.out.println("Modifying First Name \n NOTE : 'email id remains the same even if you change firstname' ");
	Validator validator = new Validator();
	ModifyEmployee modifier = new ModifyEmployee();
	String empid = employeeId;
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
		                   
		                    if (id.equals(empid)) {

		                    	NodeList firstNameList = employeeElement.getElementsByTagName("firstname");//get all firstnames with ID PROvided
		                        Element firstnameElement = (Element) firstNameList.item(0);
		                        String firstname = firstnameElement.getTextContent();
		                        System.out.println("First of the Employee is "+firstname);
		                       // System.out.println("Enter the NEW FIRST NAME OF THE EMPLOYEE");
		                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		                        int firstnamevalid=1;
								System.out.println("Enter Employee's new First Name");
								String newfirstname="";
								while(firstnamevalid==1)
								{	newfirstname = reader.readLine();
									if(validator.validateString(newfirstname))//validateString function validates the input data : checks if data contains only string
									{
										firstnamevalid=0;
									}
									else{
										System.out.println("Please Enter Valid Data : Value Should Contain Characters (ATLEAST 2)");
									}		
								
								}
								
								
		                        
		                        //CHECK IF EMPLOYEE IS HR : IF EMPLOYEE IS HR:MANAGER - CHANGE USERNAME IN HR CREDENTIALS FILES 
		                    	if(getDepartment(employeeId).equalsIgnoreCase("HR")&&getExistingDes(employeeId).equalsIgnoreCase("Manager"))
		                    	{
		                    		NodeList usernameList = employeeElement.getElementsByTagName("username");//get all firstnames with ID PROvided
		                        	Element usernameElement = (Element) usernameList.item(0);
		                        	String usernametobechanged = usernameElement.getTextContent();
		                        	XmlUpdater update = new XmlUpdater();
		                        	update.updateHr(newfirstname,usernametobechanged);
		                        	System.out.println("Employee Identified As HR:Manager - Updated HR Credentials");
		                         }
		                        //CHECK IF EMPLOYEE IS MANAGER - CHANGE USERNAME IN MANAGER FILE
		                    	if(getExistingDes(employeeId).equalsIgnoreCase("Manager"))
		                    	{		                    		
		                        	NodeList oldnameList = employeeElement.getElementsByTagName("firstname");//get all firstnames with ID PROvided
		                        	Element oldnameElement = (Element) oldnameList.item(0);
		                        	String oldname = oldnameElement.getTextContent();
		                        	XmlUpdater update = new XmlUpdater();
		                        	update.updateManager(newfirstname,oldname,getDepartment(employeeId));
		                        	System.out.println("Employee Identified As Manager - Updated Manager file");
		                         }
		                    	firstnameElement.setTextContent(newfirstname);	


		                    	//Change username of the employee
		                    	NodeList usernameList = employeeElement.getElementsByTagName("username");//get all firstnames with ID PROvided
		                        Element usernameElement = (Element) usernameList.item(0);
		                        String usernamenow = usernameElement.getTextContent();
		                        String lastname = usernamenow.substring(usernamenow.indexOf(".")+1);
		                        String newusername = newfirstname+"."+lastname;
		                        usernameElement.setTextContent(newusername);
		                        System.out.println("UserName Modified : New User Name :"+newusername);


		                        // Save the modified XML back to the file
		                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		                        Transformer transformer = transformerFactory.newTransformer();
		                        DOMSource source = new DOMSource(document);
		                        StreamResult result = new StreamResult(new File("xmlfiles/EmployeeMasterData.xml"));
		                        transformer.transform(source, result);
		                        System.out.println("Name Modified successfully");
		                        HrUseCases usecases = new HrUseCases();
		                        usecases.executeHrOps();




		                    }
		                }
		            }
		 }
		 catch(Exception e)
		 {
		 	System.out.println(e);
		 }


}
public static void modifyManager(String employeeId)
{
	String validept = getDepartment(employeeId);
	Validator validator =  new Validator();
	DropDown drop = new DropDown();
	System.out.println("Modifying Manager Of the Employee "+employeeId);
	try {
		            //read new Manager
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					
					drop.listManagers(validept);
					System.out.println("Enter New Manager's Name");
					int managervalid=1;
					String manager = "";
					while(managervalid==1)
					{	manager = reader.readLine();
						if(validator.validateManager(validept,manager))//validateString function validates the input data : checks if data contains only string
						{
							managervalid=0;
						}
						else{
							System.out.println("Please Enter Valid Manager ");
							
						}		
					
					}

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

		                    NodeList managerNodes = employeeElement.getElementsByTagName("manager");
		                    Node managerNode = managerNodes.item(0);
		                    String existing_manager = managerNode.getTextContent();
		                    System.out.println("Current Manager"+existing_manager);
		                    managerNode.setTextContent(manager.toUpperCase());

		                    // Save the modified XML back to the file
		                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		                        Transformer transformer = transformerFactory.newTransformer();
		                        DOMSource source = new DOMSource(document);
		                        StreamResult result = new StreamResult(new File("xmlfiles/EmployeeMasterData.xml"));
		                        transformer.transform(source, result);
		                        System.out.println("Manager Modified Successfully");
		                        HrUseCases usecases = new HrUseCases();
		                        usecases.executeHrOps();
		                }}



}
}catch(Exception e)
{

	System.out.println(e);
}}
}