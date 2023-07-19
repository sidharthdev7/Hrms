package enh.utilities;
import enh.userutil.Validator;
import enh.userutil.Encrypter;
import enh.userutil.Salary;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.time.LocalDate;

public class Add{
	public static void executeAdd() {

	Validator validator = new Validator();
	Salary sal = new Salary();
	Encrypter encryptor = new Encrypter();

try{		
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
	
	//Read FirstName and validate : Reader until user enters valid Firstname
	int firstnamevalid=1;
	System.out.println("Enter First Name");
	String firstname="";
	while(firstnamevalid==1)
	{	firstname = reader.readLine();
		if(validator.validateString(firstname))//validateString function validates the input data : checks if data contains only string
		{
			firstnamevalid=0;
		}
		else{
			System.out.println("Please Enter Valid Data : Value Should Contain Only Characters");
		}		
	
	}
	
	//Read Lastname and validate : Reader until user enters valid Lastname
	System.out.println("Enter Last Name");
	int lastnamevalid=1;
	String lastname="";
	while(lastnamevalid==1)
	{	lastname = reader.readLine();
		if(validator.validateString(lastname))
		{
			lastnamevalid=0;
		}
	else{
		System.out.println("Please Enter Valid Data : Value Should Contain Only Characters");
		}
	}
	//Read Gender and validate : Reader until user enters valid Gender
	System.out.println("Enter Gender [Male,Female,Others]");
	int gendervalid = 1;
	while(gendervalid==1)
	{
		String gender = reader.readLine();
		if(validator.validateGender(gender))
		{
			gendervalid=0;
		}
		else{
			System.out.println(" Please Enter Valid Gender : Choose from the given List");
			gendervalid=1;
		}
	}

	System.out.println("Enter Department");
	int deptvalid = 1;
	while(deptvalid==1)
	{
		String department = reader.readLine();
		if(validator.validateDept(department))
		{
			deptvalid=0;
		}
		else{
			System.out.println("Please Enter Valid Department");
		}
	}

	System.out.println("Enter Designation");
	int designvalid=1;
	String designation ="";
	while(designvalid==1)
	{	 designation = reader.readLine();
		if(validator.validateDesignation(designation))
		{
			designvalid=0;
		}
		else{
			System.out.println("Enter Valid Designation");
		}
	}
	System.out.println("Enter Manager's Name");
	int managervalid=1;
	while(managervalid==1)
	{	String manager = reader.readLine();
		if(validator.validateString(manager))//validateString function validates the input data : checks if data contains only string
		{
			managervalid=0;
		}
		else{
			System.out.print("Please Enter Valid Data : Value Should Contain Only Characters");
		}		
	
	}

	System.out.println("Enter Organizaton Name");
	int organizationvalid=1;
	while(organizationvalid==1)
	{	String organization = reader.readLine();
		if(validator.validateOrg(organization))//validateString function validates the input data : checks if data contains only string
		{
			organizationvalid=0;
		}
		else{
			System.out.println("Please Enter Valid Organizaton");
		}		
	
	}

	System.out.println("Enter Country [INDIA,US,UK]");
	int countryvalid=1;
	while(countryvalid==1)
	{	String country = reader.readLine();
		if(validator.validateCountry(country))//validateString function validates the input data : checks if data contains only string
		{
			countryvalid=0;
		}
		else{
			System.out.print("Please Enter Valid Country");
		}		
	
	}

	System.out.println("Enter Aadhar Number");
	int aadharvalid=1;
	String aadharnumber="" ;
	while(aadharvalid==1)
	{
		aadharnumber = reader.readLine();
		if(validator.validateAadhar(aadharnumber))
		{
			aadharvalid=0;
		}
		else{
			System.out.println("Enter Valid Aadhar Number");
		}
	}

	//Fields Explicitly Generated
	LocalDate today = LocalDate.now();//Todays Date
	String dateofjoining = today.toString();//Store it in String Date
	String dateoftermination = null;
	int salary = sal.calculate(designation);//Salary Of the Employee based on designation

	// Date of joining, Salary, Username, email, status (As Active) , Date of terminations as null, validation of department and designation, password  -- Auto generated.

	String username = firstname+"."+lastname;
	String email = username+"@enhisecure.com";
	String status = "Active";
	String password = firstname.substring(0,4).concat(aadharnumber);// Password is generated based on First and Aadhar Number Thus remains unique
	String encryptedpassword = encryptor.encrypt(password);
	System.out.println(dateofjoining);
	System.out.println(salary+"");
	System.out.println(username);
	System.out.println(email);
	System.out.println(password);
	System.out.println(encryptedpassword);
	System.out.println(salary);

}
catch(Exception e)
{
	System.out.println(e);
}

}}