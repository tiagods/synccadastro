package com.prolink.sync.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Iniciar")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		if(user.equals("admin") && password.equals("admin")){
			UserInfo userInfo = new UserInfo();
			userInfo.setId(user);
			userInfo.setSenha(password);

			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userInfo);
			
			response.sendRedirect("/SyncCadastro/front/Listar");
//			request.getRequestDispatcher("/front/Listar").forward(request, response);
		}
		}

}
