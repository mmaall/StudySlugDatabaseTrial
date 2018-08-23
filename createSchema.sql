DROP TABLE Students CASCADE;
DROP TABLE Classes CASCADE;
DROP TABLE StudyGroups CASCADE;
DROP TABLE StudentClasses CASCADE;
DROP TABLE StudentGroups CASCADE;

CREATE SEQUENCE unique_id;
CREATE SEQUENCE unique_student_id;
CREATE SEQUENCE unique_class_id;
CREATE SEQUENCE unique_group_id;

CREATE TABLE Students(
    student_id integer,
    first_name varChar(20) NOT NULL,
    last_name varChar(20) NOT NULL,
    email_address varchar(40) UNIQUE,

    PRIMARY KEY(student_id)
);

CREATE TABLE Classes(
    class_ID integer, 
    department varChar(4) NOT NULL,
    class_number varChar(3) NOT NULL,

    PRIMARY KEY (class_id),
    UNIQUE (department, class_number)
);

CREATE TABLE StudyGroups(
    group_id integer,
    associatedClass integer REFERENCES Classes(class_id),

    PRIMARY KEY(group_id)
);

CREATE TABLE StudentClasses(
    student_id integer REFERENCES Students(student_id),
    class_id integer REFERENCES Classes(class_id),
    PRIMARY KEY (student_id, class_id)

);

CREATE TABLE StudentGroups(
    student_id integer REFERENCES Students(student_id),
    group_id integer REFERENCES StudyGroups(group_id),
    PRIMARY KEY (student_id, group_id)
);

COPY Students 
    FROM '/home/axlanthier/code-samples/StudySlugRefactored/students.dat' 
    USING DELIMITERS '|';
