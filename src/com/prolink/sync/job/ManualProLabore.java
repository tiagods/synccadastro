package com.prolink.sync.job;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.prolink.sync.model.ConfExtraBean;
import com.prolink.sync.model.ConfExtraDao;
import com.prolink.sync.model.LembreteProLaboreDao;

public class ManualProLabore {

	public static void main(String[] args) {
		//aviso do prolabore mensal
		ConfExtraBean cextraB = new ConfExtraBean();
		cextraB.setDIRETORIO_TEMP("c:\\TEMP");
		LocalDateTime dateNow = LocalDateTime.now();
		if(dateNow.getDayOfMonth()==2 && 
				!new File(cextraB.getDIRETORIO_TEMP()+"/prolabore"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt").exists()) {
			String[] idClientes = new String[] {"2361","2409"};
			for(String cliente : idClientes) {
				LembreteProLaboreDao lemb = new LembreteProLaboreDao();
				lemb.enviarLembrete(
						new String[] {"jose.ferreira@prolinkcontabil.com.br","fernando.fonseca@prolinkcontabil.com.br","karin.fernandes@prolinkcontabil.com.br"}, 
						new String[] {"viviane.favero@sequenza.com.br","marcileia.ferreira@prolinkcontabil.com.br"}
						,cliente, cextraB);
			}
		}
	}

}
