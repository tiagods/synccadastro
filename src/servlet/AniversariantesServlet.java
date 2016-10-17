package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Aniversariante;
import model.AniversarianteDao;

@WebServlet(value="/front/Aniversarios")
public class AniversariantesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AniversarianteDao aniversarios = AniversarianteDao.getInstance();
		List<Aniversariante> aniversariantes = aniversarios.receberAniversariantes();
		request.setAttribute("aniversariantes", aniversariantes);
		request.getRequestDispatcher("/front/aniversarios.jsp").forward(request, response);
	}

}
