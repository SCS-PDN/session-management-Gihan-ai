import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Step 1: Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // If the session is null or user is not logged in, redirect to login page
            response.sendRedirect("login.html");
            return;
        }

        // Step 2: Create a list of courses (hardcoded)
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Java Programming", "Alice"));
        courses.add(new Course("102", "Web Development", "Bob"));
        courses.add(new Course("103", "Data Structures", "Charlie"));

        // Step 3: Store courses in the request attribute
        request.setAttribute("courses", courses);

        // Step 4: Forward the request to dashboard.jsp
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}



