package model;

public class ConfigDao {

	public ConfigBean readConfigurations(){
		ConfigBean cb = new ConfigBean();
		cb.setCODIGO(new Integer(1));
		cb.setSEGUNDO("");
		cb.setMINUTO("");
		cb.setHORA("");
		cb.setDIA_DO_MES("");
		cb.setMES("");
		cb.setDIA_DA_SEMANA("");
		return cb;
	}

}
