package DAO;




/**
 *Represents a basic student
**/
public class Student implements Cloneable{
    

    private int studentID;
    
    private String firstName;

    private String lastName;

    private String emailAddress;


    /**
     *Constructs a new student with empty fields
    **/
    public Student(){
        studentID=  0;
        firstName=null;
        lastName=null;
        emailAddress=null;

    }

    /**
     *Returns the unique student ID of the current student.
     *@return studentID of current student
    **/
    public int getStudentID(){
        return studentID;
    }

    /**
     *Returns the first name of the current student.
     *@return first name of current student
    **/
    public String getFirstName(){
        return firstName;
    }

    /**
     *Returns the last name of the current student.
     *@return last name of current student
    **/
    public String getLastName(){
        return lastName;
    }

    /**
     *Returns the first and last name of the current student.
     *@return first and last name of current student
    **/
    public String getFullName(){
        String fullName= firstName+" "+lastName;
        return fullName;
    }


    /**
     *Returns the email address of the current student.
     *@return email address of the current student
    **/
    public String getEmailAddress(){
        return emailAddress;
    }


    /**
     *Sets the unique student ID of the current student.
     *@param studentID a unique identifier for each student
    **/
    public void setStudentID(int studentID){
        this.studentID= studentID;
    }

    /**
     *Sets the first name of thec urrent student.
     *@param firstName the first name of each student
    **/
    public void setFirstName(String firstName){
        this.firstName= firstName;
    }

    /**
     *Sets the last name of the current student.
     *@param lastName the last name of each student
    **/
    public void setLastName(String lastName){
        this.lastName= lastName;
    }

    /**
     *Sets the email address of the current student.
     *@param emailAddress the email address of each student
    **/
    public void setEmailAddress(String emailAddress){
        this.emailAddress= emailAddress;
    }

    /**
     *Checks whether the current student is missing any values. and returns
     * true if one of those values is missing.
     *@return True if the student is missing a first name, last name or
     *          email address. Otherwise returns false.
    **/
    public boolean missingValues(){
        if(firstName == null || lastName == null || emailAddress == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     *Resets the current student. Sets student ID to -1 and first name, last 
     *name and email address to null. 
    **/
    public void clear(){
        studentID= 0;
        firstName= null;
        lastName= null;
        emailAddress= null;

    }

    public Object clone() throws CloneNotSupportedException{  
        return super.clone();  
    }


    public boolean equals(Student s1){
        if(this.firstName.equals(s1.getFirstName())&&
            this.lastName.equals(s1.getLastName())&&
            this.emailAddress.equals(s1.getEmailAddress())&&
            this.studentID==s1.getStudentID()){

            return true;
        }
        else{
            return false;
        }
    }

    /**
     *Returns formatted values of the current Student
     *@return StudentID, first name, last name and email address formatted
     * string.
    **/
    public String toString(){
        String output = 
            studentID + "\n" +
            firstName + " " + lastName + "\n" +
            emailAddress;
        return output; 
    }


}
