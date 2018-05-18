package com.prolink.sync.job;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.prolink.sync.model.ConfExtraBean;
import com.prolink.sync.model.LembreteProLaboreDao;
import com.prolink.sync.model.SendMail;

public class ManualIRAluguel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// aviso do prolabore mensal
		ConfExtraBean cextraB = new ConfExtraBean();
		cextraB.setDIRETORIO_TEMP("c:\\TEMP");
		LocalDateTime dateNow = LocalDateTime.now();
		if (dateNow.getDayOfMonth() == 2 && !new File(cextraB.getDIRETORIO_TEMP() + "/iraluguel"
				+ new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".txt").exists()) {
			Map<String,String[]> map = new HashMap<>();
//			map.put("2277", new String[] {"financeirocoibim@gmail.com","coibim.adm@gmail.com"});
//			map.put("2361", new String[] {"viviane.favero@sequenza.com.br"});
//			map.put("2409", new String[] {"viviane.favero@sequenza.com.br"});
//			map.put("2394", new String[] {"priueno@hotmail.com"});
//			map.put("633", new String[] {"takashi@abtcastro.com.br"});
//			map.put("2390", new String[] {"lucaszapparoli@gmail.com"});
			map.put("2211", new String[] {"otorrinomrc.auxiliar@gmail.com"});
			
			
			for(String s : map.keySet()) {
				enviarLembrete(map.get(s), new String[] {}, s, cextraB);
			}
		}
	}
	public static void enviarLembrete(String[] conta, String[] copias, String cliente, ConfExtraBean cb) {
		String saudacao="";
		LocalDateTime local = LocalDateTime.now();
		if(local.getHour()>=0 && local.getHour()<12) saudacao = "Bom dia,";
		else if(local.getHour()>=12 && local.getHour()<18) saudacao = "Boa tarde,";
		else saudacao = "Boa noite,";
		String message = mensagem(saudacao);
		SendMail mail = new SendMail();
		if(mail.enviaAlerta(conta,copias,cliente+" - IR aluguel ", message, null)){
			try {
				File f = new File(cb.getDIRETORIO_TEMP()+"/iraluguel"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt");
				f.createNewFile();
				FileWriter fileWriter = new FileWriter(f);
				fileWriter.write("E-mail IR Aluguel enviado com sucesso!");
				fileWriter.flush();
				fileWriter.close();
			}catch(Exception e) {
				
			}
		}
	}
	
	private static String mensagem(String saudacao) {
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
		sb.append("Boa tarde,");
		sb.append("				</h3>");
		sb.append("			</div>");
		sb.append("			<h4>Solicitamos que seja enviado o recibo de aluguel pago no m&ecirc;s anterior para que possamos gerar o Darf de IR.</h4>");
		sb.append("			<br /><h4>Caso j&aacute; tenha enviado por favor desconsiderar este e-mail.</h4>");
		sb.append("		</div>");
		sb.append("	</body>");
		sb.append("</html>");
		return sb.toString();
	}

}
