package model;

public class ConfExtraDao {
	public ConfExtraBean readConfigurations(){
		ConfExtraBean bean = new ConfExtraBean();
		bean.setCODIGO(new Integer(1));
		bean.setPLANILHA_NOME("");
		bean.setPLANILHA_LOCALIZACAO("");
		bean.setDIRETORIO_TEMP("");
		return bean;
	}
}
