import java.sql.*;

public abstract class SingletonDAO{

	public boolean isNew;

	public Connection databaseConnection;

	public PreparedStatement updateStatement;

	public PreparedStatement insertStatement;

	public PreparedStatement findStatement;



	/**
	 *Default constructor for SingletonDAO. Do not use
	**/
	public SingletonDAO(){
		isNew= true;
		databaseConnection= null;
	}

	/**
	 *Constructs a Singletonr DAO
	 *@param databaseConnection An active PostgreSQL database connection.
	**/
	public SingletonDAO(Connection databaseConnection){
		isNew= true;
		this.databaseConnection= databaseConnection;
	}

	public abstract void find(int uniqueID);

	public abstract void save();

	public abstract void reset();
	



}