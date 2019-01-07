Study Slug Refactored
=====================


After working on StudySlug, an application to form study groups at UCSC, I realized I was not a huge fan of using Firebase as a database. That was the perfect opportunity to explore implementing a SQL Database into an application and learning how an application and it's database truly communicate. 

The end goal is to remove Firebase from Study Slug and replace it with a PostgreSQL database being hosted on AWS EC2. Rather than use an ORM, I'm writing my own data acess objects (DAO). This is more for myself to learn how data is accessed from the database and to brush up on my SQL. This repository contains all the code pertaining to the backend database including the SQL schema, DAOs, and tests. 



### Core Components
+ Amazon AWS EC2 instance to host database
+ PostgreSQL as the database
+ Java is the language I am using to write the DAOs

### Usage
#### Amazon EC2 Instance
Spin up an Amazon EC2 instance. Install PostgreSQL and run createSchema.sql to add all necessary tables. Done!
#### Code
Clone this lovely repository and do a couple of things. Create a properties file, db.properties. Place the file cfg like below. 
```bash
	/StudySlugRefactored/app/cfg/db.properties
``` 
db.properties should be formated as below.

```text
	ip= <AWS EC2 Instance's ip>
	port= <Port PostgreSQL is listening to on EC2 instance>
	user= <PostgreSQL database username>
	password= <Password to PostgreSQL database>
```



### Data Access Objects

All the data access objects have been written by myself in Java and have their appropriate getters and setters. 

```java 
	public String getFirstName(){
        return student.getFirstName();
    }

    public void setStudentID(int studentID){
        student.setStudentID(studentID);
    }
```

All DAOs have prepared SQL statements for effeciency.

```java
	private PreparedStatement updateStatement;
	private PreparedStatement insertStatement;
	private PreparedStatement findStatement;
	private PreparedStatement deleteStatement;
```

JDBC is used to connect to database, prepare statements and execute queries. 

```java
	String findString = "SELECT "+ 
                        "student_id, first_name, "+ 
                        "last_name, email_address "+
                        "FROM students " +
                        "WHERE student_id = ?";
    try{
        findStatement= databaseConnection.prepareStatement(findString);
    }
    catch(SQLException e){
        System.out.println("Preparing find statement failed");
        e.printStackTrace();
    }
```
### Unique ID Generation
The unique ID generation is done on the database by using sequences in PostgreSQL. When creating new entries into the database, all DAOs call the UniqueIDGenerator class to generate a unique identifier. This class requests an ID from sequence unique_id in the database. 
```sql
	SELECT nextval('unique_id')
```

