package com.prolink.synccadastro.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prolink.synccadastro.model.Cliente;
import com.prolink.synccadastro.services.ClientesServices;
import com.prolink.synccadastro.services.ClientesServicesNs;

@Component
public class ClientesJob{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClientesServices clientesJob;
	
	@Autowired
	private ClientesServicesNs clientesNs;
	
	@Scheduled(cron = "${planilha.agendamento}")
	public void execute(){
		logger.info("Start Job - Cadastro");
		clientesJob.iniciarAtualizacao();
        logger.info("End of process - Cadastro");
	}
	
	@Scheduled(cron = "${cliente.agendamento}")
	public void executeTwo(){
		logger.info("Start Job - Cadastro Mongo");
		List<Cliente> clientes = clientesJob.listar();
		clientesNs.atualizar(clientes, true);
        logger.info("End of process - Cadastro Mongo");
	}
	

}
