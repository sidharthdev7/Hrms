package enh.userutil;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;


public class ReportData {

	public static void getManagersData()
	{ 
        int entries=0;
		try { System.out.println("Managers Data");
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
                    NodeList designationNodes = employeeElement.getElementsByTagName("designation");
                    Node designationNode = designationNodes.item(0);
                    String designation = designationNode.getTextContent();
                   
                    if ((designation.equalsIgnoreCase("manager"))) {

                    	//get employee id
                    	 String empid = employeeElement.getAttribute("id");
                    	//get username
                    	 NodeList usernameNodes = employeeElement.getElementsByTagName("username");
                    	 Node usernamenode = usernameNodes.item(0);
                    	String username = usernamenode.getTextContent();
                    	
                    	System.out.println("Employee Id: "+empid+", User Name:"+username);
                        entries++;
	                     
                       }
                      


            }
		        }
                System.out.println("Total Entries "+entries);}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}

    public static void getEmployeesData()
    { 
        int entries=0;
        try { 
              System.out.println("Employees Data");
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
                    NodeList designationNodes = employeeElement.getElementsByTagName("designation");
                    Node designationNode = designationNodes.item(0);
                    String designation = designationNode.getTextContent();
                   
                    if (!(designation.equalsIgnoreCase("manager"))) {

                        //get employee id
                         String empid = employeeElement.getAttribute("id");
                        //get username
                         NodeList usernameNodes = employeeElement.getElementsByTagName("username");
                         Node usernamenode = usernameNodes.item(0);
                        String username = usernamenode.getTextContent();
                        
                        System.out.println("Employee Id: "+empid+", User Name:"+username);
                        entries++;
                         
                       }
                      


            }
                }
                System.out.println("Total Entries "+entries);}
    catch(Exception e)
    {
        System.out.println(e);
    }
    }

    public static void getMasterData()
    {
            int entries=0;
        try { System.out.println("Organisation  Data");
                    // Parse the XML file
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new File("xmlfiles/EmployeeMasterData.xml"));
            
            // Find the employee element with the given ID
            NodeList employeeList = document.getElementsByTagName("employee");
            for (int i = 0; i < employeeList.getLength(); i++) {
                Node employeeNode = employeeList.item(i);
                if (employeeNode.getNodeType() == Node.ELEMENT_NODE) {
                                            //get employee id
                        Element employeeElement = (Element)employeeNode;
                         String empid = employeeElement.getAttribute("id");
                        //get username
                         NodeList usernameNodes = employeeElement.getElementsByTagName("username");
                         Node usernamenode = usernameNodes.item(0);
                        String username = usernamenode.getTextContent();
                        
                        System.out.println("Employee Id: "+empid+", User Name:"+username);
                        entries++;
                      }
                }
                System.out.println("Total Entries "+entries);}
    catch(Exception e)
    {
        System.out.println(e);
    }
    }

}