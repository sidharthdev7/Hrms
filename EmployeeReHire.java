package enh.util;
import enh.utilities.HrUseCases;
import enh.utilities.HrCredentialValidator;
import enh.utilities.Additionals;
import enh.utilities.Rehire;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import java.time.LocalDate;
import java.io.File;



	public void rehire(String empid)
	{	HrCredentialValidator validator = new HrCredentialValidator();
		 
		boolean active = validator.userIsActive(getUserName(empid));
		if(active)
		{
			System.out.println("Employee is Active Cannot Re-Hire");
			this.execute();
		}
		else{
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
		                    
		                    if (id.equalsIgnoreCase(empid)) {

		                    	//Change Status of employee to Inactive
		                    	NodeList statusList = employeeElement.getElementsByTagName("status");
		                        Element statusElement = (Element) statusList.item(0);
		                        statusElement.setTextContent("Active");
			                   
			                   //Modify Date of Joining
		                        NodeList dojList = employeeElement.getElementsByTagName("dateofjoining");
		                        Element dojElement = (Element) dojList.item(0);
		                        LocalDate today = LocalDate.now();
		                        String today_date = today.toString();
		                        dojElement.setTextContent(today_date);

		                        //Modify Date of Termination As well
		                        NodeList dotList = employeeElement.getElementsByTagName("dateoftermination");
		                        Element dotElement = (Element) dotList.item(0);
		                        String t_date = "";
		                        dotElement.setTextContent(t_date);


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

		                        //Get Designation of the Employee : Add Manager into Manager File Employee Is Manager
		                        NodeList designationList = employeeElement.getElementsByTagName("designation");
		                        Element designationElement = (Element) designationList.item(0);
		                        if(designationElement.getTextContent().equalsIgnoreCase("manager"))
		                        {	String firstnameofmanager = firstnameofemployee;
		                        	
		                        	Additionals creator = new Additionals();
		                        	creator.createManager(firstnameofmanager,department);

		                        }

		                        System.out.println("User ReHired : Status Activated");
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
}
