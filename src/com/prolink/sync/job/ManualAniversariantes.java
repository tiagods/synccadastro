package com.prolink.sync.job;

import java.util.Calendar;

import com.prolink.sync.model.AniversarianteDao;
import com.prolink.sync.model.ConfExtraBean;
import com.prolink.sync.model.ConfExtraDao;

public class ManualAniversariantes {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		ConfExtraDao cextraDao = new ConfExtraDao();
		ConfExtraBean cextraB = cextraDao.readConfigurations();
		System.out.println("Iniciando envio");
		Calendar calendar = Calendar.getInstance();
		new AniversarianteDao().processarEnviarAniversariantes(calendar, cextraB);
		System.out.println("Pronto");
		System.exit(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
