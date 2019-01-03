import DAO.*;

import java.sql.*;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;



public class TestStudentDAO{

    public static int testStudentID= 501;
    public static String testFirstName= "Michael";
    public static String testLastName= "Lanthier";
    public static String testEmail= "malanthi@ucsc.edu";

    public static void main(String[] args){

        if(args.length != 3){
            System.out.println("Usage: TestStudentDAO <postgresIP>:<port> <user> <password>");
        }

        Connection connection = null;
        String url="jdbc:postgresql://"+args[0]+"/studyslug";
        String user= args[1];
        String password= args[2];


        try{
            Class.forName("org.postgresql.Driver");    


            connection = 
                DriverManager.getConnection(url,user,password);
            
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
            testStudent.reset();
            testStudent.find(studentID);
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
