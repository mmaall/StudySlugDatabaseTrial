import java.util.ArrayList;
import java.sql.*;


public class StudentListDAO{


	/**
	 *Arraylist of Students. Reset and filled whenever a query is made.
	**/
	private ArrayList<Student> studentsList;
	
	/**
	 *Holds the database connection to a PostgreSQL database.
	**/
	private Connection databaseConnection;

	/**
	 *Holds a prepared statement to find a list of students with a certain
	 *first name. The result will only contain that exact first name 
	 *
	**/
	private PreparedStatement findStudentByFirstNameExact;
	
	/**
	 *Holds a prepared statement to find a list of students containing a
	 *certain string in their fisrt name. All results will have the exact 
	 *string somewhere within the first name.
	**/
	private PreparedStatement findStudentByFirstNameContains;


	/**
	 *Holds a prepared statement to find a list of students with a certain
	 *last name. Results will contain only that exact first name case insensitive.
	**/
	private PreparedStatement findStudentByLastNameExact;

	/**
	 *Holds a prepared statement to find a list of students that contain a certain
	 *string within thier last name. All results with have the exact input string 
	 *somewhere within the last name.
	**/
	private PreparedStatement findStudentByLastNameContains;

	/**
	 * Constructs a StudentListDAO with a null database connection.
	 * This is not very useful is it. 
	**/
	StudentListDAO(){
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
									"WHERE last_name LIKE '?%'";

		String firstNameSearchContains= "SELECT" +
											"student_id, first_name, "
											"last_name, email_address "+
										"FROM students "+
										"WHERE first_name LIKE '%?%'";
		String lastNameSearchContains= "SELECT" +
											"student_id, first_name, "
											"last_name, email_address "+
										"FROM students "+
										"WHERE last_name LIKE '%?%'";

		try{
			findStudentByFirstNameExact = databaseConnection.PreparedStatement(firstNameSearchExact);
			findStudentByLastNameExact = databaseConnection.PreparedStatement(lastNameSearchExact);
			findStudentByFirstNameContains = databaseConnection.PreparedStatement(firstNameSearchContains);
			findStudentByLastNameContains = databaseConnection.PreparedStatement(lastNameSearchContains);

		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}

	}

	/**
	 *
	**/
	StudentListDAO(Connection connection){
		studentsList= new ArrayList<Student>();
		databaseConnection= connection;
	}

	/**
	 *
	**/
	public ArrayList<Student> getStudentList(){
		return studentsList;
	}


	/**
	 *
	**/
	public void findStudentByFirstName(String firstName){
		if(findStudentByFirstName == null){
			System.out.println("Uh oh, findStudentByFirstName is null!!!!!");
			return;
		}


		try{
			findStudentByFirstName.setString(1, lastName);
			ResultSet rset = findStudentByFirstName.executeQuery();
			
			while(rset.next()){
				Student newStudent= Student();
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
	}


	/**
	 *
	**/
	public void findStudentByLastName(String lastName){
		if(findStudentByLastName == null){
			System.out.println("Uh oh, findStudentByLastName is null!!!!!");
			return;
		}


		try{
			findStudentByLastName.setString(1, lastName);
			ResultSet rset = findStudentByLastName.executeQuery();
			
			while(rset.next()){
				Student newStudent= Student();
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

	}
}


