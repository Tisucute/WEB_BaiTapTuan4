package vn.anhthu.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.anhthu.services.IUserService;
import vn.anhthu.services.impl.UserService;
import vn.anhthu.utils.Constant;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IUserService service = new UserService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession(false) != null && req.getSession().getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String passwordRepeat = req.getParameter("psw-repeat");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        
        String alertMsg = validateRegistration(username, password, passwordRepeat, email, phone);
        if (alertMsg != null) {
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
            return;
        }

        boolean isSuccess = service.register(email, password, username, fullname, phone);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "System error!");
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
        }
	}
	private String validateRegistration(String username, String password, String passwordRepeat, String email, String phone) {
        if (service.checkExistEmail(email)) {
            return "Email đã tồn tại!";
        }
        if (service.checkExistUsername(username)) {
            return "Tài khoản đã tồn tại!";
        }
        if (service.checkExistPhone(phone)) {
            return "Số điện thoại đã tồn tại!";
        }
        if (!password.equals(passwordRepeat)) {
            return "Mật khẩu không giống nhau";
        }
        return null;
    }
}
