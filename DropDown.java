package enh.userutil;
import java.util.regex.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import enh.userutil.Encrypter;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
public class DropDown{

public static void listDept()
{
	try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Departments.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList deptNodes = rootElement.getElementsByTagName("Department");

            // Check if the department exists
             System.out.println("These are the list of Department(s) Available:");
            for (int i = 0; i < deptNodes.getLength(); i++) {
                Node deptNode = deptNodes.item(i);
                String dept = deptNode.getTextContent();
               	System.out.println(dept);
                } 
          }
          catch(Exception e)
          {
          	System.out.println(e);
          }
}
public static void listOrg()
{
	try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Organisations.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList orgNodes = rootElement.getElementsByTagName("Organisation");

            // Check if the department exists
             System.out.println("These are the list of Organisation(s) Available:");
            for (int i = 0; i < orgNodes.getLength(); i++) {
                Node orgNode = orgNodes.item(i);
                String org = orgNode.getTextContent();
               	System.out.println(org);
                } 
          }
          catch(Exception e)
          {
          	System.out.println(e);
          }
}

public static void listDes(String department)
{
	try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/DepartmentsAndDesignations.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList designationNodes = rootElement.getElementsByTagName(department.toUpperCase());

            // Check if the department exists
            System.out.println("List Of Available Designations in "+ department+" department");
            for (int i = 0; i < designationNodes.getLength(); i++) {
                Node designationNode = designationNodes.item(i);
                String designationvalue = designationNode.getTextContent();
                System.out.println(designationvalue);
                }
             } 
        catch(Exception e)
          {
          	System.out.print(e);
          }
}

public static void listManagers(String department)
{
    try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("xmlfiles/Managers.xml");
        Element rootElement = document.getDocumentElement();
        
            // Find the manager nodes of specified dept
            NodeList managerNodes = rootElement.getElementsByTagName(department.toUpperCase());

            // Check if the managers exists
            System.out.println("The List of Available Managers");
            for (int i = 0; i < managerNodes.getLength(); i++) {
                Node managerNode = managerNodes.item(i);
                String managername = managerNode.getTextContent();
                System.out.println(managername);
                }
             } 
          catch(Exception e)
          {
            System.out.print(e);
          }
}
public static void listStates(String country)
{

try {       System.out.println("The Available States in "+country.toUpperCase());
            File inputFile = new File("xmlfiles/Address.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Element rootElement = doc.getDocumentElement();
            NodeList countryList = rootElement.getElementsByTagName(country.toUpperCase());
            for(int i=0;i<countryList.getLength();i++)
            {
                //Node counrtyNode = countryList.item();
                Element countryElement = (Element)countryList.item(i);
                NodeList states = countryElement.getChildNodes();
                for(int j=0;j<states.getLength();j++)
                {
                    Node state = states.item(j);
                    if(state.getNodeType()==Node.ELEMENT_NODE)
                   { System.out.println(state.getNodeName()+"");}
                    
                }
                //System.out.print(countryElement);
        } }catch (Exception e) {
           
            e.printStackTrace();
}       
}
public static void listCities(String country,String state)
{

try {       System.out.println("The Available Cities in "+state.toUpperCase());
            File inputFile = new File("xmlfiles/Address.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Element rootElement = doc.getDocumentElement();
            NodeList countryList = rootElement.getElementsByTagName(country.toUpperCase());
            for(int i=0;i<countryList.getLength();i++)
            {
                //Node counrtyNode = countryList.item();
                Element countryElement = (Element)countryList.item(i);
                NodeList statesList = countryElement.getElementsByTagName(state.toUpperCase());
                for(int j=0;j<statesList.getLength();j++)
                {
                    Node stateNode = statesList.item(0);
                    System.out.println(stateNode.getTextContent());
                }
        } }catch (Exception e) {
           
            e.printStackTrace();
}       
}
}