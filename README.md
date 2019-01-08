Experimenting with PostgreSQL Database Access
=============================================


After working on StudySlug, an application to form study groups at UCSC, I realized I was not a huge fan of using Firebase as a database. That was the perfect opportunity to explore implementing a SQL Database into an application and learning how an application and it's database truly communicate. 

The end goal is to remove Firebase from Study Slug and replace it with a PostgreSQL database being hosted on Amazon Web Services EC2. Rather than use an ORM, I'm writing my own data acess objects (DAO). This is more for myself to learn how data is accessed from the database and to brush up on my SQL. This repository contains all the code pertaining to the backend database including the SQL schema, DAOs, and tests. 



### Core Components
+ Amazon AWS EC2 instance to host database
+ PostgreSQL as the database
+ Java is the language I am using to write the DAOs

### Usage
#### Amazon EC2 Instance
Spin up an Amazon EC2 instance. Install PostgreSQL and run createSchema.sql to add all necessary tables. Make sure to open up PostgreSQL's port (default 5432) in your security groups on AWS. 
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
Also run setup.sh. This bash script will set up environment variables that many tests and other scripts will depend on. Easist thing to do is to write the following in .bashrc. 
```bash
	source ~/StudySlugRefactored/setup.sh
```
Your filepath to setup.sh will probably not be the same, so change it accordingly. 



### Data Access Objects

The goal was to create simple and reusable DAOs that can handle selects, inserts, deletes and updates. An example of a singleton operation is below. 

```java
    StudentDAO student = new StudentDAO(connection);


    //Create Student
    student.setFirstName("Michael");
    student.setLastName("Lanthier");
    student.setEmailAddress("mlanthier@gmail.com");
    student.save();

    //Update Student
    student.setFirstName("Mike");
    student.save();


    //Find Student by id
    student.setStudentId("20");
    student.find();
```

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
	protected PreparedStatement updateStatement;
	protected PreparedStatement insertStatement;
	protected PreparedStatement findStatement;
	protected PreparedStatement deleteStatement;
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

### Future Goals
As of right now, this project is not in a good state to be deployed to the Study Slug Android application that was developed in the past. It requires quite a few more objects and features listed below.

##### Exception Handling 
Exception handling right now mostly consists of printing, stack traces and exiting the program. This is nuacceptable if this project is to continue, so throwing correct and useful exceptions is high up on the list of things to get done.  

##### Logger
As of right now there is no way of logging anything the program does. Implementing some sort of log would better allow me to debug issues as well as track other information, such as unsuccesful queries, connection issues, etc.. 

##### Expanding DAOs
Not all the DAOs are finished and not all have been created for all the possible information you could want from the database. Defintitly more to come.