CREATE TABLE Enrollment_Management (
    enrollmentId INT PRIMARY KEY AUTO_INCREMENT,
    enrollmentDate DATE NOT NULL,
    grade CHAR(2),
    courseId INT,
    studentId INT,
    FOREIGN KEY (courseId) REFERENCES Course(courseId),
    FOREIGN KEY (studentId) REFERENCES Student(studentId)
);