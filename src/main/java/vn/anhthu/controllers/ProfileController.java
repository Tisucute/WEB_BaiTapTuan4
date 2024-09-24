package vn.anhthu.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.anhthu.models.UserModel;
import vn.anhthu.services.IUserService;
import vn.anhthu.services.impl.UserService;

@WebServlet(name = "ProfileController", value = "/profile")
public class ProfileController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserService();
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Retrieve username from cookie
        Cookie[] cookies = req.getCookies();
        String username = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        UserModel user = userService.findByUsername(username);

        // Set attributes in the request
        req.setAttribute("username", username);
        req.setAttribute("phone", user.getPhone()); // Example phone number
        req.setAttribute("fullname", user.getFullname()); // Example full name
        req.setAttribute("email", user.getEmail()); // Example email
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get parameters from the request
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm-password");

        // Check if the passwords match
        if (!password.equals(confirmPassword)) {
            resp.sendRedirect(req.getContextPath() + "/profile?error=Passwords do not match");
            return;
        }

        // Update user information
        UserModel user = userService.findByUsername(username);
        user.setPhone(phone);
        user.setFullname(fullname);
        user.setEmail(email);
        userService.update(user);

        userService.updatePassword(username, password);

        // Redirect to the profile page
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
