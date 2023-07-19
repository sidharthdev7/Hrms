package enh.utilities;
import enh.userutil.Validator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

public class NameModifier {

public static void modifyFirstName(String employee_id)
{	Validator validator = new Validator();
	ModifyEmployee modifier = new ModifyEmployee();
	String empid = employee_id;
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
								firstnameElement.setTextContent(newfirstname);
								System.out.println("First Name Changed Successfully");
								modifier.execute();



		                    }
		                }
		            }
		 }
		 catch(Exception e)
		 {
		 	System.out.println(e);
		 }


}
public static void modifySecondName()
{

}

}