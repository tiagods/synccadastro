package com.prolink.sync.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class LembreteProLaboreDao {
	public void enviarLembrete(String[] conta, String[] copias, String cliente, ConfExtraBean cb) {
		String saudacao="";
		LocalDateTime local = LocalDateTime.now();
		if(local.getHour()>=0 && local.getHour()<12) saudacao = "Bom dia,";
		else if(local.getHour()>=12 && local.getHour()<18) saudacao = "Boa tarde,";
		else saudacao = "Boa noite,";
		
		String message = mensagem(saudacao);
		SendMail mail = new SendMail();
		if(mail.enviaAlerta(conta,copias,cliente+"-Solicita��o de Demonstrativos de Pr�-Labore", message, null)){
			try {
				File f = new File(cb.getDIRETORIO_TEMP()+"\\email"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt");
				f.createNewFile();
			}catch(Exception e) {}
		}
	}
	
	private String mensagem(String saudacao) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("	<head>");
		sb.append("		<title>Lembrete</title>");
		sb.append("		<link crossorigin=\"anonymous\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" rel=\"stylesheet\" />");
		sb.append("	</head>");
		sb.append("	<body>");
		sb.append("		<div class=\"container\">");
		sb.append("			<div class=\"page-header\">");
		sb.append("				<h3>");
		sb.append("Bom dia,");
		sb.append("				</h3>");
		sb.append("			</div>");
		sb.append("			<p>");
		sb.append("				Solicito o envio dos demonstrativos de Pr&oacute;-Labore do m&ecirc;s para a empresa acima citada.</p>");
		sb.append("			<div class=\"page-footer\">");
		sb.append("				<br />");
		sb.append("				<h4 style=\"text-align:center;\">");
		sb.append("					<span style=\"color:#a9a9a9;\">Essa mensagem &eacute; autom&aacute;tica, favor n&atilde;o responder</span></h4>");
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("	</body>");
		sb.append("</html>");
		return sb.toString();
	}
}