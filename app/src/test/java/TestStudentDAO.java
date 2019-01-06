import DAO.*;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.URL;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;



public class TestStudentDAO{

    public static void main(String[] args){


        Connection connection = null;

        //Load in properties file
        FileReader reader;
        Properties p = new Properties();
        String studySlugDir= System.getenv("STUDYSLUGDIR");

        try{
        	reader= new FileReader(studySlugDir+"/app/config/db.properties");
        	p.load(reader);
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        /*
        System.out.println("IP: "+p.getProperty("ip"));
        System.out.println("Port: "+p.getProperty("port"));
        System.out.println("User: "+p.getProperty("user"));
		*/

        //Prepares url and user
        String url="jdbc:postgresql://"+p.getProperty("ip")
            +":"+p.getProperty("port")+"/studyslug";
        String user= p.getProperty("user");

        try{
            Class.forName("org.postgresql.Driver");    
            connection = 
                DriverManager.getConnection(url,user,p.getProperty("password"));
            
            if ( connection != null){
                System.out.println("Connection to database succesful");
            }

            StudentDAO student1 = new StudentDAO(connection);
            testFind(student1, testStudentID);
            testInsert(student1);
        
        }
        catch(Exception e){
            System.out.println("Error while connection to database: " +e);
            e.printStackTrace();
        }
    }


    public static void testFind(StudentDAO testStudent, int studentID){
        System.out.println("Test Find Student");
        try{
            testStudent.reset()
;            testStudent.find(studentID);
            if(testStudent.getFirstName().equals(testFirstName) &&
                testStudent.getLastName().equals(testLastName) &&
                testStudent.getEmailAddress().equals(testEmail)){

                System.out.println("Find Statement succesful!!\n");

            }
            else{
                System.out.println("Find Statement unsuccesful");
                System.out.println("Test Student Data");
                System.out.println(testStudentID+": "+testFirstName+" "+testLastName);
                System.out.println("\t"+testEmail);
                System.out.println("Student Retrieved from Database");
                System.out.println(testStudent);



            }
        }
        catch(Exception e){

        }
    }

    public static void testInsert(StudentDAO testStudent){
        System.out.println("Test Insert Student");
        try{
            String insertFirstName = "Foo";
            String insertLastName = "Bar";
            String insertEmailAddress= "foobar@gmail.com";


            testStudent.reset();
            testStudent.setFirstName(insertFirstName);
            testStudent.setLastName(insertLastName);
            testStudent.setEmailAddress(insertEmailAddress);
            testStudent.save();
            System.out.println(testStudent);
            testStudent.find(testStudent.getStudentID());

            if(testStudent.getFirstName().equals(insertFirstName) &&
                testStudent.getLastName().equals(insertLastName) &&
                testStudent.getEmailAddress().equals(insertEmailAddress)){

                System.out.println("Insert Statement succesful!!\n");

            }
            else{
                System.out.println("Insert Statement unsuccesful");
                System.out.println("Test Student Data to Be Inserted");
                System.out.println(testStudentID+": "+  testFirstName+" "+testLastName);
                System.out.println("\t"+testEmail);
                System.out.println("Student Retrieved from Database");
                System.out.println(testStudent);



            }

        }

        catch (Exception e){

        }
    }
    
    public void testCreate(){

    }

}
