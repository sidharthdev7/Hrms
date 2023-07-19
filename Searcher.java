package enh.userutil;
import 	enh.utilities.HrUseCases;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Searcher{

public static void getDataOfId(String empid)
{
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
		                    
		                    if (id.equalsIgnoreCase(empid)) {
		                    	Decrypter decrypter = new Decrypter();
		                    	 
		                    	String[] fields = {"firstname", "lastname", "dob", "gender", "department", "designation", "manager", "organization", "country",
                   									"dateofjoining", "dateoftermination", "salary", "username", "email", "status"};

		                    	 for(String field:fields)
		                    	 {NodeList nList = employeeElement.getElementsByTagName(field);
			                     Element eElement = (Element) nList.item(0);
			                     String eString = eElement.getTextContent();
			                     System.out.println(field.toUpperCase() +" : "+eString);			                    
			                 	}
			                 	//Password
			                 	 NodeList passList = employeeElement.getElementsByTagName("encryptedpassword");
			                     Element passElement = (Element) passList.item(0);
			                     String passString = passElement.getTextContent();
			                     System.out.println("PASSWORD : "+decrypter.decryptPass(passString));
			                     //aadhar
			                     NodeList aadharList = employeeElement.getElementsByTagName("aadharencrypted");
			                     Element aadharElement = (Element) aadharList.item(0);
			                     String aadharString = aadharElement.getTextContent();
			                     System.out.println("AADHAR  : "+decrypter.decryptAadhar(aadharString));
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

}