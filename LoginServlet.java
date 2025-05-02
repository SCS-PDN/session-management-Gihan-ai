import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    // Hardcoded users for login validation
    private static final Map<String, String> USERS = new HashMap<>();

    @Override
    public void init() {
        // Prepopulate users and passwords
        USERS.put("student1", "pass1");
        USERS.put("student2", "pass2");
        USERS.put("admin", "admin123");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Step 2: Validate credentials
        if (USERS.containsKey(username) && USERS.get(username).equals(password)) {
            // Step 3: If valid, create session and store username in a cookie

            // Create session and store username
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Store the username in a cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // Set cookie to expire in 1 hour
            response.addCookie(userCookie);

            // Redirect to DashboardServlet after successful login
            response.sendRedirect("dashboard");

        } else {
            // Step 4: If invalid, redirect back to login page
            response.sendRedirect("login.html");
        }
    }
}


