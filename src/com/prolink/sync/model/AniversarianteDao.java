package com.prolink.sync.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.mail.EmailAttachment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.prolink.sync.factory.HibernateFactory;

import jxl.write.WriteException;

public class AniversarianteDao {
	static AniversarianteDao instance;
	
	LocalDate segunda=null;
	LocalDate terca=null;
	LocalDate quarta=null;
	LocalDate quinta=null;
	LocalDate sexta=null;
	LocalDate sabado=null;
	LocalDate domingo=null;
	
	public static AniversarianteDao getInstance(){
		if(instance==null)
			instance = new AniversarianteDao();
		return instance;
	}
	@SuppressWarnings("unchecked")
	public List<Aniversariante> receberAniversariantes(){
		HibernateFactory factory = new HibernateFactory();
		Session session = factory.getSession();
		List<Aniversariante> aniversariantes = new ArrayList<>();
		List<LocalDate> lista = new ArrayList<>();
		LocalDate hoje = LocalDate.now();
		
		tratarDias(hoje);
		
		lista.add(segunda);
		lista.add(terca);
		lista.add(quarta);
		lista.add(quinta);
		lista.add(sexta);
		lista.add(sabado);
		lista.add(domingo);
		String[] a = new String[]{"PLATINA","PRATA 2","OURO 3","OURO 2","PRATA 3","OURO 1","BRONZE","PRATA 1","Exceï¿½ï¿½o","Inativa","Em andamento"};
		List<String> filtroStatus = new ArrayList<String>();
		for(String t : a)
			filtroStatus.add(t);
		for(LocalDate d : lista){
	/*		String hql = "FROM CadastroBean as c where c.DIA_NASC1 "
	 *				+ "= :dayOfMounth "
	 *				+ "and c.MES_NASC1 = :mounth";
	 *
	 * 		List<CadastroBean> object = (List<CadastroBean>)session.createQuery(hql)
	 *
	 *				.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
	 *				.setParameter("mounth", convertMounth(d.getMonthValue()).toLowerCase())
	 *				.getResultList();
	 */	
			Criteria criteria = session.createCriteria(CadastroBean.class);
			criteria.add(Restrictions.eq("DIA_NASC1", String.valueOf(d.getDayOfMonth())));
			criteria.add(Restrictions.eq("MES_NASC1", convertMounth(d.getMonthValue()).toLowerCase()));
			criteria.add(Restrictions.in("STATUS", filtroStatus));
			List<CadastroBean> object = (List<CadastroBean>)criteria.list();
			if(!object.isEmpty()){
				object.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOCIO1());
					aniversariante.setAniversario(c.getDIA_NASC1()+"/"+convertMounth(c.getMES_NASC1()));
					aniversariante.setEmail(c.getEMAIL_SOC_1());
					String listaFones = "";
					if(c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1CEL()+" "+c.getCELULAR();
					else if(!c.getFONECOML1().trim().equals("") && c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1COML()+" "+c.getFONECOML1();
					else if(!c.getFONECOML1().trim().equals("") && !c.getCELULAR().trim().equals(""))
						listaFones = c.getDDD1COML()+" "+c.getFONECOML1()+","+c.getDDD1CEL()+" "+c.getCELULAR();
					aniversariante.setTelefone(listaFones);
					aniversariante.setTipoSocio(1);
					aniversariantes.add(aniversariante);
				});
			} 
			session.clear();
//			hql = "FROM CadastroBean as c where c.DIA_NASC2 "
//					+ "= :dayOfMounth "
//					+ "and c.MES_NASC2 = :mounth";
//			List<CadastroBean> object2 = (List<CadastroBean>)session.createQuery(hql)
//					.setParameter("dayOfMounth", String.valueOf(d.getDayOfMonth()))
//					.setParameter("mounth", convertMounth(d.getMonthValue()).toLowerCase())
//					.getResultList();
			criteria = session.createCriteria(CadastroBean.class);
			criteria.add(Restrictions.eq("DIA_NASC2", String.valueOf(d.getDayOfMonth())));
			criteria.add(Restrictions.eq("MES_NASC2", convertMounth(d.getMonthValue()).toLowerCase()));
			criteria.add(Restrictions.in("STATUS", filtroStatus));
			List<CadastroBean> object2 = (List<CadastroBean>)criteria.list();
			if(!object2.isEmpty()){
				object2.forEach(c->{
					Aniversariante aniversariante = new Aniversariante();
					aniversariante.setId(String.valueOf(c.getCOD()));
					aniversariante.setStatus(c.getSTATUS());
					aniversariante.setEmpresa(c.getEMPRESA());
					aniversariante.setNome(c.getNOME_SOC_2());
					aniversariante.setAniversario(c.getDIA_NASC2()+"/"+convertMounth(c.getMES_NASC2()));
					aniversariante.setEmail(c.getEMAIL_SOC_1());
					aniversariante.setTipoSocio(2);
					String listaFones2 = "";
					if(c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2CEL()+" "+c.getCELULAR2();
					else if(!c.getFONECOM2().trim().equals("") && c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2COML()+" "+c.getFONECOM2();
					else if(!c.getFONECOM2().trim().equals("") && !c.getCELULAR2().trim().equals(""))
						listaFones2 = c.getDDD2COML()+" "+c.getFONECOM2()+","+c.getDDD2CEL()+" "+c.getCELULAR2();
					aniversariante.setTelefone(listaFones2);
					aniversariantes.add(aniversariante);
				});
			}
			session.clear();
		}
		factory.closeSession(session);
		return aniversariantes;
	}
	//use localdate para o metodo conversor, se for usar calendar (0 - Janeiro a 11 - Dezembro) soma calendar.get(Calendar.Month)+1
	public String convertMounth(int mes){
		switch(mes){
		case 1:
			return "Janeiro";
		case 2:
			return "Fevereiro";
		case 3:
			return "Marï¿½o";
		case 4:
			return "Abril";
		case 5:
			return "Maio";
		case 6:
			return "Junho";
		case 7:
			return "Julho";
		case 8:
			return "Agosto";
		case 9:
			return "Setembro";
		case 10:
			return "Outubro";
		case 11:
			return "Novembro";
		case 12:
			return "Dezembro";
		}
		return "";
	}
	public String convertMounth(String mes){
		switch(mes.toUpperCase()){
		case "JANEIRO":
			return "01";
		case "FEVEREIRO":
			return "02";
		case "MARï¿½O":
			return "03";
		case "ABRIL":
			return "04";
		case "MAIO":
			return "05";
		case "JUNHO":
			return "06";
		case "JULHO":
			return "07";
		case "AGOSTO":
			return "08";
		case "SETEMBRO":
			return "09";
		case "OUTUBRO":
			return "10";
		case "NOVEMBRO":
			return "11";
		case "DEZEMBRO":
			return "12";
		}
		return "";
	}
	public File gerarExcel(Iterator<Aniversariante> iterator, ConfExtraBean cextraB){
		ArrayList<ArrayList<String>> listaImpressao = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		SimpleDateFormat sdn = new SimpleDateFormat("ddMMyyyy");
		Integer[] colunasLenght = new Integer[]{10,40,10,30,30,18,30,15};
		String[] cabecalho = new String[]{
				"DIA","NOME","SOCIO","TELEFONE","E-MAIL","ID_CLIENTE",
				"NOME_CLIENTE","STATUS"};
		listaImpressao.add(new ArrayList<>());
		for(String c : cabecalho){
			listaImpressao.get(0).add(c);
		}
		int i=1;
		while(iterator.hasNext()){
			Aniversariante an = iterator.next();
			listaImpressao.add(new ArrayList<>());
			listaImpressao.get(i).add(an.getAniversario());
			listaImpressao.get(i).add(an.getNome());
			listaImpressao.get(i).add(""+an.getTipoSocio());
			listaImpressao.get(i).add(an.getTelefone());
			listaImpressao.get(i).add(an.getEmail());
			listaImpressao.get(i).add(an.getId());
			listaImpressao.get(i).add(an.getEmpresa());
			listaImpressao.get(i).add(an.getStatus());
			i++;
		}
		String arquivoGerado=cextraB.getDIRETORIO_TEMP()+"/lista"+sdf.format(new Date())+".xls";
		ExcelGenerico planilha = new ExcelGenerico(arquivoGerado,listaImpressao,colunasLenght);
		try {
			planilha.gerarExcel();
			FileWriter fw = new FileWriter(new File(cextraB.getDIRETORIO_TEMP()+"/envio"+sdn.format(new Date())+".txt"));
			fw.write("a lista de aniversariantes foi enviada");
			fw.flush();
			fw.close();
			return new File(arquivoGerado);
		} catch (WriteException e1) {
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}
	public void processarEnviarAniversariantes(Calendar calendar, ConfExtraBean cextraB){
		List<Aniversariante> aniversariantes = receberAniversariantes();
		File file = gerarExcel(aniversariantes.iterator(), cextraB);
		if(file!=null){
	        EmailAttachment attachment = new EmailAttachment();
	        attachment.setPath(file.getAbsolutePath());
	        attachment.setDisposition(EmailAttachment.ATTACHMENT);
	        attachment.setDescription("Planilha de Aniversariantes");
	        attachment.setName("Aniversariantes da Semana.xls");
	        SendMail email = new SendMail();
	        LocalDate localDate = LocalDate.now();
	        String mes = convertMounth(localDate.getMonthValue());
	        String assunto = " Aniversariantes em "+mes+" - "+calendar.get(Calendar.WEEK_OF_MONTH)+"ª semana";
	        String[] contas = new String[]{"isabelle.souza@prolinkcontabil.com.br","monary.torres@prolinkcontabil.com.br",
	        		"victor.santos@prolinkcontabil.com.br","tiago.dias@prolinkcontabil.com.br"};
	        //String[] contas = new String[] {"tiago.dias@prolinkcontabil.com.br"};
	        if(email.enviaAlerta(contas, assunto,
	        		emailAniversariante(aniversariantes), attachment))
	        	file.delete();
		}
	}
	public void tratarDias(LocalDate hoje){
		switch(hoje.getDayOfWeek()){
		case MONDAY:
			segunda = hoje;
			terca = hoje.plusDays(1);
			quarta = hoje.plusDays(2);
			quinta = hoje.plusDays(3);
			sexta = hoje.plusDays(4);
			sabado = hoje.plusDays(5);
			domingo = hoje.plusDays(6);
			break;
		case TUESDAY:
			segunda = hoje.plusDays(-1);
			terca = hoje;
			quarta = hoje.plusDays(1);
			quinta = hoje.plusDays(2);
			sexta = hoje.plusDays(3);
			sabado = hoje.plusDays(4);
			domingo = hoje.plusDays(5);
			break;
		case WEDNESDAY:
			segunda = hoje.plusDays(-2);
			terca = hoje.plusDays(-1);
			quarta = hoje;
			quinta = hoje.plusDays(1);
			sexta = hoje.plusDays(2);
			sabado = hoje.plusDays(3);
			domingo = hoje.plusDays(4);
			break;
		case THURSDAY:
			segunda = hoje.plusDays(-3);
			terca = hoje.plusDays(-2);
			quarta = hoje.plusDays(-1);
			quinta = hoje;
			sexta = hoje.plusDays(1);
			sabado = hoje.plusDays(2);
			domingo = hoje.plusDays(3);
			break;
		case FRIDAY:
			segunda = hoje.plusDays(-4);
			terca = hoje.plusDays(-3);
			quarta = hoje.plusDays(-2);
			quinta = hoje.plusDays(-1);
			sexta = hoje;
			sabado = hoje.plusDays(1);
			domingo = hoje.plusDays(2);
			break;
		case SATURDAY:
			segunda = hoje.plusDays(-5);
			terca = hoje.plusDays(-4);
			quarta = hoje.plusDays(-3);
			quinta = hoje.plusDays(-2);
			sexta = hoje.plusDays(-1);
			sabado = hoje;
			domingo = hoje.plusDays(1);
			break;
		case SUNDAY:
			segunda = hoje.plusDays(-6);
			terca = hoje.plusDays(-5);
			quarta = hoje.plusDays(-4);
			quinta = hoje.plusDays(-3);
			sexta = hoje.plusDays(-2);
			sabado = hoje.plusDays(-1);
			domingo = hoje;
			break;
			default:
				break;
	}		
	}
	public String emailAniversariante(List<Aniversariante> lista){
		StringBuilder builder = new StringBuilder();

		builder.append("<!DOCTYPE html>");
		builder.append("<html lang=\"pt-br\">");
		builder.append("	<head>");
		builder.append("		<title>Aniversariantes</title>");
		builder.append("		<meta charset=\"UTF-8\">");
		builder.append("		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		builder.append("		<!-- Bootstrap CSS -->");
		builder.append("		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">");
		builder.append("		<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js\" integrity=\"sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1\" crossorigin=\"anonymous\"></script>");
		builder.append("		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->");
		builder.append("		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->");
		builder.append("		<!--[if lt IE 9]>");
		builder.append("		  <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>");
		builder.append("		  <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>");
		builder.append("		<![endif]-->");
		builder.append("		<style type=\"text/css\">");
		builder.append("			.paragrafo{");
		builder.append("				font-size: 20px;");
		builder.append("				font-weight: bolder;");
		builder.append("			}");
		builder.append("		</style>");
		builder.append("	</head>");
		builder.append("	<body>");
		builder.append("		<div class=\"container\">	");
		builder.append("			<div class=\"page-header\">");
		builder.append("				<p class=\"paragrafo\">Ol&aacute;,<br>");
		builder.append("				Segue rela&ccedil;&atilde;o de aniversariantes da semana!</p>");
		builder.append("			</div>");
		builder.append("			<table class=\"table table-striped table-bordered table-hover table-condensed table-responsive table-sm\">");
		builder.append("				<thead>");
		builder.append("					<tr class=\"table-danger\">");
		builder.append("						<th>Data</th>");
		builder.append("						<th>Nome</th>");
		builder.append("						<th>S&oacute;cio</th>");
		builder.append("						<th>Telefone</th>");
		builder.append("						<th>E-mail</th>");
		builder.append("						<th>ID</th>");
		builder.append("						<th>Cliente</th>");
		builder.append("						<th>Status");
		builder.append("					</th>");
		builder.append("					</tr>");
		builder.append("				</thead>");
		builder.append("				<tbody>");
			
		for(int i = 0; i < lista.size(); i++) {
			String trClass = "table-success";
			Aniversariante an = lista.get(i);
			String email = an.getEmail().replace(";", ";<br>");
		    if(i%2==1) {
		    	trClass = "table-info";
			}
			builder.append("<tr class=\"").append(trClass).append("\">");
			
			StringBuilder token = new StringBuilder();
			token.append("						<td>");
			token.append("							<span>")
			.append("{token}")
			.append("									</span>");
			token.append("						</td>");
			
			builder.append(token.toString().replace("{token}", an.getAniversario()));
			builder.append(token.toString().replace("{token}", an.getNome()));
			builder.append(token.toString().replace("{token}", String.valueOf(an.getTipoSocio())));
			builder.append(token.toString().replace("{token}", an.getTelefone()));
			builder.append(token.toString().replace("{token}", email));
			builder.append(token.toString().replace("{token}", an.getId()));
			builder.append(token.toString().replace("{token}", an.getEmpresa()));
			builder.append(token.toString().replace("{token}", an.getStatus()));
			
			builder.append("					</tr>");
			
		}
		builder.append("</tbody>");
		builder.append("			</table>");
		builder.append("			<div class=\"row align-s-center\">");
		builder.append("				<div class=\"col-12\">");
		builder.append("					<p align=\"center\">Todos os direitos reservados a Prolink Contabil - 2017</p>");
		builder.append("				</div>");
		builder.append("			</div>");
		builder.append("		</div>");
		builder.append("		");
		builder.append("	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->");
		builder.append("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		builder.append("	</body>");
		builder.append("</html>");
		return builder.toString();
	}
}