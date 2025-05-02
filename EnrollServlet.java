import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Step 1: Get courseId from URL parameter
        String courseId = request.getParameter("courseId");

        // Step 2: Get the current user's session
        HttpSession session = request.getSession(false);
        if (session == null) {
            // If no session exists, redirect to login page
            response.sendRedirect("login.html");
            return;
        }

        // Step 3: Add course to the enrolled list in session
        List<String> enrolledCourses = (List<String>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        if (!enrolledCourses.contains(courseId)) {
            enrolledCourses.add(courseId);  // Add course to the list
            session.setAttribute("enrolledCourses", enrolledCourses); // Store the updated list in session
        }

        // Step 4: Redirect back to DashboardServlet
        response.sendRedirect("dashboard?msg=Enrolled in course " + courseId);
    }
}
