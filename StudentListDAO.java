import java.util.ArrayList;
import java.sql.*;


public class StudentListDAO{
	private ArrayList<Student> studentList;
	private Connection databaseConnection;

	private PreparedStatement findStudentByFirstName;
	private PreparedStatement findStudentByLastName;


	StudentListDAO(){
		studentList= new ArrayList<Student>();
		databaseConnection= null;
		String firstNameSearch= "SELECT " +
									"student_id, first_name, " +
									"last_name, email_address " +
								"FROM students "+
								"WHERE first_name LIKE '?%'";


		String lastNameSearch = "SELECT " +
									"student_id, first_name, " +
									"last_name, email_address " +
								"FROM students "+
								"WHERE last_name LIKE '?%'";


	}

	StudentListDAO(Connection connection){
		studentList= new ArrayList<Student>();
		databaseConnection= connection;
	}


	public ArrayList<Student> getStudentList(){
		return studentList;
	}



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
                studentList.add(newStudent);
			}

		}

		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}
	}

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
                studentList.add(newStudent);
			}

		}

		catch(SQLException e){
			e.printStackTrace();
			System.exit(-1);
		}

	}




}