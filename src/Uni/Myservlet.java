package Uni;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
  Servlet implementation class MyServlet
 **/
public class Myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Redirected to servlet");
		String iType = request.getParameter("req_type");
		
		// Input data
		
		if(iType.compareToIgnoreCase("newStudent")== 0){
			String iNIC = request.getParameter("NIC");
			String iStudentId = request.getParameter("studentId");
			String iFirstName = request.getParameter("firstName");
			String iLastName = request.getParameter("lastName");
			String iEmail = request.getParameter("email");
			String iDepartmentId = request.getParameter("departmentId");
			
		}// End of if - newStudent
		
		else if(iType.compareToIgnoreCase("newFacultyMember")==0){
			String iFacultyId = request.getParameter("facultyId");
			String iDepartmentId = request.getParameter("departmentId");
			String iFirstNameOfLec = request.getParameter("fNameOfLec");
			String iLastNameOfLec = request.getParameter("lastNameOfLec");
			String iEmailOfLec = request.getParameter("emailOfLec");
			String iHireDate = request.getParameter("hireData");
			
		}// End of ifelse - newFacultyMember
		
		else if(iType.compareToIgnoreCase("newEnrollment")==0){
			String iEnrollmentId = request.getParameter("enrollmentId");
			String iCourseId = request.getParameter("courseId");
			String iNIC = request.getParameter("NIC");
			String iStudentId = request.getParameter("studentId");
			String iEnrollmentDate = request.getParameter("enrollmentDate");
			String iGrade = request.getParameter("grade");
			
		} // End of ifelse - newEnrollment
		
		else if(iType.compareToIgnoreCase("newCourse")==0){
			String iCourseId = request.getParameter("courseId");
			String iFacultyId = request.getParameter("facultyId");
			String iDepartmentId = request.getParameter("departmentId");
			String iCourseName = request.getParameter("courseName");
			String iCredits = request.getParameter("credits");
			
		} // End of ifelse - newCourse
		
		else if(iType.compareToIgnoreCase("newDepartment")==0){
			String iDepartmentId = request.getParameter("departmentId");
			String iLocation = request.getParameter("location");
			String iDepartmentName = request.getParameter("departmentName");
			
		} // End of ifelse - newDepartment
		
		
		// searching details...
		
		
		else if(iType.compareToIgnoreCase("searchStudent")== 0){
			String iStudentId = request.getParameter("studentId");
			System.out.println("Searching for student: "+ iStudentId);
			//create an attribute and store the StudentId received
			request.setAttribute("StudentId", iStudentId);
			request.getRequestDispatcher("displayStudentDetails.jsp").forward(request, response);
		}//end searchStudent
		
		else if(iType.compareToIgnoreCase("searchFacultyMember")== 0){
			String iFacultyId = request.getParameter("facultyId");
			System.out.println("Searching Faculty Member : "+ iFacultyId);
			request.setAttribute("facultyId", iFacultyId);
			request.getRequestDispatcher("displayFacultyMemberdDtails.jsp").forward(request, response);
		}//end searchFacultyMember
		
		else if(iType.compareToIgnoreCase("searchEnrollmentDetails")== 0){
			String iEnrollmentId = request.getParameter("enrollmentId");
			System.out.println("Searching Enrollment Details : "+ iEnrollmentId);
			request.setAttribute("enrollmentId", iEnrollmentId);
			request.getRequestDispatcher("displayEnrollmentDtails.jsp").forward(request, response);
		}//end searchEnrollmentDetails
		
		else if(iType.compareToIgnoreCase("searchCourseDetails")== 0){
			String iCourseId = request.getParameter("courseId");
			System.out.println("Searching Course Details : "+ iCourseId);
			request.setAttribute("courseId", iCourseId);
			request.getRequestDispatcher("displayCourseDtails.jsp").forward(request, response);
		}//end searchCourseDetails
		
		else if(iType.compareToIgnoreCase("searchDepartmentDetails")== 0){
			String iDepartmentId = request.getParameter("departmentId");
			System.out.println("Searching Department Details : "+ iDepartmentId);
			request.setAttribute("departmentId", iDepartmentId);
			request.getRequestDispatcher("displayDepartmentDtails.jsp").forward(request, response);
		}//end searchDepartmentDetails
		
		else if(iType.compareToIgnoreCase("login")==0){
			String user = request.getParameter("username");
			String pass = request.getParameter("password");
			User u = new User();
			User dbUser = u.validateUser(user,pass);
			System.out.println("Password in database: "+dbUser.getPassword());
			if(pass.compareTo(dbUser.getPassword())==0){
				 HttpSession session = request.getSession();
				 session.setAttribute("username", user);
				 session.setAttribute("password", pass);
				 request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		
		// Update Data for Student
        else if (iType.compareToIgnoreCase("updateStudent") == 0) {
            String studentId = request.getParameter("studentId");
            String columnName = request.getParameter("column");
            Student s = new Student();
            if (columnName.compareToIgnoreCase("email") == 0) {
                String newEmail = request.getParameter("newVal");
                s.updateEmail(studentId, newEmail);
            }
            else if (columnName.compareToIgnoreCase("departmentId") == 0) {
                String newDepartmentId = request.getParameter("newVal");
                s.updateDepartmentId(studentId, newDepartmentId);
            }
            // Other column updates for student
        }
		
		// Update Faculty Member
        else if (iType.compareToIgnoreCase("updateFacultyMember") == 0) {
            String facultyId = request.getParameter("facultyId");
            String columnName = request.getParameter("column");
            Faculty_Member fm = new Faculty_Member();
            if (columnName.compareToIgnoreCase("emailOfLec") == 0) {
                String newEmailOfLec = request.getParameter("newVal");
                fm.updateFaculty(facultyId,columnName, newEmailOfLec);
            }
            else if (columnName.compareToIgnoreCase("departmentId") == 0) {
                String newDepartmentId = request.getParameter("newVal");
                fm.updateFaculty(facultyId, newDepartmentId);
            }
            else if (columnName.compareToIgnoreCase("hireDate") == 0) {
                String newHireDate = request.getParameter("newVal");
                fm.updateFaculty(facultyId, newHireDate);
            }
        }
		
		// Update Department
        else if (iType.compareToIgnoreCase("updateDepartment") == 0) {
            String departmentId = request.getParameter("departmentId");
            String columnName = request.getParameter("column");
            Department d = new Department();
            if (columnName.compareToIgnoreCase("location") == 0) {
                String newLocation = request.getParameter("newVal");
                d.updateLocation(departmentId, newLocation);
            }
        }
		
		// Update Course
        else if (iType.compareToIgnoreCase("updateCourse") == 0) {
            String courseId = request.getParameter("courseId");
            String columnName = request.getParameter("column");
            Course c = new Course();
            if (columnName.compareToIgnoreCase("credits") == 0) {
                String newCredits = request.getParameter("newVal");
                c.updateCredits(courseId, newCredits);
            }
        }
		
		// Update Enrollment_Management
        else if (iType.compareToIgnoreCase("updateEnrollment_Management") == 0) {
            String enrollmentId = request.getParameter("enrollmentId");
            String columnName = request.getParameter("column");
            Enrollment_Management em = new Enrollment_Management();
            if (columnName.compareToIgnoreCase("grade") == 0) {
                String newGrade = request.getParameter("newVal");
                em.updateGrade(enrollmentId,columnName, newGrade);
	           }
            else if (columnName.compareToIgnoreCase("enrollmentDate") == 0) {
                String newEnrollmentDate = request.getParameter("newVal");
                em.updateEnrollmentDate(enrollmentId, newEnrollmentDate);
	           }
            else if (columnName.compareToIgnoreCase("course") == 0) {
                String newCourse = request.getParameter("newVal");
                em.updateCourse(enrollmentId, newCourse);
	           }
            }	
		
	}
