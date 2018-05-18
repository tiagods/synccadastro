package com.prolink.sync.job;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import com.prolink.sync.model.ConfExtraBean;
import com.prolink.sync.model.SendMail;

public class ManualContabil {
	private static String mensagem1=
			"Solicito por gentileza o envio dos extratos banc&aacute;rios de todas as contas correntes da empresa e de todas as aplica&ccedil;&otilde;es financeiras referente ao fechamento do m&ecirc;s anterior, para que possamos dar sequ&ecirc;ncia ao fechamento do Balancete.";
	private static String mensagem2="Solicito por gentileza o envio dos extratos banc&aacute;rios consolidados de conta corrente&nbsp; referente ao fechamento do m&ecirc;s anterior, para que possamos dar sequ&ecirc;ncia ao fechamento do Balancete.";
	private static String mensagem3="Solicito por gentileza o envio dos extratos banc&aacute;rios de conta corrente e aplica&ccedil;&otilde;es financeiras referente ao fechamento do m&ecirc;s anterior, para que possamos dar sequ&ecirc;ncia ao fechamento do Balancete.";
	private static String mensagem4="Solicito por gentileza o envio dos extratos banc&aacute;rios consolidados de conta corrente&nbsp; e aplica&ccedil;&otilde;es financeiras da empresa, e tamb&eacute;m as informa&ccedil;&otilde;es financeiras (notas fiscais, contas pagas e demais despesas) referente ao fechamento do m&ecirc;s anterior, para que possamos dar sequ&ecirc;ncia ao fechamento do Balancete.";
	public static void main(String[] args) {
		new ManualContabil().iniciar();
	}
	private void iniciar() {
		Set<ClienteComunicado> cli = new HashSet<>();
		ClienteComunicado cli1 = new ClienteComunicado();
		cli1.setDia(10);
		cli1.setId("1745");
		cli1.setEmail(new String[] {"financeiro@masterycon.com.br"});
		cli1.setMensagem(mensagem1);
		cli.add(cli1);
		
		ClienteComunicado cli2 = new ClienteComunicado();
		cli2.setDia(10);
		cli2.setId("2218");
		cli2.setEmail(new String[] {"gracaomendes@hotmail.com"});
		cli2.setMensagem(mensagem1);
		cli.add(cli2);
		
		ClienteComunicado cli3 = new ClienteComunicado();
		cli3.setDia(15);
		cli3.setId("2286");
		cli3.setEmail(new String[] {"marta.maioli@talentfour.com.br"});
		cli3.setMensagem(mensagem2);
		cli.add(cli3);
		
		ClienteComunicado cli4 = new ClienteComunicado();
		cli4.setDia(15);
		cli4.setId("2361");
		cli4.setEmail(new String[] {"viviane.favero@sequenza.com.br"});
		cli4.setMensagem(mensagem3);
		cli.add(cli4);
		
		ClienteComunicado cli5 = new ClienteComunicado();
		cli5.setDia(15);
		cli5.setId("2409");
		cli5.setEmail(new String[] {"viviane.favero@sequenza.com.br"});
		cli5.setMensagem(mensagem3);
		cli.add(cli5);
		
		ClienteComunicado cli6 = new ClienteComunicado();
		cli6.setDia(10);
		cli6.setId("2204");
		cli6.setEmail(new String[] {"nacoesunidas@mundoverde.com.br"});
		cli6.setMensagem(mensagem3);
		cli.add(cli6);
		
		ClienteComunicado cli7 = new ClienteComunicado();
		cli7.setDia(15);
		cli7.setId("2431");
		cli7.setEmail(new String[] {"felipe.guimaraes@rhhandson.com.br"});
		cli7.setMensagem(mensagem4);
		cli.add(cli7);
		
		ClienteComunicado cli8 = new ClienteComunicado();
		cli8.setDia(15);
		cli8.setId("2244");
		cli8.setEmail(new String[] {
				"mhlsanti@gmail.com",
				"brunosantimv@gmail.com",
				"andraewoomv@gmail.com"});
		cli8.setMensagem(mensagem3);
		cli.add(cli8);
		
		ConfExtraBean cextraB = new ConfExtraBean();
		cextraB.setDIRETORIO_TEMP("c:\\TEMP");
		
		LocalDateTime dateNow = LocalDateTime.now();
		if (!new File(cextraB.getDIRETORIO_TEMP() + "/extratos"
				+ new SimpleDateFormat("ddMMyyyy").format(new Date()) + ".txt").exists()) {
			Stream<ClienteComunicado> stream = cli.stream().filter(c->c.getDia()==dateNow.getDayOfMonth());
			stream.forEach(c->{
				enviarLembrete(c.getEmail(), c.getEmailCopia(), c.getId(), cextraB, c.getMensagem());
				//enviarLembrete(new String[] {"tiago.dias@prolinkcontabil.com.br"}, new String[] {"tiago.dias@prolinkcontabil.com.br"}, c.getId(), cextraB, c.getMensagem());
				
			});
			try {
				File f = new File(cextraB.getDIRETORIO_TEMP()+"/extratos"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt");
				f.createNewFile();
				FileWriter fileWriter = new FileWriter(f);
				fileWriter.write("Cobrança de extratos bancários!");
				fileWriter.flush();
				fileWriter.close();
			}catch(Exception e) {	
			}
		}
	}
	public static void enviarLembrete(String[] conta, String[] copias, String cliente, ConfExtraBean cb, String mensagem) {
		String saudacao="";
		LocalDateTime local = LocalDateTime.now();
		if(local.getHour()>=0 && local.getHour()<12) saudacao = "Bom dia,";
		else if(local.getHour()>=12 && local.getHour()<18) saudacao = "Boa tarde,";
		else saudacao = "Boa noite,";
		String message = mensagem(saudacao,mensagem);
		SendMail mail = new SendMail();
		mail.enviaAlerta(conta,copias,cliente+" - Envio de Extratos", message, null);
	}
	private static String mensagem(String saudacao,String mensagem) {
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
		sb.append(saudacao);
		sb.append("				</h3>");
		sb.append("			</div>");
		sb.append("			<h4>");
		sb.append(mensagem);
		sb.append("         </h4>");
		sb.append("			<div class=\"page-footer\">");
		sb.append("				<br />");
		sb.append("				<h4>");
		sb.append("					Caso j&aacute; tenha sido enviado anteriormente, por gentileza desconsiderar o e-mail.</h4>");
		sb.append("			</div>");
		sb.append("		</div>");
		sb.append("	</body>");
		sb.append("</html>");
		return sb.toString();
	}
	public class ClienteComunicado implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String id;
		private String mensagem;
		private String[] email;
		private int dia;
		private String[] emailCopia=new String[] {"marcileia.ferreira@prolinkcontabil.com.br"};
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}
		/**
		 * @return the mensagem
		 */
		public String getMensagem() {
			return mensagem;
		}
		/**
		 * @param mensagem the mensagem to set
		 */
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
		/**
		 * @return the email
		 */
		public String[] getEmail() {
			return email;
		}
		/**
		 * @param email the email to set
		 */
		public void setEmail(String[] email) {
			this.email = email;
		}
		/**
		 * @return the dia
		 */
		public int getDia() {
			return dia;
		}
		/**
		 * @param dia the dia to set
		 */
		public void setDia(int dia) {
			this.dia = dia;
		}
		/**
		 * @return the emailCopia
		 */
		public String[] getEmailCopia() {
			return emailCopia;
		}
		/**
		 * @param emailCopia the emailCopia to set
		 */
		public void setEmailCopia(String[] emailCopia) {
			this.emailCopia = emailCopia;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ClienteComunicado other = (ClienteComunicado) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		
		private ManualContabil getOuterType() {
			return ManualContabil.this;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ClienteComunicado [id=" + id + ", email=" + Arrays.toString(email) + ", dia=" + dia
					+ ", emailCopia=" + Arrays.toString(emailCopia) + "]";
		}
			
	}
}
