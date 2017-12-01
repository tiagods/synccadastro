package com.prolink.sync.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prolink.sync.model.Aniversariante;
import com.prolink.sync.model.AniversarianteDao;

@WebServlet(value="/front/Aniversarios")
public class AniversariantesServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2891484410005297747L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AniversarianteDao aniversarios = AniversarianteDao.getInstance();
		List<Aniversariante> aniversariantes = aniversarios.receberAniversariantes();
		request.setAttribute("aniversariantes", aniversariantes);
		request.getRequestDispatcher("/front/aniversarios.jsp").forward(request, response);
	}

}
