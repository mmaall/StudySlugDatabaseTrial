package DAO;


import java.util.ArrayList;
import java.sql.*;


public class CourseListDAO{

	private ArrayList<Course> courses; 
	
	private Connection databaseConnection;

	private PreparedStatement findCoursesByDepartment;

	private PreparedStatement findCoursesByDepartmentAndNumber;

	private PreparedStatement findCoursesByNumber;


	public CourseListDAO(){
		courses = new ArrayList<Course>();
		databaseConnection=null;

	}

	/**
	 *Creates a CourseListDAO with a new database connection.
	 *@param databaseConnection Active PostgreSQL database connection
	**/
	public CourseListDAO(Connection databaseConnection){
		this.databaseConnection=databaseConnection;
		courses= new ArrayList<Course>();
	}

	public ArrayList<Course> getCourses(){
		return courses;
	}

	public ArrayList<Course> findCoursesByDepartment(String department){
		if(department.length()> 4){
			//TODO Invalid department length, throw exception?
		}
		if(findCoursesByDepartment == null){
			String statement="SELECT course_id, department, "+
							"course_number, section " +
							"FROM Courses "+
							"WHERE department LIKE '?'";
			try{
				findCoursesByDepartment= databaseConnection.prepareStatement(statement);
			}
			catch(SQLException e){
				e.printStackTrace();
				//TODO throw proper exception
			}
		}
		try{
			findCoursesByDepartment.setString(1, department);
			ResultSet rset = findCoursesByDepartment.executeQuery();
			resultToCourseList(rset);
		}
		catch(SQLException e){
			e.printStackTrace();
			//TODO throw proper exception
		}

		return courses;

	}

	public ArrayList<Course> findCoursesByNumber(String courseNumber){
		if(findCoursesByNumber == null){
			String statement="SELECT course_id, department, "+
							"course_number, section " +
							"FROM Courses "+
							"WHERE course_number LIKE '?'";
			try{
				findCoursesByNumber = databaseConnection.prepareStatement(statement);
			}
			catch(SQLException e){
				e.printStackTrace();
				//TODO throw proper exception
			}
		}
		try{
			findCoursesByNumber.setString(1,courseNumber);
			ResultSet rset = findCoursesByNumber.executeQuery();
			resultToCourseList(rset);
		}
		catch(SQLException e){
			e.printStackTrace();
			//TODO throw proper exception
		}



		return courses;
	}

	public ArrayList<Course> findCoursesByDepartmentAndNumber(String department, String courseNum){

		if( department.length()> 4 || courseNum.length() >4 ){
			courses.clear();
			return courses;
		}
		if (findCoursesByDepartmentAndNumber==null){
			String statement="SELECT course_id, department, "+
							"course_number, section " +
							"FROM Courses "+
							"WHERE department LIKE '?' "+
							"AND course_number LIKE '?'";
			try{
				findCoursesByDepartmentAndNumber = databaseConnection.prepareStatement(statement);
			}
			catch(SQLException e){
				e.printStackTrace();
				//TODO Throw proper exception
			}
		}

		try{
			findCoursesByDepartmentAndNumber.setString(1,department);
			findCoursesByDepartmentAndNumber.setString(2, courseNum);
			ResultSet rset = findCoursesByDepartmentAndNumber.executeQuery();
			resultToCourseList(rset);
			

		}
		catch(SQLException e){
			e.printStackTrace();
			//TODO Throw proper exception
		}

		return courses;
	}

	private void resultToCourseList(ResultSet result){
		try{
			courses.clear();

			while(result.next()){
				Course newCourse = new Course();
				newCourse.setCourseID(result.getInt(1));
				newCourse.setDepartment(result.getString(2));
				newCourse.setCourseNumber(result.getString(3));
				newCourse.setSection(result.getInt(4));	
				courses.add(newCourse);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}




}