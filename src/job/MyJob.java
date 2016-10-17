package job;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.CadastroBean;
import model.CadastroDao;
import model.ConfExtraBean;
import model.ConfExtraDao;
import model.Status;
import model.StatusDao;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ConfExtraDao cextraDao = new ConfExtraDao();
		ConfExtraBean cextraB = cextraDao.readConfigurations();
		
		CadastroDao cadastro = new CadastroDao();
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
				Status bean = new Status(new Date(), cadastro.getTempoGravacao(), cadastro.getTempoLeitura(), cadastro.getErros());
				StatusDao statusDao = new StatusDao();
				statusDao.salvar(bean);
				
				builder.append("Processo concluido em : "+(fim-inicio)+" ms");
				if(gerarTxt){
					try{
					File file = new File(cextraB.getPLANILHA_LOCALIZACAO()+"/logs");
					if(!file.exists())
						file.mkdir();
						cadastro.createTxtLogFile(file, builder);
					}catch(IOException e){
						System.out.println("Falha ao gravar o arquivo txt: "+e.getMessage());;
					}
				}
			}
			}
	}
}