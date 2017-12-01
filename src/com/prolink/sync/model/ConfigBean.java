package com.prolink.sync.model;

import java.io.Serializable;

public class ConfigBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CODIGO;
	private String SEGUNDO;
	private String MINUTO;
	private String HORA;
	private String DIA_DO_MES;
	private String MES;
	private String DIA_DA_SEMANA;
	
	public int getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(int cODIGO) {
		CODIGO = cODIGO;
	}
	public String getSEGUNDO() {
		return SEGUNDO;
	}
	public void setSEGUNDO(String sEGUNDO) {
		SEGUNDO = sEGUNDO;
	}
	public String getMINUTO() {
		return MINUTO;
	}
	public void setMINUTO(String mINUTO) {
		MINUTO = mINUTO;
	}
	public String getHORA() {
		return HORA;
	}
	public void setHORA(String hORA) {
		HORA = hORA;
	}
	public String getDIA_DO_MES() {
		return DIA_DO_MES;
	}
	public void setDIA_DO_MES(String dIA_DO_MES) {
		DIA_DO_MES = dIA_DO_MES;
	}
	public String getMES() {
		return MES;
	}
	public void setMES(String mES) {
		MES = mES;
	}
	public String getDIA_DA_SEMANA() {
		return DIA_DA_SEMANA;
	}
	public void setDIA_DA_SEMANA(String dIA_DA_SEMANA) {
		DIA_DA_SEMANA = dIA_DA_SEMANA;
	}
	
}