package enh.userutil;
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
public class XmlUpdater{
	public static void updateHr(String newfirstname,String username)
{

		File xmlFile = new File("xmlfiles/HrCredentials.xml"); // Replace with your XML file path
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

           // Get all user elements
            NodeList userList = doc.getElementsByTagName("user");

            for (int i = 0; i < userList.getLength(); i++) {
            Element userElement = (Element) userList.item(i);

            // Get the username element within the user element
            Element usernameElement = (Element) userElement.getElementsByTagName("username").item(0);

            if (username.equalsIgnoreCase(usernameElement.getTextContent())) {
                
                String oldusername = usernameElement.getTextContent();
            	String lastname = oldusername.substring(oldusername.indexOf(".")+1);
            	String newusername = newfirstname+"."+lastname;
            	usernameElement.setTextContent(newusername);
            	System.out.println("New User Name :"+newusername);
            	
            	// Save the modified XML back to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("xmlfiles/HrCredentials.xml"));
                transformer.transform(source, result);
            }  
                          
            }
        }        
        catch (Exception e) {
            e.printStackTrace();
            
        }
       

}
public static void updateManager(String newfirstname,String oldname,String department)
{
	try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Managers.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the MANAGER nodes
            NodeList managerNodes = rootElement.getElementsByTagName(department.toUpperCase());

            // Check if the MANAGER exists
            
            for (int i = 0; i < managerNodes.getLength(); i++) {
                Node managerNode = managerNodes.item(i);
                String managername = managerNode.getTextContent();
                if (managername.equalsIgnoreCase(oldname)) {
                    
                    managerNode.setTextContent(newfirstname);
                // Save the modified XML back to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File("xmlfiles/Managers.xml"));
                transformer.transform(source, result);

                }
             } 
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          
		
	}


public static void removeManagerFromXML(String department , String firstnameofmanager)
{
     try {
            // Load the XML file
            File xmlFile = new File("xmlfiles/managers.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Find the <Department> element with the text "MARIE"
            NodeList departmentNodes = doc.getElementsByTagName(department.toUpperCase());
            for (int i = 0; i < departmentNodes.getLength(); i++) {
                Element departmentElement = (Element) departmentNodes.item(i);
                if (departmentElement.getTextContent().equalsIgnoreCase(firstnameofmanager)) {
                    // Remove the element
                    Node parent = departmentElement.getParentNode();
                    parent.removeChild(departmentElement);
                }
            }

            // Save the modified XML back to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("xmlfiles/managers.xml"));
            transformer.transform(source, result);

            System.out.println("Employee"+firstnameofmanager+" Identified as Manager: Terminated and Removed from Manger File");

        } catch (Exception e) {
            e.printStackTrace();
        }
}
public static void replaceManagerOf(String firstnameofmanager,String defaultmanager)
{
   try {int found = 0;

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
                           NodeList managerList = employeeElement.getElementsByTagName("manager");
                            Node managerNode = managerList.item(0);
                            String manager = managerNode.getTextContent();
                           
                            if (manager.equalsIgnoreCase(firstnameofmanager)) {
                                found++;
                                managerNode.setTextContent(defaultmanager.toUpperCase());
                                // Save the modified XML back to the file
                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                Transformer transformer = transformerFactory.newTransformer();
                                DOMSource source = new DOMSource(document);
                                StreamResult result = new StreamResult(new File("xmlfiles/EmployeeMasterData.xml"));
                                transformer.transform(source, result);
                                
                            }
                        }
                      }System.out.println("Found "+found+" Employee(s) with "+firstnameofmanager+" As Manager , Alloted Default Manager");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }                
}
}
