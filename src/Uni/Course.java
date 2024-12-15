package Uni;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseId;
    private String courseName;
    private String credits;
    private String departmentId;
    private String facultyId;

    // Getters and setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    // Add a new course
    public void addCourse(String courseId, String courseName, String credits, String departmentId, String facultyId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO course (courseId, courseName, credits, departmentId, facultyId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, courseId);
        ps.setString(2, courseName);
        ps.setString(3, credits);
        ps.setString(4, departmentId);
        ps.setString(5, facultyId);
        ps.executeUpdate();
        conn.close();
    }

    // Update course information
    public void updateCourse(String courseId, String columnName, String newValue) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "UPDATE course SET " + columnName + " = ? WHERE courseId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newValue);
        ps.setString(2, courseId);
        ps.executeUpdate();
        conn.close();
    }

    // Delete a course
    public void deleteCourse(String courseId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "DELETE FROM course WHERE courseId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, courseId);
        ps.executeUpdate();
        conn.close();
    }

    // View details of a course, including students enrolled and the faculty member teaching
    public void viewCourseDetails(String courseId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        
        // Retrieve course details
        String courseQuery = "SELECT * FROM course WHERE courseId = ?";
        PreparedStatement ps = conn.prepareStatement(courseQuery);
        ps.setString(1, courseId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Course ID: " + rs.getString("courseId"));
            System.out.println("Course Name: " + rs.getString("courseName"));
            System.out.println("Credits: " + rs.getString("credits"));
            System.out.println("Department ID: " + rs.getString("departmentId"));
            System.out.println("Faculty ID: " + rs.getString("facultyId"));
        }

        // Retrieve list of students enrolled in the course
        String studentQuery = "SELECT s.studentNo, s.firstName, s.lastName FROM student s JOIN enrollment e ON s.studentNo = e.studentNo WHERE e.courseId = ?";
        PreparedStatement ps2 = conn.prepareStatement(studentQuery);
        ps2.setString(1, courseId);
        ResultSet studentRs = ps2.executeQuery();
        System.out.println("Enrolled Students:");
        while (studentRs.next()) {
            System.out.println("Student No: " + studentRs.getString("studentNo"));
            System.out.println("Name: " + studentRs.getString("firstName") + " " + studentRs.getString("lastName"));
        }

        // Retrieve faculty member teaching the course
        String facultyQuery = "SELECT f.firstName, f.lastName FROM faculty f JOIN course c ON f.facultyId = c.facultyId WHERE c.courseId = ?";
        PreparedStatement ps3 = conn.prepareStatement(facultyQuery);
        ps3.setString(1, courseId);
        ResultSet facultyRs = ps3.executeQuery();
        if (facultyRs.next()) {
            System.out.println("Faculty: " + facultyRs.getString("firstName") + " " + facultyRs.getString("lastName"));
        }

        conn.close();
    }

    // List all courses
    public void displayAllCourses() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM course";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            System.out.println("Course ID: " + rs.getString("courseId"));
            System.out.println("Course Name: " + rs.getString("courseName"));
            System.out.println("Credits: " + rs.getString("credits"));
            System.out.println("Department ID: " + rs.getString("departmentId"));
            System.out.println("Faculty ID: " + rs.getString("facultyId"));
        }
        conn.close();
    }
}
