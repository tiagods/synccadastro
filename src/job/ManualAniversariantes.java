package job;

import java.util.Calendar;

import model.AniversarianteDao;
import model.CadastroDao;
import model.ConfExtraBean;
import model.ConfExtraDao;

public class ManualAniversariantes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfExtraDao cextraDao = new ConfExtraDao();
		ConfExtraBean cextraB = cextraDao.readConfigurations();
		CadastroDao cadastro = CadastroDao.getInstance();
		Calendar calendar = Calendar.getInstance();
		new AniversarianteDao().processarEnviarAniversariantes(calendar, cextraB);
	}

}
