DROP TABLE Students CASCADE;
DROP TABLE Classes CASCADE;
DROP TABLE StudyGroups CASCADE;
DROP TABLE StudentClasses CASCADE;
DROP TABLE StudentGroups CASCADE;

CREATE SEQUENCE unique;

CREATE TABLE Students(
    user_id integer,
    first_name varChar(20) NOT NULL,
    last_name varChar(20) NOT NULL,
    email varchar(40) UNIQUE,

    PRIMARY KEY(user_id)
);

CREATE TABLE Classes(
    class_ID integer, 
    department varChar(4) NOT NULL,
    class_number varChar(3) NOT NULL,

    PRIMARY KEY (class_ID),
    UNIQUE (department, class_number)
);

CREATE TABLE StudyGroups(
    group_id integer,
    associatedClass integer REFERENCES Classes(class_id),

    PRIMARY KEY(group_id)
);

CREATE TABLE StudentClasses(
    student_id integer REFERENCES Students(user_id),
    classID integer REFERENCES Classes(class_id),
    PRIMARY KEY (student_id, class_id)

);

CREATE TABLE StudentGroups(
    student_id integer REFERENCES Students(user_id),
    group_id integer REFERENCES StudyGroups(group_id),
    PRIMARY KEY (student_id, group_id)
);
