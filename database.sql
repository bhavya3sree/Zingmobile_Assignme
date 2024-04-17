

-- use the created database
USE role_based_db;

-- create table for colleges
CREATE TABLE colleges (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    campus VARCHAR(100) NOT NULL,
    section VARCHAR(10) NOT NULL
);

-- create table for students
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    section VARCHAR(10) NOT NULL,
    marks VARCHAR(100) NOT NULL,
    college_id INT,
    FOREIGN KEY (college_id) REFERENCES colleges(id)
);

-- create table for users and their roles
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('super_admin', 'admin', 'teacher', 'student') NOT NULL
);

-- insert sample data for colleges
INSERT INTO colleges (name, state, city, campus, section) VALUES
('Sri Chaitanya', 'Telangana', 'Hyderabad', 'KPHB', 'A'),
('Sri Chaitanya', 'Telangana', 'Hyderabad', 'KPHB', 'B');

-- insert sample data for students
INSERT INTO students (name, section, marks, college_id) VALUES
('bhavya', 'A', '90,85,95', 1),
('chesvika', 'B', '80,75,85', 1);

-- insert sample data for users
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'admin'),
('teacher', 'teacher123', 'teacher'),
('student', 'student123', 'student'),
('superadmin', 'superadmin123', 'super_admin');
