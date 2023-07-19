package enh.utilities;
import enh.userutil.Validator;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class HrCredentialValidator{
   

	public static boolean validateUsername(String username)
	{
		boolean valid = false;
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
                valid=true;

            }                      
                
            }
        }        
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            
        }
        return valid;
	}
    public static boolean userIsActive(String username)
    { 
        boolean status=false;
        try {            
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(new File("xmlfiles/EmployeeMasterData.xml"));

                // Get all employee elements
                NodeList employeeNodes = doc.getElementsByTagName("employee");

                for (int i = 0; i < employeeNodes.getLength(); i++) {
                    Element employeeElement = (Element) employeeNodes.item(i);

                    // Get the username element within the employee element
                    Element usernameElement = (Element) employeeElement.getElementsByTagName("username").item(0);
                    Element statusElement = (Element) employeeElement.getElementsByTagName("status").item(0);
                    String usernameinxml=usernameElement.getTextContent();
                    String statusinxml=statusElement.getTextContent();

                    
                    if(usernameinxml.equalsIgnoreCase(username)&&statusinxml.equalsIgnoreCase("Active"))
                    {
                        //System.out.println("");
                        status = true;
                    }   
                    
                 }

            } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
                e.printStackTrace();
               
            }
           return status;
        }
    public static boolean validatePassword(String username, String password) {
        
        try {
            String filePath="xmlfiles/HrCredentials.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filePath));

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // Construct XPath expression to find user element with the given username
            String expression = "/users/user[username='" + username + "']";
            Element userElement = (Element) xPath.evaluate(expression, doc, XPathConstants.NODE);

            if (userElement != null) {
                // Get the password element within the user element
                Element passwordElement = (Element) userElement.getElementsByTagName("password").item(0);

                if (passwordElement != null && password.equals(passwordElement.getTextContent())) {
                    return true; // Password is valid
                }
            }

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }

        return false; // Password is invalid or user not found
    }
}
