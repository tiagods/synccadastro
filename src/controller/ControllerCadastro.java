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

public class ControllerCadastro {
	private SchedulerFactory schedFact;
	private JobDetail job;
	public static ControllerCadastro instance;
	
	public static ControllerCadastro getInstance(){
		if(instance==null){
			instance=new ControllerCadastro();
		}
		return instance;
	}
	public void iniciarProcesso(){
		//CadastroDao cd = new CadastroDao();
		//List<CadastroBean>listaCadastro= cd.lerCadastro();
        try {
            schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            
            job = JobBuilder.newJob(MyJob.class)
                .withIdentity("myjob", "grupo1")
                .build();
            
            Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("meugatilho", "grupo1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 01 11 ? * MON,TUE,WED,THU,FRI,SAT"))
                .build();
            sched.scheduleJob(job, trigger);
             
            sched.deleteJob(job.getKey());
            trigger = TriggerBuilder.newTrigger()
            	.withIdentity("meugatilho", "grupo1")
 	            .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
 	            .build();
            sched.scheduleJob(job,trigger);
            
        } catch (SchedulerException e) {
            System.out.println("erro");
            e.printStackTrace();
        }
	}
	public void pararProcesso(){
		//
	}
	public void pegarAgendamentoBD(){
		//Agenda agenda = new Agenda();
		//iniciarProcesso(agenda);
	}
	

}
