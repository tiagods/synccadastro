package job;

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
		if(cadastro.validateExtension(cextraB)){
			
		}
		
		
	}

}
