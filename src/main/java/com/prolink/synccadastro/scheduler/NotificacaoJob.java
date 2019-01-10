package com.prolink.synccadastro.scheduler;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.prolink.synccadastro.model.NotificacaoEnvio;
import com.prolink.synccadastro.services.NotificacaoService;


@Component
public class NotificacaoJob implements Job{
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private NotificacaoService notificacao;
	@Autowired 
	private JavaMailSender mailSender;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
		
		notificacao.analisar();
	    List<NotificacaoEnvio> ne = notificacao.pendentes();
	    for(NotificacaoEnvio n : ne) {
	    	try {
				if(notificacao.verificarEnvio(n)) continue;
				MimeMessage mail = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setTo(n.getNotificacao().getPara().replace(" ", "").split(";"));
				if(!n.getNotificacao().getCc().trim().equals(""))
					helper.setCc(n.getNotificacao().getCc().replace(" ", "").split(";"));
				if(!n.getNotificacao().getCo().trim().equals(""))
					helper.setBcc(n.getNotificacao().getCo().replace(" ", "").split(";"));
				helper.setSubject(n.getNotificacao().getAssunto());
				helper.setText(n.getNotificacao().getModelo().getTexto(),true);
				helper.setFrom(n.getNotificacao().getDe(),n.getNotificacao().getAutor());
				mailSender.send(mail);
				n.setDataEnvio(Calendar.getInstance());
				n.setStatus(true);
				notificacao.salvar(n);
				logger.debug("Send ok - "+n.getId()+" - "+LocalDateTime.now());
			} catch (NullPointerException | MailException | MessagingException | UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
	    }
	    
		logger.info("Next job scheduled @ {}", context.getNextFireTime());
	}
}
