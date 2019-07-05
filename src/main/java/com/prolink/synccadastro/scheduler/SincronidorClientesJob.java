package com.prolink.synccadastro.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prolink.synccadastro.services.ClientesServices;

@Component
@PropertySource("classpath:arquivos.properties")
public class SincronidorClientesJob{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClientesServices clientesJob;
	
	@Scheduled(cron = "${agendamento.planilha}")
	public void execute(){
		logger.info("Start Job - Cadastro");
		clientesJob.iniciarAtualizacao();
        logger.info("End of process - Cadastro");
	}

}
