package enh.userutil;
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
import enh.utilities.Additionals;
public class EmployeeAdder{
public static   void add(String firstname, String lastname, String dob, String gender, String department, String designation, String manager, String organization, String country, String state,String aadharencrypted,
 String dateofjoining, String dateoftermination, String salary, String username, String email, String status, String encryptedpassword,String password) {
    try {


    if(department.equalsIgnoreCase("hr")&&designation.equalsIgnoreCase("manager"))
    {
        //System.out.print("Department is Hr"); //add employee details to hr file if the designation is HR
        Additionals hrCreator = new Additionals();
        hrCreator.createHr(username,password);
        System.out.println("Employee Identified As HR : Access to the HRMS has been Approved");
        System.out.println("Please Note HR Credentials");
        System.out.println("Username : "+username);
        System.out.println("Password : "+password);

    }
    if(designation.equalsIgnoreCase("manager"))
    {
        //System.out.print("Department is Hr"); //add employee details to hr file if the designation is HR
        Additionals hrCreator = new Additionals();
        hrCreator.createManager(firstname,department);
        System.out.println("Employee Identified As Manager: Registered as Manager");

    }
       // Create the XML document
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document;

        // Check if the XML file already exists
        File file = new File("C:/Users/sidharth dev/Desktop/xml test/xmlfiles/EmployeeMasterData.xml");
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

        // Create the data element and add it to the root element
        Element dataElement = document.createElement("employee");
        rootElement.appendChild(dataElement);

        // Find the last entry position and increment it by 1 to get the new ID
        int lastEntryPosition = findLastEntryPosition(document);
        String dataId = "ENHEMP"+String.valueOf(lastEntryPosition + 1);
        dataElement.setAttribute("id", dataId);

        // Add the parameter data as child elements to the data element
        createElementWithValue(document, dataElement, "firstname", firstname);
        createElementWithValue(document, dataElement, "lastname", lastname);
        createElementWithValue(document, dataElement, "dob", dob);
        createElementWithValue(document, dataElement, "gender", gender);
        createElementWithValue(document, dataElement, "department", department);
        createElementWithValue(document, dataElement, "designation", designation);
        createElementWithValue(document, dataElement, "manager", manager);
        createElementWithValue(document, dataElement, "organization", organization);
        createElementWithValue(document, dataElement, "country", country);
        createElementWithValue(document, dataElement, "state", state);
        createElementWithValue(document, dataElement, "aadharencrypted", aadharencrypted);
        createElementWithValue(document, dataElement, "dateofjoining", dateofjoining);
        createElementWithValue(document, dataElement, "dateoftermination", dateoftermination);
        createElementWithValue(document, dataElement, "salary", salary);
        createElementWithValue(document, dataElement, "username", username);
        createElementWithValue(document, dataElement, "email", email);
        createElementWithValue(document, dataElement, "status", status);
        createElementWithValue(document, dataElement, "encryptedpassword", encryptedpassword);

        // Write the XML back to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        OutputStream outputStream = new FileOutputStream(file);
        StreamResult result = new StreamResult(outputStream);
        transformer.transform(source, result);

        System.out.println("Employee Added Successfully: Employee ID - "+dataId);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static void createElementWithValue(Document document, Element parentElement, String tagName, String value) {
    Element element = document.createElement(tagName);
    element.appendChild(document.createTextNode(value));
    parentElement.appendChild(element);
}
private static int findLastEntryPosition(Document document) {
    int position = 0;
    NodeList dataList = document.getElementsByTagName("employee");
    position=dataList.getLength()+1;
    
    return position;
}}