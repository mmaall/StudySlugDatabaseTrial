

import java.sql.*;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;

public class TestStudentDAO{

    public static void main(String[] args){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");    

            connection = DriverManager.getConnection("jdbc:postgresql://192.168.2.205:5433/StudySlug", "axlanthier", "poseydog");
            
            if ( connection != null){
                System.out.println("Connection to database succesful!!!!");
            }

            StudentDAO student1 = new StudentDAO(connection);
            testFind(student1, 1);
            testUpdate(student1, 1);
        
        }
        catch(Exception e){
            System.out.println("Error while connection to database: " +e);
            e.printStackTrace();
        }
    }
    public static void testFind(StudentDAO testStudent, int studentID){
        System.out.println("Test Find Student\n\n"+
                            "Print student with ID: "+ studentID);
        try{
            testStudent.reset();
            testStudent.find(studentID);
            System.out.println(testStudent);
        }
        catch(Exception e){

        }
    }

    public static void testUpdate(StudentDAO testStudent, int studentID){
        System.out.println("Test Update Student\n\n" +
                            "Original Student Data");
        try{
            testStudent.reset();
            testStudent.find(studentID);
            System.out.println(testStudent);
            testStudent.setFirstName("Bob");
            testStudent.setLastName("LawBlah");
            testStudent.save();
            testStudent.reset();
            System.out.println("After Update");
            testStudent.find(studentID);
            System.out.println(testStudent);
        }
        catch (Exception e){
        
        }
    }
    
    public void testCreate(){

    }

}
