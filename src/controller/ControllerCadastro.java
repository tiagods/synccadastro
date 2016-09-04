package controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import job.MyJob;
import model.ConfigBean;
import model.ConfigDao;

public class ControllerCadastro {
	private SchedulerFactory schedFact;
	private Scheduler sched;
	private JobDetail job;
	private Trigger trigger;
	public static ControllerCadastro instance;
	
	public static ControllerCadastro getInstance(){
		if(instance==null){
			instance=new ControllerCadastro();
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
//               "0/20 * * * * ?"
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 01 11 ? * MON,TUE,WED,THU,FRI,SAT"))
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
		System.out.println("Lendo configurações de trabalho");
		ConfigBean cb = configDao.readConfigurations();
		if(cb!=null){
			System.out.println(cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
					cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA());
			
			return cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
					cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA();
		}
		else
			return null;
	}
//iniciando job
	public void startJob(){
		String scheduling = rescueSchedulingBD();
		if(scheduling!=null)
			initialize(scheduling);
		else
			System.out.println("Não foi possivel receber os paramentros de trabalho!");
	}
//reiniciando job
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
			System.out.println("Não foi possivel receber os paramentros de trabalho!");
	}
	

}
