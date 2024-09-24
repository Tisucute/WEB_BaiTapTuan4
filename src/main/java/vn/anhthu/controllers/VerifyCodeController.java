package vn.anhthu.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.anhthu.models.UserModel;

@WebServlet(urlPatterns = {"/VerifyCode"})
public class VerifyCodeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		try (PrintWriter out = resp.getWriter()){
			HttpSession session = req.getSession();
			UserModel user = (UserModel) session.getAttribute("account");
			
			String code = req.getParameter("authcode");
			
			if (code.equals(user.getId())) {
				user.setEmail(user.getEmail());
				//user.setStatus(1);
				
				//userService.updatestatus(user);
				
				out.println("<div class=\"container\"><br/>\r\n" + "<br/>\r\n"
							+ "<br/> Kích hoạt tài khoản thành công!<br/>\r\n" +"<br/>\r\n"
							+ "<br/></div>");
			}
			else {
				out.println("<div class=\"container\"><br/>\r\n" + "<br/>\r\n"
							+ "<br/>Sai mã kích hoạt, vui lòng kiểm tra lại<br/>\r\n" + "<br/>\r\n"
							+ "<br/></div>");
			}
		}
	}

}
