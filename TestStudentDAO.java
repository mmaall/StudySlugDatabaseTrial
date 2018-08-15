

import java.sql.*;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;



public class TestStudentDAO{

    public static int testStudentID= 501;
    public static String testFirstName= "Michael";
    public static String testLastName= "Lanthier";
    public static String testEmail= "malanthi@ucsc.edu";

    public static void main(String[] args){


        Connection connection = null;
        


        try{
            Class.forName("org.postgresql.Driver");    

            connection = DriverManager.getConnection("jdbc:postgresql://192.168.2.205:5433/StudySlug", "axlanthier", "poseydog");
            
            if ( connection != null){
                System.out.println("Connection to database succesful!!!!");
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
        System.out.println("Test Find Student\n");
        try{
            testStudent.reset();
            testStudent.find(studentID);
            if(testStudent.getFirstName() == testFirstName &&
                testStudent.getLastName()==testLastName &&
                testStudent.getEmailAddress()==testEmail){

                System.out.println("Find Statement succesful!!");

            }
            else{
                System.out.println("Find Statement unsuccesful");


            }
        }
        catch(Exception e){

        }
    }

    public static void testInsert(StudentDAO testStudent){
        System.out.println("Test Insert Student\n");
        try{
            testStudent.reset();
            testStudent.setFirstName("Foo");
            testStudent.setLastName("Bar");
            testStudent.setEmailAddress("foobar@gmail.com");
            testStudent.save();

        }
        catch (Exception e){
        
        }
    }
    
    public void testCreate(){

    }

}
