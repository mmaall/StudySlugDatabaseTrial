DROP TABLE Students CASCADE;
DROP TABLE Courses CASCADE;
DROP TABLE StudyGroups CASCADE;
DROP TABLE StudentCourses CASCADE;
DROP TABLE StudentGroups CASCADE;

CREATE SEQUENCE unique_id;
CREATE SEQUENCE unique_student_id;
CREATE SEQUENCE unique_course_id;
CREATE SEQUENCE unique_group_id;

CREATE TABLE Students(
    student_id integer,
    first_name varChar(20) NOT NULL,
    last_name varChar(20) NOT NULL,
    email_address varchar(40) UNIQUE,

    PRIMARY KEY(student_id)
);

CREATE TABLE Courses(
    course_id integer, 
    department varChar(4) NOT NULL,
    course_number varChar(4) NOT NULL,
    section integer,

    PRIMARY KEY (course_id),
    UNIQUE (department, class_number)
);

CREATE TABLE Group(
    group_id integer,
    associatedClass integer REFERENCES Courses(course_id),

    PRIMARY KEY(group_id)
);

CREATE TABLE StudentCourses(
    student_id integer REFERENCES Students(student_id),
    course_id integer REFERENCES Courses(course_id),
    PRIMARY KEY (student_id, course_id)

);

CREATE TABLE StudentGroups(
    student_id integer REFERENCES Students(student_id),
    group_id integer REFERENCES StudyGroups(group_id),
    PRIMARY KEY (student_id, group_id)
);

COPY Students 
    FROM '/home/axlanthier/code-samples/StudySlugRefactored/students.dat' 
    USING DELIMITERS '|';
