package cn.edu.hit.weibo.controller;

import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//测试用
//		System.out.println("用户名："+username);
//		System.out.println("密码："+password);

		User user = us.login(username,password);
		if(user != null){
			//测试用
//			System.out.println("找到了用户");

			Cookie cookie = new Cookie("username",username);
			response.addCookie(cookie);
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			out.write("true");
			out.close();
		}else {
			out.write("false");
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
