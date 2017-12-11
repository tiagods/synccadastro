package com.prolink.sync.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.prolink.sync.job.MyJob;
import com.prolink.sync.model.ConfigBean;
import com.prolink.sync.model.ConfigDao;

public class ControllerJob {
	private SchedulerFactory schedFact;
	private Scheduler sched;
	private JobDetail job;
	private Trigger trigger;
	public static ControllerJob instance;
	
	public static ControllerJob getInstance(){
		if(instance==null){
			instance=new ControllerJob();
		}
		return instance;
	}
//inicializando agendamento
	public void initialize(String agendamento){
		try {
			System.out.println("Agendamento criado");
            schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myjob", "grupo1")
                .build();
            trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("meugatilho", "grupo1")
                .withSchedule(CronScheduleBuilder.cronSchedule(agendamento))
                .build();
            sched.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
            System.out.println("erro");
            e.printStackTrace();
        }
	}
//removendo tarefa
	public void stopJob(){
		 try {
			sched.deleteJob(job.getKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
//pegando configuracao do banco de dados
	public String rescueSchedulingBD(){
		ConfigDao configDao = new ConfigDao();
		ConfigBean cb = configDao.readConfigurations();
		if(cb!=null){
			return cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
					cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA();
		}
		else
			return null;
	}
	//startando serviço
	public void startJob(){
		String scheduling = rescueSchedulingBD();
		if(scheduling!=null)
			initialize(scheduling);
		else
			System.out.println("Nao foi possivel receber os paramentros de trabalho!");
	}
	//restartando serviço
	public void restartJob(){
		String scheduling = rescueSchedulingBD();
		if(scheduling != null){
			trigger = TriggerBuilder.newTrigger()
	            	.withIdentity("meugatilho", "grupo1")
	 	            .withSchedule(CronScheduleBuilder.cronSchedule(scheduling))
	 	            .build();
			
			try {
				sched.scheduleJob(job,trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}    
		}
		else
			System.out.println("Nao foi possivel receber os paramentros de trabalho!");
	}
	

}
