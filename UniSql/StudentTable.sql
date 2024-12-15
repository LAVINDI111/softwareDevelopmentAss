CREATE TABLE Student (
    studentId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    dob DATE NOT NULL,
    departmentId INT,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId)
);
