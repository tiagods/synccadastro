package job;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.CadastroBean;
import model.CadastroDao;
import model.ConfExtraBean;
import model.ConfExtraDao;

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
				try{
					lista = cadastro.readWorkbook(workbook);
					builder.append("Leitura realizada em : "+cadastro.getTempo()+" ms");
					builder.append(quebra);
				}catch(IOException e){
					builder.append("Falha ao ler o arquivo temporario xls: "+e.getMessage());
					builder.append(quebra);
				}
				long inicio = System.currentTimeMillis();
				builder.append(cadastro.insertOrUpdate(lista));
				long fim = System.currentTimeMillis();
				builder.append("Processo concluido em : "+(fim-inicio)+" ms");
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