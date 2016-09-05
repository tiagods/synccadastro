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
<<<<<<< HEAD:src/controller/ControllerJob.java
	//inicializaÁ„o do agendamento
=======
//inicializando agendamento
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
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
<<<<<<< HEAD:src/controller/ControllerJob.java
	//deletendo job
=======
//removendo tarefa
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
	public void stopJob(){
		 try {
			sched.deleteJob(job.getKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD:src/controller/ControllerJob.java
	//recuperando job do banco de dados
	private String rescueSchedulingBD(){
=======
//pegando configuracao do banco de dados
	public String rescueSchedulingBD(){
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
		ConfigDao configDao = new ConfigDao();
		System.out.println("Lendo configura√ß√µes de trabalho");
		ConfigBean cb = configDao.readConfigurations();
		if(cb!=null){
<<<<<<< HEAD:src/controller/ControllerJob.java
			System.out.println("Leitura realizada, tranferindo valores + ");
=======
			System.out.println(cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
					cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA());
			
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
			return cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
					cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA();
		}
		else
			return null;
	}
<<<<<<< HEAD:src/controller/ControllerJob.java
	//startando serviÁo
=======
//iniciando job
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
	public void startJob(){
		String scheduling = rescueSchedulingBD();
		if(scheduling!=null)
			initialize(scheduling);
		else
			System.out.println("N√£o foi possivel receber os paramentros de trabalho!");
	}
<<<<<<< HEAD:src/controller/ControllerJob.java
	//restartando serviÁo
=======
//reiniciando job
>>>>>>> refs/remotes/origin/master:src/controller/ControllerCadastro.java
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
			System.out.println("N√£o foi possivel receber os paramentros de trabalho!");
	}
	

}
