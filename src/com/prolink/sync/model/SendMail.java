package com.prolink.sync.model;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

public class SendMail {
	
	private String errorMessage="";
	
    public boolean enviaAlerta(String[] conta, String[] copias, String titulo, String mensagem, EmailAttachment atach){
    HtmlEmail email = new HtmlEmail();
    email.setHostName( "email-ssl.com.br" );
    email.setSmtpPort(587);
    email.setAuthenticator( new DefaultAuthenticator( "alertas@prolinkcontabil.com.br" ,  "Js5TRgKtkARm" ) );
    try {
        email.setFrom( "alertas@prolinkcontabil.com.br" , "Alertas \\ Prolink Contabil");
        email.setSubject( titulo );
        email.setHtmlMsg( mensagem );
        for(String s : conta) email.addTo(s);
        for(String c : copias) email.addCc(c);
        if(atach!=null) email.attach(atach);
        email.send();
        return true;
    } catch (Exception e) {
    	errorMessage =e.getMessage();
    	e.printStackTrace();
        return false;
    } 
    }
    public String getErroMessage(){
    	return this.errorMessage;
    }

}
