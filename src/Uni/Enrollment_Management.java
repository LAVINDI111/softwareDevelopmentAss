package Uni;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Enrollment_Management {
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private Date enrollmentDate;
    private String grade;

    // Getter and Setter methods
    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    // Enroll a student in a course
    public void enrollStudent(String enrollmentId, String studentId, String courseId, String enrollmentDate, String grade) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "INSERT INTO enrollment_management (enrollmentId, studentId, courseId, enrollmentDate, grade) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, enrollmentId);
            ps.setString(2, studentId);
            ps.setString(3, courseId);
            ps.setString(4, enrollmentDate);
            ps.setString(5, grade);
            ps.executeUpdate();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Withdraw a student from a course
    public void withdrawStudent(String studentId, String courseId) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "DELETE FROM enrollment_management WHERE studentId = ? AND courseId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.executeUpdate();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Update an enrollment record (e.g., update grade or course)
    public void updateEnrollment(String enrollmentId, String columnName, String newValue) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "UPDATE enrollment_management SET " + columnName + " = ? WHERE enrollmentId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newValue);
            ps.setString(2, enrollmentId);
            ps.executeUpdate();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // View enrollment history of a student
    public List<Enrollment_Management> viewEnrollmentHistory(String studentId) {
        List<Enrollment_Management> enrollments = new ArrayList<Enrollment_Management>();
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            String query = "SELECT * FROM enrollment_management WHERE studentId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment_Management enrollment = new Enrollment_Management();
                enrollment.setEnrollmentId(rs.getString("enrollmentId"));
                enrollment.setStudentId(rs.getString("studentId"));
                enrollment.setCourseId(rs.getString("courseId"));
                enrollment.setEnrollmentDate(rs.getDate("enrollmentDate"));
                enrollment.setGrade(rs.getString("grade"));
                enrollments.add(enrollment);
            }
        } catch (SQLException sq) {
            sq.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enrollments;
    }
}
