package DAO;

import java.sql.*;

public class UniqueIDGenerator{

	private static UniqueIDGenerator uniqueIDInstance;

	private Connection databaseConnection;

	private PreparedStatement nextSequenceValueStatement;


	private UniqueIDGenerator(){
		try{
			Class.forName("org.postgresql.Driver");    
        	databaseConnection = DriverManager.getConnection(
        						"jdbc:postgresql://192.168.2.205:5433/StudySlug", 
        						"axlanthier", "poseydog");

    	}
    	catch(Exception e){
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
			String nextSeqeunceString = "SELECT nextval(unique_id)";
			try{
				nextSequenceValueStatement= 
					databaseConnection.prepareStatement(nextSeqeunceString);
			}
			catch(SQLException e){
				e.printStackTrace();//TODO: Fix to throw proper exception
			}
		}
		try{
			ResultSet rset= nextSequenceValueStatement.executeQuery();
			rset.next();
			id=rset.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();//TODO: Fix to throw proper exception
		}
		return id;
	}

}