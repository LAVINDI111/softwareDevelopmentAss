package Uni;

import java.sql.*;

public class Faculty_Member {

    // Add Faculty - Insert a new faculty member
    public void addFaculty(String facultyId, String fNameOfLec, String lNameOfLec, String departmentId, String email, String hireDate) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "INSERT INTO faculty_member (facultyId, fNameOfLec, lNameOfLec, departmentId, email, hireDate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, facultyId);
        ps.setString(2, fNameOfLec);
        ps.setString(3, lNameOfLec);
        ps.setString(4, departmentId);
        ps.setString(5, email);
        ps.setString(6, hireDate);
        ps.executeUpdate();
        conn.close();
    }

    // Delete Faculty - Remove a faculty member by facultyId
    public void deleteFaculty(String facultyId) throws SQLException {
        // Before deleting, we should check if the faculty member is assigned to any courses
        Connection conn = ConnectionManager.getConnection();
        // First, check for any courses assigned to the faculty member
        String checkQuery = "SELECT COUNT(*) FROM courses WHERE facultyId = ?";
        PreparedStatement checkPs = conn.prepareStatement(checkQuery);
        checkPs.setString(1, facultyId);
        ResultSet rs = checkPs.executeQuery();
        rs.next();
        
        if (rs.getInt(1) > 0) {
            System.out.println("This faculty member is assigned to courses and cannot be deleted.");
            conn.close();
            return;
        }
        
        // If not assigned to courses, proceed with deletion
        String deleteQuery = "DELETE FROM faculty_member WHERE facultyId = ?";
        PreparedStatement deletePs = conn.prepareStatement(deleteQuery);
        deletePs.setString(1, facultyId);
        deletePs.executeUpdate();
        conn.close();
    }

    // Update Faculty - Update a faculty member's details (e.g., email, hire date, department)
    public void updateFaculty(String facultyId, String columnName, String newValue) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "UPDATE faculty_member SET " + columnName + " = ? WHERE facultyId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, newValue);
        ps.setString(2, facultyId);
        ps.executeUpdate();
        conn.close();
    }

  

    // View Faculty Details - View a faculty member's details including courses and department
    public void viewFacultyDetails(String facultyId) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        
        // Query to get faculty member details
        String query = "SELECT * FROM faculty_member WHERE facultyId = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, facultyId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            System.out.println("Faculty ID: " + rs.getString("facultyId"));
            System.out.println("First Name: " + rs.getString("fNameOfLec"));
            System.out.println("Last Name: " + rs.getString("lNameOfLec"));
            System.out.println("Department ID: " + rs.getString("departmentId"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Hire Date: " + rs.getString("hireDate"));
            
            // Get assigned courses for this faculty member
            String courseQuery = "SELECT courseName FROM courses WHERE facultyId = ?";
            PreparedStatement coursePs = conn.prepareStatement(courseQuery);
            coursePs.setString(1, facultyId);
            ResultSet courseRs = coursePs.executeQuery();
            
            System.out.println("Courses Assigned:");
            while (courseRs.next()) {
                System.out.println(courseRs.getString("courseName"));
            }
        } else {
            System.out.println("No faculty member found with the given Faculty ID.");
        }
        
        conn.close();
    }

    // Display all Faculty members
    public void displayAllFaculty() throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        String query = "SELECT * FROM faculty_member";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            System.out.println("Faculty ID: " + rs.getString("facultyId"));
            System.out.println("First Name: " + rs.getString("fNameOfLec"));
            System.out.println("Last Name: " + rs.getString("lNameOfLec"));
            System.out.println("Department ID: " + rs.getString("departmentId"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Hire Date: " + rs.getString("hireDate"));
        }
        
        conn.close();
    }
}
