package servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import controller.ControllerJob;

@WebServlet(value="/front/Iniciar",loadOnStartup=1)
=======
import controller.ControllerCadastro;

@WebServlet(value="/security/Iniciar",loadOnStartup=1)
>>>>>>> refs/remotes/origin/master
public class IniciarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
<<<<<<< HEAD
		System.out.println("Requisição realizada");
		ControllerJob controller = ControllerJob.getInstance();
=======
		//chamando a instancia que gerencia as tarefas agendadas
		ControllerCadastro controller = ControllerCadastro.getInstance();
>>>>>>> refs/remotes/origin/master
		controller.startJob();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerJob controller = ControllerJob.getInstance();
		controller.stopJob();
		//salvar novo parametro de configuração
		controller.restartJob();
	}

}
