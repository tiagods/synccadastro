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
	public void initialize(String agendamento){

		try {
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
//                .withSchedule(CronScheduleBuilder.cronSchedule("0 01 11 ? * MON,TUE,WED,THU,FRI,SAT"))
                .build();
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            System.out.println("erro");
            e.printStackTrace();
        }
	}
	public void stopJob(){
		 try {
			sched.deleteJob(job.getKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	public String rescueSchedulingBD(){
		ConfigDao configDao = new ConfigDao();
		System.out.println("Lendo configurações de trabalho");
		ConfigBean cb = configDao.readConfigurations();
		System.out.println("Leitura realizada, tranferindo valores");
		return cb.getSEGUNDO()+" "+cb.getMINUTO()+" "+cb.getHORA()+" "+
				cb.getDIA_DO_MES()+" "+cb.getMES()+" "+cb.getDIA_DA_SEMANA();
	}
	public void startJob(){
		String scheduling = rescueSchedulingBD();
		initialize(scheduling);
	}
	public void restartJob(){
		String scheduling = rescueSchedulingBD();
		
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
	

}
