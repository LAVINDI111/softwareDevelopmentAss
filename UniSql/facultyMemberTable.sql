CREATE TABLE Faculty_Member (
    facultyId INT PRIMARY KEY AUTO_INCREMENT,
    firstNameOfLec VARCHAR(50) NOT NULL,
    lastNameOfLec VARCHAR(50) NOT NULL,
    emailOfLec VARCHAR(100) UNIQUE NOT NULL,
    hireDate DATE NOT NULL,
    departmentId INT,
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId)
);