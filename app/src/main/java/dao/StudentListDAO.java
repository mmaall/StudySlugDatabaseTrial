package DAO;


import java.util.ArrayList;
import java.sql.*;


public class StudentListDAO{



	private ArrayList<Student> studentsList;
	

	private Connection databaseConnection;
	

	private PreparedStatement findStudentByFirstNameExact;
	

	private PreparedStatement findStudentByFirstNameContains;


	private PreparedStatement findStudentByLastNameExact;


	private PreparedStatement findStudentByLastNameContains;


	public StudentListDAO(){
		studentsList= new ArrayList<Student>();
		databaseConnection= null;
		String firstNameSearchExact= "SELECT " +
									"student_id, first_name, " +
									"last_name, email_address " +
								"FROM students "+
								"WHERE first_name LIKE '?'";


		String lastNameSearchExact ="SELECT " +
										"student_id, first_name, " +
										"last_name, email_address " +
									"FROM students "+
									"WHERE last_name LIKE '? 	'";

		String firstNameSearchContains= "SELECT" +
											"student_id, first_name, "+
											"last_name, email_address "+
										"FROM students "+
										"WHERE first_name LIKE '%?%'";
		String lastNameSearchContains= "SELECT" +
											"student_id, first_name, "+
											"last_name, email_address "+
										"FROM students "+
										"WHERE last_name LIKE '%?%'";

		try{
			findStudentByFirstNameExact = databaseConnection.prepareStatement(firstNameSearchExact);
			findStudentByLastNameExact = databaseConnection.prepareStatement(lastNameSearchExact);
			findStudentByFirstNameContains = databaseConnection.prepareStatement(firstNameSearchContains);
			findStudentByLastNameContains = databaseConnection.prepareStatement(lastNameSearchContains);

		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 *Creates a new StudentListDAO using an active database connection.
	 *@param dbConnection Active connection to the PostgreSQL database.
	**/
	public StudentListDAO(Connection dbConnection){
		studentsList= new ArrayList<Student>();
		databaseConnection= dbConnection;

		String firstNameSearchExact= "SELECT " +
									"student_id, first_name, " +
									"last_name, email_address " +
								"FROM students "+
								"WHERE first_name LIKE '?'";


		String lastNameSearchExact ="SELECT " +
										"student_id, first_name, " +
										"last_name, email_address " +
									"FROM students "+
									"WHERE last_name LIKE '?%'";

		String firstNameSearchContains= "SELECT" +
											"student_id, first_name, "+
											"last_name, email_address "+
										"FROM students "+
										"WHERE first_name LIKE '%?%'";
		String lastNameSearchContains= "SELECT" +
											"student_id, first_name, "+
											"last_name, email_address "+
										"FROM students "+
										"WHERE last_name LIKE '%?%'";

		try{
			findStudentByFirstNameExact = databaseConnection.prepareStatement(firstNameSearchExact);
			findStudentByLastNameExact = databaseConnection.prepareStatement(lastNameSearchExact);
			findStudentByFirstNameContains = databaseConnection.prepareStatement(firstNameSearchContains);
			findStudentByLastNameContains = databaseConnection.prepareStatement(lastNameSearchContains);

		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 *Gets a list of students based off of the last query. If empty, no students
	 *match the criteria of the last query.
	 *@return ArrayList of Students
	**/
	public ArrayList<Student> getStudentList(){
		return studentsList;
	}


	/**
	 *Finds all students by a given first name.
	 *@param firstName The exact first name being searched for
	 *@return List of students who match the first name
	**/
	public ArrayList<Student> findStudentByFirstNameExact(String firstName){
		if(findStudentByFirstNameContains == null){
			System.out.println("Uh oh, findStudentByFirstName is null!!!!!");
			return null;
		}


		try{
			findStudentByFirstNameContains.setString(1, firstName);
			ResultSet rset = findStudentByFirstNameExact.executeQuery();
			
			while(rset.next()){
				Student newStudent= new Student();
				newStudent.setStudentID(rset.getInt(1));
                newStudent.setFirstName(rset.getString(2));
                newStudent.setLastName (rset.getString(3));
                newStudent.setEmailAddress(rset.getString(4));    
                studentsList.add(newStudent);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}

		return studentsList;

	}


	/**
	 *Finds all students by a given last name.
	 *@param lastName The exact last name being searched for
	 *@return List of students who match the last name
	**/
	public ArrayList<Student> findStudentByLastNameExact(String lastName){
		if(findStudentByLastNameExact == null){
			System.out.println("Uh oh, findStudentByLastNameExact is null!!!!!");
			return null;
		}


		try{

			studentsList.clear();
			findStudentByLastNameExact.setString(1, lastName);
			ResultSet rset = findStudentByLastNameExact.executeQuery();
			
			while(rset.next()){
				Student newStudent= new Student();
				newStudent.setStudentID(rset.getInt(1));
                newStudent.setFirstName(rset.getString(2));
                newStudent.setLastName (rset.getString(3));
                newStudent.setEmailAddress(rset.getString(4));    
                studentsList.add(newStudent);
			}

		}

		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}

		return studentsList;

	}

	/**
	 *Finds all students whose first name contains the inputed string.
	 *@param searchString Text being searched for in the first name
	 *@return List of students containing text being searched for.
	**/
	public ArrayList<Student> findStudentByFirstNameContains(String searchString){
		if(findStudentByFirstNameContains== null){
			System.out.println("Uh oh, it looks like findStudentByFirstNameContains is null");
			return null;
		}

		try{
			studentsList.clear();
			findStudentByFirstNameContains.setString(1,searchString);
			ResultSet rset = findStudentByFirstNameContains.executeQuery();
			while(rset.next()){
				Student newStudent = new Student();
				newStudent.setStudentID(rset.getInt(1));
				newStudent.setFirstName(rset.getString(2));
				newStudent.setLastName(rset.getString(3));
				newStudent.setEmailAddress(rset.getString(4));
				studentsList.add(newStudent);
			}
			return studentsList;

		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 *Finds all students whose last name contains the inputed string.
	 *@param searchString Text being searched for in the lsat name
	 *@return List of students containing text being searched for.
	**/
	public ArrayList<Student> findStudentByLastNameContains(String searchString){
		if(findStudentByLastNameContains == null){
			System.out.println("Uh oh, it looks like findStudentByFirstNameContains is null");
			return null;
		}

		try{
			studentsList.clear();
			findStudentByLastNameContains.setString(1, searchString);
			ResultSet rset = findStudentByLastNameContains.executeQuery();

			while(rset.next()){
				Student newStudent = new Student();
				newStudent.setStudentID(rset.getInt(1));
				newStudent.setFirstName(rset.getString(2));
				newStudent.setLastName(rset.getString(3));
				newStudent.setEmailAddress(rset.getString(4));
				studentsList.add(newStudent);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return studentsList;
	}
}


