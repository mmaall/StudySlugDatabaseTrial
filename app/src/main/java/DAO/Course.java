package DAO;

public class Course{

	private int courseID;

	private String department;

	private String courseNumber;

	private int section;


	public Course(){
		this.clear();
	}

	public int getCourseID(){
		return courseID;
	}

	public String getDepartment(){
		return department;
	}

	public String getCourseNumber(){
		return courseNumber;
	}

	public int getSection(){
		return section;
	}


	public void setCourseID(int courseID){
		this.courseID= courseID;
	}

	public void setDepartment(String department){
		if(department.length() > 4){
			//TODO throw proper exception when department length is too long
		}

		this.department= department;
	}

	public void setCourseNumber(String courseNumber){
		this.courseNumber = courseNumber;

	}

	public void setSection(int section ){
		if(section < 1){
			//TODO throw proper exception when invalid section
		}
		this.section= section;
	}

	public void clear(){
		courseID = -1; 
		department= null;
		courseNumber= null;
		section= -1;
	}

}