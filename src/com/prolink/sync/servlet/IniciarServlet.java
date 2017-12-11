package com.prolink.sync.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prolink.sync.controller.ControllerJob;

@WebServlet(value="/front/Iniciar", loadOnStartup=1)

public class IniciarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		ControllerJob controller = ControllerJob.getInstance();
		//chamando a instancia que gerencia as tarefas agendadas
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
