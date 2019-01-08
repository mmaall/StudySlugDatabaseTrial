package dao;

import java.sql.*;


public abstract class SingletonDAO{

	protected boolean isNew;

	protected Connection databaseConnection;

	protected PreparedStatement updateStatement;

	protected PreparedStatement insertStatement;

	protected PreparedStatement findStatement;

	protected PreparedStatement deleteStatement;

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

	public abstract boolean find(int uniqueID);

	public abstract void save();

	public abstract void reset();
	



}
