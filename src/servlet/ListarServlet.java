package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CadastroBean;
import model.CadastroDao;

/**
 * Servlet implementation class InicioServlet
 */
@WebServlet("/front/Listar")
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CadastroDao  cadastro = CadastroDao.getInstance();
		List<CadastroBean> contatos =cadastro.readClients();
		System.out.println("peguei lista");
		request.setAttribute("contatos", contatos);
		System.out.println("redirecionando");
		request.getRequestDispatcher("/front/listarClientes.jsp").forward(request, response);
	}
}
