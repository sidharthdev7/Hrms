package enh.utilities;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Additionals{

	public static   void createHr(String username,String password) {
    try {

       // Create the XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document;

        // Check if the XML file already exists
        File file = new File("C:/Users/sidharth dev/Desktop/xml test/xmlfiles/HrCredentials.xml");
        if (file.exists()) {
            document = docBuilder.parse(file);
        } else {
            document = docBuilder.newDocument();
        }

        // Get the root element or create it if it doesn't exist
        Element rootElement = document.getDocumentElement();
        if (rootElement == null) {
            rootElement = document.createElement("root");
            document.appendChild(rootElement);
        }
        Element dataElement = document.createElement("user");
        rootElement.appendChild(dataElement);
        // Add the parameter data as child elements to the data element

        createElementWithValue(document, dataElement, "username", username);
        createElementWithValue(document, dataElement, "password", password);
       
        // Write the XML back to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        OutputStream outputStream = new FileOutputStream(file);
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);

     //   System.out.println("Employee Added Successfully");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static   void createManager(String firstname,String dept) {
     try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("C:/Users/sidharth dev/Desktop/xml test/xmlfiles/Managers.xml"));

            // Get the root element
            Element rootElement = doc.getDocumentElement();

            // Create a new manager element
            Element managerElement = doc.createElement(dept.toUpperCase());
            managerElement.setTextContent(firstname);

            // Append the new manager element to the root element
            rootElement.appendChild(managerElement);

            // Write the updated document back to the XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/sidharth dev/Desktop/xml test/xmlfiles/Managers.xml"));
            transformer.transform(source, result);
           
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | TransformerException e) {
            e.printStackTrace();
        }
}
private static void createElementWithValue(Document document, Element parentElement, String tagName, String value) {
    Element element = document.createElement(tagName);
    element.appendChild(document.createTextNode(value));
    parentElement.appendChild(element);
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

}