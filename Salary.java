package enh.userutil;
import java.util.regex.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;


public class Salary{

public static String calculate(String department, String designation) {
        String salary = null;
        try {
            File inputFile = new File("xmlfiles/salarystructure.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList departmentList = doc.getElementsByTagName(department.toUpperCase());
            if (departmentList.getLength() > 0) {
                Element departmentElement = (Element) departmentList.item(0);
                NodeList designationList = departmentElement.getElementsByTagName(designation.toUpperCase());
                if (designationList.getLength() > 0) {
                    Element designationElement = (Element) designationList.item(0);
                     salary= designationElement.getTextContent();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salary;
    }}

