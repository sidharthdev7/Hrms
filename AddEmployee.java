package enh.utilities;
import enh.utilities.HrUseCases;
import enh.userutil.Validator;
import enh.userutil.Encrypter;
import enh.userutil.Salary;
import enh.userutil.EmployeeAdder;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.time.LocalDate;
import enh.userutil.DropDown;


public class AddEmployee implements Executor{

EmployeeAdder adder = new EmployeeAdder();
DropDown drop = new DropDown();

public void execute()
	{
		System.out.println("Enter Employee Details");
		Validator validator = new Validator();
		Salary sal = new Salary();
		Encrypter encryptor = new Encrypter();
		HrUseCases usecases = new HrUseCases();
try{		
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));	
	
	//Read FirstName and validate : Reader until user enters valid Firstname
	int firstnamevalid=1;
	System.out.println("Enter Employee First Name");
	String firstname="";
	while(firstnamevalid==1)
	{	firstname = reader.readLine();
		if(validator.validateString(firstname))//validateString function validates the input data : checks if data contains only string
		{
			firstnamevalid=0;
		}
		else{
			System.out.println("Please Enter Valid Data : Value Should Contain Characters (ATLEAST 2)");
		}		
	
	}
	
	//Read Lastname and validate : Reader until user enters valid Lastname
	System.out.println("Enter Employee Last Name");
	int lastnamevalid=1;
	String lastname="";
	while(lastnamevalid==1)
	{	lastname = reader.readLine();
		if(validator.validateString(lastname))
		{
			lastnamevalid=0;
		}
	else{
		System.out.println("Please Enter Valid Data : Value Should Contain Only Characters (ATLEAST 2)");
		}
	}

	System.out.println("Enter Date Of Birth of Employee in YYYY-MM-DD Format");
	int dobvalid=1;
	String dob="";	
	while(dobvalid==1)
	{
		 dob = reader.readLine();

		if(validator.validateDOB(dob))
		{
			dobvalid=0;
		}
		else{
			System.out.println("Enter Valid Date Of Birth");
			dobvalid=1;
		}
	}

	//Read Gender and validate : Reader until user enters valid Gender
	System.out.println("Enter Gender [Male,Female,Others]");
	int gendervalid = 1;
	String gender = "";
	while(gendervalid==1)
	{
		 gender = reader.readLine();
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
	String department="";
	String validept=null;
	while(deptvalid==1)
	{
		 department = reader.readLine();
		if(validator.validateDept(department))
		{
			deptvalid=0;
			validept=department;
		}
		else{
			System.out.println("Please Enter Valid Department");
			drop.listDept();

		}
	}

	System.out.println("Enter Designation");
	drop.listDes(department);
	int designvalid=1;
	String designation ="";
	while(designvalid==1)
	{	 designation = reader.readLine();
		if(validator.validateDesignation(validept, designation))
		{
			designvalid=0;

		}
		else{
			System.out.println("Enter Valid Designation");
			//drop.listDes(department);
		}
	}
	System.out.println("Enter Manager's Name");
	drop.listManagers(validept);
	int managervalid=1;
	String manager = "";
	while(managervalid==1)
	{	manager = reader.readLine();
		if(validator.validateManager(validept,manager))//validateString function validates the input data : checks if data contains only string
		{
			managervalid=0;
		}
		else{
			System.out.println("Please Enter Valid Manager ");
			
		}		
	
	}

	System.out.println("Enter Organizaton Name");
	drop.listOrg();
	int organizationvalid=1;
	String organization="";
	while(organizationvalid==1)
	{	 organization = reader.readLine();
		if(validator.validateOrg(organization))//validateString function validates the input data : checks if data contains only string
		{
			organizationvalid=0;
		}
		else{
			System.out.println("Please Enter Valid Organizaton");
			
		}		
	
	}

	System.out.println("Enter Country [INDIA,US]");
	int countryvalid=1;
	String country="";
	while(countryvalid==1)
	{	country = reader.readLine();
		if(validator.validateCountry(country))//validateString function validates the input data : checks if data contains only string
		{
			countryvalid=0;
		}
		else{
			System.out.println("Please Enter Valid Country");
		}		
	
	}

	System.out.println("Enter State ");
	drop.listStates(country);
	int statevalid=1;
	String state="";
	while(statevalid==1)
	{	state = reader.readLine();
		if(validator.validateState(country,state))//validateString function validates the input data : checks if data contains only string
		{
			statevalid=0;
		}
		else{
			System.out.println("Please Enter Valid State");
		}		
	
	}
	System.out.println("Enter City ");
	drop.listCities(country,state);
	int cityvalid=1;
	String city="";
	while(cityvalid==1)
	{	city = reader.readLine();
		if(validator.validateCity(country,state,city))//validateString function validates the input data : checks if data contains only string
		{
			cityvalid=0;
		}
		else{
			System.out.println("Please Enter Valid City");
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
			System.out.println("Please Enter Valid Aadhar Number:");
		}
	}

	//Fields Explicitly Generated
	LocalDate today = LocalDate.now();//Todays Date
	String dateofjoining = today.toString();//Store it in String Date
	String dateoftermination = null;
	//int salaryamount = //Salary Of the Employee based on designation
	String salary = sal.calculate(validept.toUpperCase(),designation.toUpperCase());
	String username = (firstname+"."+lastname).toLowerCase();
	String email = username+"@enhisecure.com";
	String status = "Active";
	String password = username.substring(0,4).concat(aadharnumber);// Password is generated based on First and Aadhar Number Thus remains unique
	String encryptedpassword = encryptor.encryptPass(password);
	String aadharencrypted = encryptor.encrypt(aadharnumber);
	adder.add(firstname,lastname,dob,gender,department,designation,manager,organization,country,state,aadharencrypted,dateofjoining,dateoftermination,salary,username,email,status,encryptedpassword,password);				

	usecases.executeHrOps();
	
	
}
catch(Exception e)
{
	System.out.println(e);
}

	}
}