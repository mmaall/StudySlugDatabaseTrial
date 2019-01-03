import DAO.*;

import java.sql.*;

public class TestStudentListDAO{

	public static void main(String[] args){
		Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");    

            connection = DriverManager.getConnection("jdbc:postgresql://18.224.94.214:5432/studyslug",
                                            "ubuntu", "password");
            
            if ( connection != null){
                System.out.println("Connection to database succesful!!!!");
            }

            StudentListDAO studentList = new StudentListDAO(connection);
        
        }
        catch(Exception e){
            System.out.println("Error while connection to database: " +e);
            e.printStackTrace();
        }
	}


}
