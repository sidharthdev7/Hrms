package enh.utilities;

public static boolean validateUserId(String employeeId)
{
	boolean id_available=false;
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
		                    
		                    if (id.equals(employeeId)) {
		                    	id_available= true;
		                    }
		                   
		                }
		            }
		        }
	catch(Exception e)
	{
		System.out.println(e);
	}
	return id_available;
}


public  String readId()
{
	int useridvalid=1;
	String userid="";
	System.out.println("Enter User ID to Continue");
	try{
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(useridvalid==1)
		{ 
		userid=reader.readLine();
		if(validateUserId(userid))
		{
		  useridvalid=0;
			
		}
		else if(userid.equalsIgnoreCase("back"))
		{
			this.execute();
		}
		else{
				System.out.println("User Doesn't Exist with ID : "+userid +" Enter Valid Id OR TYPE 'BACK' to Go Back");
			
		}
	} 
}
catch(Exception e)
{
	System.out.println(e);
}return userid;
}