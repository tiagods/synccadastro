package com.prolink.sync.model;

import java.io.Serializable;

public class ConfExtraBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int CODIGO;
	private String PLANILHA_NOME;
	private String PLANILHA_LOCALIZACAO;
	private String DIRETORIO_TEMP;
	public int getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(int cODIGO) {
		CODIGO = cODIGO;
	}
	public String getPLANILHA_NOME() {
		return PLANILHA_NOME;
	}
	public void setPLANILHA_NOME(String pLANILHA_NOME) {
		PLANILHA_NOME = pLANILHA_NOME;
	}
	public String getPLANILHA_LOCALIZACAO() {
		return PLANILHA_LOCALIZACAO;
	}
	public void setPLANILHA_LOCALIZACAO(String pLANILHA_LOCALIZACAO) {
		PLANILHA_LOCALIZACAO = pLANILHA_LOCALIZACAO;
	}
	public String getDIRETORIO_TEMP() {
		return DIRETORIO_TEMP;
	}
	public void setDIRETORIO_TEMP(String dIRETORIO_TEMP) {
		DIRETORIO_TEMP = dIRETORIO_TEMP;
	}
	
}
