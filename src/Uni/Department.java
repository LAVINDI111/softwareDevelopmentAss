package Uni;

import java.sql.*;
import java.util.*;

public class Department {
    private String departmentId;
    private String name;
    private String location;
    
    // Getters and Setters
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    // Add a new department
    public void addDepartment(String departmentId, String name, String location) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO department (department_id, name, location) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, departmentId);
        ps.setString(2, name);
        ps.setString(3, location);
        ps.executeUpdate();
        conn.close();
    }

    // Update department information
    public void updateDepartment(String departmentId, String columnName, String newValue) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "UPDATE department SET " + columnName + " = ? WHERE department_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newValue);
        ps.setString(2, departmentId);
        ps.executeUpdate();
        conn.close();
    }

    // Delete a department, ensuring courses and faculty are handled
    public void deleteDepartment(String departmentId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        
        // Optionally, add logic to handle related courses and faculty
        
        String query = "DELETE FROM department WHERE department_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, departmentId);
        ps.executeUpdate();
        conn.close();
    }

    // Search for a specific department by ID and display details
    public void searchDepartment(String departmentId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM department WHERE department_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, departmentId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            System.out.println("Department ID: " + rs.getString("department_id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Location: " + rs.getString("location"));
        }
        conn.close();
    }

    // View details of all departments, including associated courses and faculty
    public void displayAllDepartments() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM department";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            System.out.println("Department ID: " + rs.getString("department_id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Location: " + rs.getString("location"));
            
            // Assuming you have a method to get associated courses and faculty
            displayDepartmentDetails(rs.getString("department_id"));
        }
        conn.close();
    }

    // View department details including courses and faculty (assuming related tables)
    private void displayDepartmentDetails(String departmentId) throws SQLException {
        // Displaying associated courses
        Connection conn = ConnectionManager.getConnection();
        String courseQuery = "SELECT * FROM courses WHERE department_id = ?";
        PreparedStatement ps = conn.prepareStatement(courseQuery);
        ps.setString(1, departmentId);
        ResultSet rs = ps.executeQuery();
        
        System.out.println("Courses Offered:");
        while (rs.next()) {
            System.out.println("Course ID: " + rs.getString("course_id"));
            System.out.println("Course Name: " + rs.getString("course_name"));
        }
        
        // Displaying associated faculty
        String facultyQuery = "SELECT * FROM faculty WHERE department_id = ?";
        ps = conn.prepareStatement(facultyQuery);
        ps.setString(1, departmentId);
        rs = ps.executeQuery();
        
        System.out.println("Faculty Members:");
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getString("faculty_id"));
            System.out.println("Faculty Name: " + rs.getString("faculty_name"));
        }
        conn.close();
    }
}
