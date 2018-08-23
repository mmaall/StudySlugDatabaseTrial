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
    	catch(SQLException e){
    		e.printStackTrace();
    	}
	}

	public static UniqueIDGenerator getInstance(){
		if(uniqueIDInstance == null){
			uniqueIDInstance = new UniqueIDGenerator();
		}
	}

	public int getUniqueID(){
		if(nextSequenceValueStatement == null){
			String nextSeqeunceString = "SELECT nextval(unique_id)";
		}

	}

}