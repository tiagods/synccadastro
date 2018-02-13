package com.prolink.sync.job;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.prolink.sync.model.AniversarianteDao;
import com.prolink.sync.model.CadastroBean;
import com.prolink.sync.model.CadastroDao;
import com.prolink.sync.model.ComentarioBean;
import com.prolink.sync.model.ComentarioDao;
import com.prolink.sync.model.ConfExtraBean;
import com.prolink.sync.model.ConfExtraDao;
import com.prolink.sync.model.DarfsDao;
import com.prolink.sync.model.LembreteProLaboreDao;
import com.prolink.sync.model.Status;
import com.prolink.sync.model.StatusDao;

public class MyJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ConfExtraDao cextraDao = new ConfExtraDao();
		ConfExtraBean cextraB = cextraDao.readConfigurations();
		CadastroDao cadastro = CadastroDao.getInstance();
		synchronized (cadastro) {
			StringBuilder builder = new StringBuilder();
			String quebra = System.getProperty("line.separator");
			if(cadastro.validateExtension(cextraB)){
				String workbook =cextraB.getDIRETORIO_TEMP()+"\\"+cextraB.getPLANILHA_NOME(); 
				List<CadastroBean> lista = null;
				boolean gerarTxt = false;
				try{
					lista = cadastro.readWorkbook(workbook);
					builder.append("Leitura realizada em : "+cadastro.getTempoLeitura()+" ms");
					builder.append(quebra);
				}catch(IOException e){
					gerarTxt = true;
					builder.append("Falha ao ler o arquivo temporario xls: "+e.getMessage());
					builder.append(quebra);
				}
				long inicio = System.currentTimeMillis();
				builder.append(cadastro.insertOrUpdate(lista));
				long fim = System.currentTimeMillis();
				
				List<ComentarioBean> comentarios = cadastro.getComentarios();
				builder.append(new ComentarioDao().insertOrUpdate(comentarios));
				
				String erros = cadastro.getErros();
				if(erros.length()>10){
					gerarTxt=true;
					erros = "Foi gerado um arquivo txt com os dados nao salvos";
				}
				Status bean = new Status(new Date(), cadastro.getTempoGravacao(), cadastro.getTempoLeitura(), erros);
				StatusDao statusDao = new StatusDao();
				statusDao.salvar(bean);

				Calendar calendar = Calendar.getInstance();
				if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY && calendar.get(Calendar.HOUR_OF_DAY)<13 &&
						!new File(cextraB.getDIRETORIO_TEMP()+"/envio"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt").exists()){
					new AniversarianteDao().processarEnviarAniversariantes(calendar, cextraB);
				}
				if(calendar.get(Calendar.DAY_OF_MONTH)==2 && calendar.get(Calendar.HOUR_OF_DAY)<13 && 
						!new File(cextraB.getDIRETORIO_TEMP()+"/email"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt").exists()) {
					String[] idClientes = new String[] {"2361","2409"};
					for(String cliente : idClientes) {
						LembreteProLaboreDao lemb = new LembreteProLaboreDao();
						lemb.enviarLembrete(new String[] {"jose.ferreira@prolinkcontabil.com.br",
								"fernando.fonseca@prolinkcontabil.com.br",
								"karin.fernandes@prolinkcontabil.com.br"}, 
								new String[] {"viviane.favero@sequenza.com.br"}
								,cliente, cextraB);
					}
				}
				builder.append("Processo concluido em : "+(fim-inicio)+" ms");
				if(gerarTxt){
					try{
						File file = new File(cextraB.getDIRETORIO_TEMP());
						cadastro.createTxtLogFile(file, builder);
					}catch(IOException e){
						System.out.println("Falha ao gravar o arquivo txt: "+e.getMessage());;
					}
				}
				LocalDateTime dateNow = LocalDateTime.now();
				if(dateNow.getHour()>17 && dateNow.getDayOfMonth() <= 20){
					LocalDateTime localDate2 = dateNow.plusMonths(-1);
					LocalDateTime localDate3 = dateNow.plusMonths(-2);
					DarfsDao darfs = new DarfsDao();
					darfs.iniciar(localDate2);			
					darfs.iniciar(localDate3);
				}
			}
		}
	}
	
}