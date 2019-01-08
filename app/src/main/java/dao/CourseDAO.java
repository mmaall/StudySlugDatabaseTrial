package dao;

import java.sql.*;


public class CourseDAO extends SingletonDAO{

	private Course course;


	public CourseDAO(){
		super(null);
		this.course= new Course();
	}


	/**
	 *Creates a new CourseDAO with an active database connection
	 *@param databaseConnection An active PostgreSQL database connection
	**/
	public CourseDAO(Connection databaseConnection){
		super(databaseConnection);
		this.course= new Course();
	}


	public int getCourseID(){
		return course.getCourseID();
	}

	public String getDepartment(){
		return course.getDepartment();
	}

	public String getCourseNumber(){
		return course.getCourseNumber();
	}

	public int getSection(){
		return course.getSection();
	}

	public void setDepartment(String department){
		course.setDepartment(department);
	}

	public void setCourseNumber(String courseNumber){
		course.setCourseNumber(courseNumber);
	}



	/**
	 *
	**/
	public boolean find(int courseID){
		if(findStatement == null){
			String findByCourseID = "SELECT course_id, department, "+
									"course_number, section " +
									"FROM Courses " +
									"WHERE course_id =?";
			try{
				findStatement = 
					databaseConnection.prepareStatement(findByCourseID);
			}
			catch(SQLException e){
				e.printStackTrace();
				//TODO throw proper exception
			}
		}

		try{
			findStatement.setInt(1,courseID);
			ResultSet rset = findStatement.executeQuery();
			if(rset.next()){
				course.setCourseID(rset.getInt(1));
				course.setCourseNumber(rset.getString(2));
				course.setDepartment(rset.getString(3));
				course.setSection(rset.getInt(4));
		        return true;	
                    }
		}
		catch(SQLException e){

		}
            return false;
	}


	/**
	 *
	**/
	public void save(){

	}

	/**
	 *
	**/
	public void reset(){
		course.clear();
	}

}
