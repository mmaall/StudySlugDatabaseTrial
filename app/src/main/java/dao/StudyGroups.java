package dao;


import java.util.ArrayList;


public class StudyGroups{

	private int groupID;
	private int associatedClass;
	private ArrayList<Student> students;

	public StudyGroups(){
		groupID = -1;
		associatedClass= -1;
		students = new ArrayList<Student>();
	}




	public String toString(){
		return "";//TODO finish toString
	}

}
