package Uni;

import java.sql.*;
import java.util.*;

public class Student {
    private String studentId;
    private String NIC;
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String departmentId;

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    // Method to retrieve details of a specific student
    public Student getStudentDetail(String studentId) {
        Student student = null;
        Connection dbConn = null;

        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                PreparedStatement prst = dbConn.prepareStatement("SELECT * FROM student WHERE studentId = ?");
                prst.setString(1, studentId);
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setNIC(rs.getString(2));
                    student.setFirstName(rs.getString(3));
                    student.setLastName(rs.getString(4));
                    student.setEmail(rs.getString(5));
                    student.setDob(rs.getString(6));
                    student.setDepartmentId(rs.getString(7));
                }
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    // Method to retrieve all students from a given department
    public List<Student> getDepartmentStudents(String departmentId) {
        List<Student> studentList = new ArrayList<Student>();
        Student student = null;
        Connection dbConn = null;

        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                PreparedStatement prst = dbConn.prepareStatement("SELECT * FROM student WHERE departmentId = ?");
                prst.setString(1, departmentId);
                ResultSet rs = prst.executeQuery();
                while (rs.next()) {
                    student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setNIC(rs.getString(2));
                    student.setFirstName(rs.getString(3));
                    student.setLastName(rs.getString(4));
                    student.setEmail(rs.getString(5));
                    student.setDob(rs.getString(6));
                    student.setDepartmentId(rs.getString(7));
                    studentList.add(student);
                }
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentList;
    }

    // Method to update student details like email, department, etc.
    public void updateStudentDetail(String studentId, String newEmail, String newDepartment) {
        Connection dbConn = null;

        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                PreparedStatement prst = dbConn.prepareStatement("UPDATE student SET email = ?, departmentId = ? WHERE studentId = ?");
                prst.setString(1, newEmail);
                prst.setString(2, newDepartment);
                prst.setString(3, studentId);
                prst.executeUpdate();
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a student to the database
    public void addStudent(String NIC, String studentId, String firstName, String lastName, String email, String dob, String departmentId) {
        Connection dbConn = null;
        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                String query = "INSERT INTO student (NIC, studentId, firstName, lastName, email, dob, departmentId) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = dbConn.prepareStatement(query);
                ps.setString(1, NIC);
                ps.setString(2, studentId);
                ps.setString(3, firstName);
                ps.setString(4, lastName);
                ps.setString(5, email);
                ps.setString(6, dob);
                ps.setString(7, departmentId);
                ps.executeUpdate();
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student from the database
    public void deleteStudent(String studentId) {
        Connection dbConn = null;
        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                PreparedStatement prst = dbConn.prepareStatement("DELETE FROM student WHERE studentId = ?");
                prst.setString(1, studentId);
                prst.executeUpdate();
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update a student's department
    public void updateDepartment(String studentId, String newDepartment) {
        Connection dbConn = null;

        try {
            dbConn = ConnectionManager.getConnection();
            if (dbConn != null) {
                PreparedStatement prst = dbConn.prepareStatement("UPDATE student SET departmentId = ? WHERE studentId = ?");
                prst.setString(1, newDepartment);
                prst.setString(2, studentId);
                prst.executeUpdate();
            }
            dbConn.close();
        } catch (SQLException sq) {
            sq.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
