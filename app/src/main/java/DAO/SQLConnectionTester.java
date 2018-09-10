import java.sql.*;

public class SQLConnectionTester {


   //  Database credentials
	
   static final String USER = "axlanthier";
   static final String PASS = "poseydog";
   
   static final String DB_URL = "jdbc:postgresql://192.168.2.150:5433/StudySlug";
	  
   
   public static void main(String[] args) {
	   
   Connection conn = null;
   Statement stmt = null;
   
   try{
	   
   
      System.out.println("Connecting to database...");
      
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      stmt = conn.createStatement();
      
      String sql = "SELECT student_id, first_name, last_name FROM students";
      
      ResultSet rs = stmt.executeQuery(sql);

      while(rs.next()){
    	  
         //Retrieve by column name
    	  
         int id  = rs.getInt("id");
         String first = rs.getString("first_name");
         String last = rs.getString("last_name");

         //Display values
         
         System.out.print("ID: " + id);
         System.out.print(", Last Name: " + last);
         System.out.print(", First Name: " + first + "\n");
         
         
      }
      
      rs.close();
      stmt.close();
      conn.close();
      
   }catch(SQLException se){
	   
      //Handle errors for JDBC
      se.printStackTrace();

   }catch(Exception e){

      //Handle errors for Class.forName
      e.printStackTrace();

   }finally{

      //finally block used to close resources

      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
}
   
}//end main
