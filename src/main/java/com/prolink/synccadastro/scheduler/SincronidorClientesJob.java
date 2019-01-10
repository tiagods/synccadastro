package com.prolink.synccadastro.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prolink.synccadastro.services.ClientesServices;

@Component
public class SincronidorClientesJob implements Job{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ClientesServices clientesJob;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
		clientesJob.iniciarAtualizacao();
        logger.info("Next job scheduled @ {}", context.getNextFireTime());
	}

}
