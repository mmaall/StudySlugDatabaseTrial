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
        
        }
        catch(Exception e){
            System.out.println("Error while connection to database: " +e);
            e.printStackTrace();
        }
    }

}
