import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Step 1: Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();  // Invalidate the session to log out the user
        }

        // Step 2: Remove the username cookie (if it exists)
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    cookie.setMaxAge(0);  // Set the cookie's max age to 0 to delete it
                    response.addCookie(cookie);  // Add the cookie to the response to delete it
                }
            }
        }

        // Step 3: Redirect to login.html
        response.sendRedirect("login.html");
    }
}
