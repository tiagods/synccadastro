package com.prolink.sync.model;

import java.io.Serializable;
import java.util.Date;

public class Status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int COD;
	private Date DATA;
	private long TEMPOGRAVACAO;
	private long TEMPOLEITURA;
	private String DETALHES;
	
	
	public Status(Date data, long tempoGravacao, long tempoPlanilha, String detalhes){
		this.setDATA(data);
		this.setTEMPOLEITURA(tempoPlanilha);
		this.setTEMPOGRAVACAO(tempoGravacao);
		this.setDETALHES(detalhes);
	}

	/**
	 * @return the cOD
	 */
	public int getCOD() {
		return COD;
	}

	/**
	 * @param cOD the cOD to set
	 */
	public void setCOD(int cOD) {
		COD = cOD;
	}

	/**
	 * @return the dATA
	 */
	public Date getDATA() {
		return DATA;
	}


	/**
	 * @param dATA the dATA to set
	 */
	public void setDATA(Date dATA) {
		DATA = dATA;
	}


	/**
	 * @return the tEMPOGRAVACAO
	 */
	public long getTEMPOGRAVACAO() {
		return TEMPOGRAVACAO;
	}


	/**
	 * @param tEMPOGRAVACAO the tEMPOGRAVACAO to set
	 */
	public void setTEMPOGRAVACAO(long tEMPOGRAVACAO) {
		TEMPOGRAVACAO = tEMPOGRAVACAO;
	}


	/**
	 * @return the tEMPOLEITURA
	 */
	public long getTEMPOLEITURA() {
		return TEMPOLEITURA;
	}


	/**
	 * @param tEMPOLEITURA the tEMPOLEITURA to set
	 */
	public void setTEMPOLEITURA(long tEMPOLEITURA) {
		TEMPOLEITURA = tEMPOLEITURA;
	}


	/**
	 * @return the dETALHES
	 */
	public String getDETALHES() {
		return DETALHES;
	}


	/**
	 * @param dETALHES the dETALHES to set
	 */
	public void setDETALHES(String dETALHES) {
		DETALHES = dETALHES;
	}
}
