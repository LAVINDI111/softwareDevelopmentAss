CREATE TABLE Course (
    courseId INT PRIMARY KEY AUTO_INCREMENT,
    courseName VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    facultyId INT,
    departmentId INT,
    FOREIGN KEY (facultyId) REFERENCES Faculty_Member(facultyId),
    FOREIGN KEY (departmentId) REFERENCES Department(departmentId)
);