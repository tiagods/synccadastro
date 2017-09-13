package job;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.AniversarianteDao;
import model.CadastroBean;
import model.CadastroDao;
import model.ComentarioBean;
import model.ComentarioDao;
import model.ConfExtraBean;
import model.ConfExtraDao;
import model.Status;
import model.StatusDao;

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
					erros = "Foi gerado um arquivo txt com os dados não salvos";
				}
				Status bean = new Status(new Date(), cadastro.getTempoGravacao(), cadastro.getTempoLeitura(), erros);
				StatusDao statusDao = new StatusDao();
				statusDao.salvar(bean);

				Calendar calendar = Calendar.getInstance();
				if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY && calendar.get(Calendar.HOUR_OF_DAY)<13 &&
						!new File(cextraB.getDIRETORIO_TEMP()+"/envio"+new SimpleDateFormat("ddMMyyyy").format(new Date())+".txt").exists()){
					new AniversarianteDao().processarEnviarAniversariantes(calendar, cextraB);
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
			}
		}
	}
	
}