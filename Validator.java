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
import java.time.LocalDate;


public class Validator{

	//String Validator Function
	public  static boolean validateString(String input)
	{
		String pattern="^[a-zA-Z]{2,}$";
 		if(Pattern.matches(pattern,input))
		{return true;
			
		}
		else
		{
			return false;
		}
	}

	//Gender Validator
	public static boolean validateGender(String input)
	{
		if(input.equalsIgnoreCase("Male") || input.equalsIgnoreCase("Female") || input.equalsIgnoreCase("Others"))
		{
			return true;
		}
		else{
			return false;
		}
	}

	//Country Validator Check if the data exists in XML
	public static boolean validateCountry(String input)
	{
		boolean countryexists=false;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Countries.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the Country nodes
            NodeList countryNodes = rootElement.getElementsByTagName("Country");

            // Check if the country exists
            
            for (int i = 0; i < countryNodes.getLength(); i++) {
                Node countryNode = countryNodes.item(i);
                String country = countryNode.getTextContent();
                if (country.equalsIgnoreCase(input)) {
                    countryexists = true;
                    break;
                }
             } 
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          return countryexists;

	}

	//State Validator : Check if State matches the Country and exists in Xml

	public static boolean validateState(String country,String state)
	{
	    boolean state_exists = false;
	try {
	            File inputFile = new File("xmlfiles/Address.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(inputFile);
	            doc.getDocumentElement().normalize();
	            Element rootElement = doc.getDocumentElement();
	            NodeList countryList = rootElement.getElementsByTagName(country.toUpperCase());
	            
	            for(int i =0 ; i<countryList.getLength(); i++)
	            {
	                Element countryElement = (Element) countryList.item(i);
	                NodeList stateList = countryElement.getElementsByTagName(state.toUpperCase());
	                if (stateList.getLength() > 0) {
	                    state_exists=true;
	         }}
	        } catch (Exception e) {
	            state_exists = false;
	            e.printStackTrace();
	}       return state_exists;
	}

	//Validate City Based on Countr , State 
	public static boolean validateCity(String country,String state,String city)
	{
	    boolean city_exists = false;
	    try {
	                File inputFile = new File("xmlfiles/Address.xml");
	                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	                Document doc = dBuilder.parse(inputFile);
	                doc.getDocumentElement().normalize();
	                Element rootElement = doc.getDocumentElement();
	                NodeList countryList = rootElement.getElementsByTagName(country.toUpperCase());
	                for(int i=0;i<countryList.getLength();i++)
	                {
	                    Element countryElement = (Element)countryList.item(i);
	                    NodeList stateList = countryElement.getElementsByTagName(state.toUpperCase());
	                    for(int j=0;j<stateList.getLength();j++)
	                    {
	                        Node stateNode = stateList.item(j);
	                        String cityinxml = stateNode.getTextContent();
	                        if(cityinxml.equalsIgnoreCase(city))
	                        {
	                            city_exists = true;
	                        }
	                    }
	                }

	        }
	             catch (Exception e) {
	                city_exists = false;
	                e.printStackTrace();
	    }      return city_exists;
	    }
	//Validate Department : Check if the data exists in XML 
	public static boolean validateDept(String input)
	{
		boolean available=false;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Departments.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList deptNodes = rootElement.getElementsByTagName("Department");

            // Check if the department exists
            
            for (int i = 0; i < deptNodes.getLength(); i++) {
                Node deptNode = deptNodes.item(i);
                String dept = deptNode.getTextContent();
                if (dept.equalsIgnoreCase(input)) {
                    available = true;
                    break;
                }
             } 
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          return available;
    	
	}
	// Validate Designation based on department : Check if the data exists in XML
	public static boolean validateDesignation(String department,String designation)
	{
		  
		boolean available=false;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/DepartmentsAndDesignations.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList designationNodes = rootElement.getElementsByTagName(department.toUpperCase());

            // Check if the department exists
            
            for (int i = 0; i < designationNodes.getLength(); i++) {
                Node designationNode = designationNodes.item(i);
                String designationvalue = designationNode.getTextContent();
                if (designationvalue.equalsIgnoreCase(designation)) 
                {
                 available=true;break;
                 }
                }
             } 
        catch(Exception e)
          {
          	System.out.print(e);
          }
          return available;
	}

	// Validate Organisation : Check if the data exists in XML
	public static boolean validateOrg(String input)
	{
		boolean available=false;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Organisations.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the department nodes
            NodeList orgNodes = rootElement.getElementsByTagName("Organisation");

            // Check if the department exists
            
            for (int i = 0; i < orgNodes.getLength(); i++) {
                Node orgNode = orgNodes.item(i);
                String org = orgNode.getTextContent();
                if (org.equalsIgnoreCase(input)) {
                    available = true;
                    break;
                }
             } 
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          return available;
		
	}
	//Check if the data exists in XML : If the manager exists
	public static boolean validateManager(String dept,String input)
	{
		boolean available=false;
		try{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse("xmlfiles/Managers.xml");
		Element rootElement = document.getDocumentElement();
		
            // Find the MANAGER nodes
            NodeList managerNodes = rootElement.getElementsByTagName(dept.toUpperCase());

            // Check if the MANAGER exists
            
            for (int i = 0; i < managerNodes.getLength(); i++) {
                Node managerNode = managerNodes.item(i);
                String managername = managerNode.getTextContent();
                if (managername.equalsIgnoreCase(input)) {
                    available = true;
                    break;
                }
             } 
          }
          catch(Exception e)
          {
          	System.out.print(e);
          }
          return available;
		
	}

	// Validate aadhar number format and aadhar as primary attribute
	public static boolean validateAadhar(String input)
	{
		boolean valid=false;
		String pattern="^[0-9]{12}$";
	 		
	 		if(Pattern.matches(pattern,input))
			{
				Encrypter encrypter = new Encrypter();
				String encryptedAadhar =encrypter.encrypt(input);
				//check if encryptedAadhar is already in the existing data of employees
			
		        try {            
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	            Document doc = docBuilder.parse(new File("xmlfiles/EmployeeMasterData.xml"));

	            // Get all employee elements
	            NodeList employeeNodes = doc.getElementsByTagName("employee");

	            for (int i = 0; i < employeeNodes.getLength(); i++) {
	                Element employeeElement = (Element) employeeNodes.item(i);

	                // Get the username element within the employee element
	                Element usernameElement = (Element) employeeElement.getElementsByTagName("aadharencrypted").item(0);
	                String elemetdata=usernameElement.getTextContent();
	                
	                if (usernameElement != null && encryptedAadhar.equals(elemetdata)) {
	                	System.out.println("User with Aadhar Provided Already Exists");
	                    valid=false; break;// Username exists
	                }
	                else{
	                	valid=true;
	                }
	            }

	        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
	            e.printStackTrace();
	            valid=false;
	        }
				 
		}
			else
			{
				System.out.println("Enter Valid Aadhar Number : 12 Digit");
				valid=false;
			}

			return valid;
	}	
	// Validate Date Of Birth - Format/Age/ValidDate
	public static boolean validateDOB(String dob){

		String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
		if(Pattern.matches(pattern,dob))
		{	String yearinstring=dob.substring(0,4);
			String monthinstring=dob.substring(5,7);
			String dateinstring=dob.substring(8,10);
			int year = Integer.parseInt(yearinstring);
			int month = Integer.parseInt(monthinstring);
			int date = Integer.parseInt(dateinstring);
			LocalDate today = LocalDate.now();
			int this_year = today.getYear();
			int age=this_year-year;
			if(age>18)
			{
				if(year>1950&&month<13&&date<=31)
				{
					return true;
				}
				else{
					
					System.out.println("Enter Valida Date / Month / Year");
					return false;
				}
			}
			else{				
				System.out.println("Employee Identified As Underaged");
				return false;
			}
		}
		else{
			return false;	
		}
	}

	}


