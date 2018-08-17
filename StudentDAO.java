import java.sql.*;


/**
 *Data Access Object for a Single Student.
 *Interacts with a Postgresql Database containing Student information
**/
public class StudentDAO{
    

    private Student student;

    private boolean isNew;

    private Connection databaseConnection;

    private PreparedStatement updateStudent;

    private PreparedStatement insertStudent;

    private PreparedStatement findStudent;

    private PreparedStatement nextSequenceValue;

    /**
     *Constructor to create a StudentDAO. Datbaase connection is default to 
     *null. Also sets isNew to true.
    **/
    public StudentDAO(){
        databaseConnection= null;
        student = new Student();
        isNew=true;
    }

    /**
     *Creates a new StudentDAO using an active database connection. 
     *New student is set to true and all prepared statements are initialized. 
     *@param dbConnection Active connection to the PostgreSQL datbaase.
     *
    **/
    public StudentDAO(Connection dbConnection){
        student= new Student();
        isNew=true;
        databaseConnection= dbConnection;
        String updateString="UPDATE students "+
                            "SET first_name = ?, "+
                                "last_name = ?, "+
                                "email_address = ?"+
                            "WHERE student_id = ?";

        String insertString="INSERT INTO students "+
                            "VALUES " +
                                "(?, ?, ?, ?) ";
        String findString = "SELECT "+ 
                            "student_id, first_name, "+ 
                            "last_name, email_address "+
                            "FROM students " +
                            "WHERE student_id = ?";
        String getSequence = "SELECT nextval(unique_student_id)";
        try{
            updateStudent = databaseConnection.prepareStatement(updateString);
            insertStudent = databaseConnection.prepareStatement(insertString);
            findStudent = databaseConnection.prepareStatement(findString);
            nextSequenceValue = databaseConnection.prepareStatement(getSequence);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.exit(-1);
        }

    }


    /**
     *Returns the unique student ID of the current student.
     *@return studentID of current student
    **/
    public int getStudentID(){
        return student.getStudentID();
    }

    /**
     *Returns the first name of the current student.
     *@return first name of current student
    **/
    public String getFirstName(){
        return student.getFirstName();
    }

    /**
     *Returns the last name of the current student.
     *@return last name of current student
    **/
    public String getLastName(){
        return student.getLastName();
    }

    /**
     *Returns the first and last name of the current student.
     *@return first and last name of current student
    **/
    public String getFullName(){
        return student.getFullName();
    }

    /**
     *Returns the email address of the current student.
     *@return email address of the current student
    **/
    public String getEmailAddress(){
        return student.getEmailAddress();
    }

    /**
     *Finds a student based off of a student ID and sets the current student 
     *object. Makes a SQL query using a prepared statement to access database 
     *and get the first name. last name, and email address. Sets isNew to false 
     *because all changes will force an update.
     *@param studentID Unique student ID of student being searched for.
    **/
    public void find(int studentID){
        if(findStudent == null){
            System.out.println("Uh oh, Preparedstatement findStudent is null?!\n");
            return;
        }

        try{
            findStudent.setInt(1, studentID);
            ResultSet rset = findStudent.executeQuery();
            boolean hasResult = rset.next();
            if(hasResult){
                student.setStudentID(rset.getInt(1));
                student.setFirstName(rset.getString(2));
                student.setLastName (rset.getString(3));
                student.setEmailAddress(rset.getString(4));    
                isNew= false;
            }
            
            
        }
        catch(SQLException e){
            //FIXME Figure ouT how to handle excpetions
            e.printStackTrace();
            System.exit(-1);
        }
                    
    }

    /**
     *Sets the unique student ID of the current student.
     *@param studentID a unique identifier for each student
    **/
    public void setStudentID(int studentID){
        student.setStudentID(studentID);
    }

    /**
     *Sets the first name of the current student.
     *@param firstName the first name of each student
    **/
    public void setFirstName(String firstName){
        student.setFirstName(firstName);
    }

    /**
     *Sets the last name of the current student.
     *@param lastName the last name of each student
    **/
    public void setLastName(String lastName){
        student.setLastName(lastName);
    }

    /**
     *Sets the email address of the current student.
     *@param emailAddress the email address of each student
    **/
    public void setEmailAddress(String emailAddress){
        student.setEmailAddress(emailAddress);
    }

    /**
     *Pushes all changes made to the current student object to the database. 
     *If a new student is being created, an insert will be made, otherwise an 
     *update will be made. If any values are missing in the Student object, a 
     *no database transaction will occur. 
     *
    **/
    public void save(){

        /*
            Check if prepared statement is null. If is, set template
        */

        if(student.missingValues()){
            return;// one of the values is not set. Oopsy
        }

        if( isNew ){
            try{
                ResultSet rset = nextSequenceValue.executeQuery();
                if(rset.next()){
                    student.setStudentID(rset.getInt(1));
                }
                else{
                    //It appears that the sequence value could not be found.
                    //This is a no no.
                    System.out.println("Sequence value unable to be found");
                    return;
                }
                insertStudent.setInt(1, student.getStudentID());
                insertStudent.setString(2, student.getFirstName());
                insertStudent.setString(3, student.getLastName());
                insertStudent.setString(4, student.getEmailAddress());

            
                insertStudent.executeUpdate();
                isNew=false; 
            }
            catch(SQLException e){
                e.printStackTrace();
                System.exit(-1);
            }
            
        }
        else{
            try{ 
                updateStudent.setString(1, student.getFirstName());
                updateStudent.setString(2, student.getLastName());
                updateStudent.setString(3, student.getEmailAddress());
                updateStudent.setInt(4, student.getStudentID());
                updateStudent.executeUpdate();
            }
            catch(SQLException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     *Resets the current StudentDAO. Clears the student and resets for a new
     * student.S
    **/
    public void reset(){
        student.clear();
        isNew= true;

    }


    /**
     *Returns a formatted string of the Student objects.
     *@return Formatted string of the student's id, first name, last name
     * and email address.
    **/
    public String toString(){
        return student.toString(); 
    }


}
