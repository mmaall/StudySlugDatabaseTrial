package DAO;

import java.sql.*;
import java.util.*;
import java.io.*;

public class UniqueIDGenerator{

	private static UniqueIDGenerator uniqueIDInstance;

	private Connection databaseConnection;

	private PreparedStatement nextSequenceValueStatement;


	private UniqueIDGenerator(){
	    try{
        	
        	FileReader reader;
        	Properties p = new Properties();
        	String studySlugDir= System.getenv("STUDYSLUGDIR");

		reader= new FileReader(studySlugDir+"/app/config/db.properties");
        	p.load(reader);
                String url="jdbc:postgresql://"+p.getProperty("ip")
                +":"+p.getProperty("port")+"/studyslug";
                String user= p.getProperty("user");
                databaseConnection =
                    DriverManager.getConnection(url,user,p.getProperty("password"));


    	    }
    	    catch(Exception e){
                System.out.println("Unable to connect to database for " +
                    "unique ID generator");
        	e.printStackTrace();//TODO: Fix to throw proper exception
    	    }
	}

	public static UniqueIDGenerator getInstance(){
		if(uniqueIDInstance == null){
			uniqueIDInstance = new UniqueIDGenerator();
		}
		return uniqueIDInstance;
	}

	public int getUniqueID(){
		int id= -1;
		if(nextSequenceValueStatement == null){
			String nextSeqeunceString = "SELECT nextval('unique_id')";
			try{
				nextSequenceValueStatement= 
					databaseConnection.prepareStatement(nextSeqeunceString);
			}
			catch(SQLException e){
				e.printStackTrace();//TODO: Fix to throw proper exception
			}
		}
		try{
		    ResultSet rset= 
                    nextSequenceValueStatement.executeQuery();
	            rset.next();
		    id=rset.getInt(1);
		}
		catch(SQLException e){
                     System.out.println("Unique ID Generator Result Set Failure");
			e.printStackTrace();//TODO: Fix to throw proper exception
		}
		return id;
	}

}
