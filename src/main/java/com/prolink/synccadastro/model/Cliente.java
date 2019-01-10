package com.prolink.synccadastro.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int COD;
	private String STATUS;
	private String COMPL_STS;
	private String ATENDIMENTO;
	private String PROCESSOS;
	private String SISTEMA;
	private String MP;
	private String TIPO;
	private String REGIME_TRIBUTARIO;
	private String QUANT_SOC_GER;
	private String EMPRESA;
	private String NOME;
	private String ENDERECO;
	private String BAIRRO;
	private String CIDADE;
	private String EST;
	private String CEP;
	private String CNPJ;
	private String DATA_CNPJ;
	private String IE;
	private String VLR_CAPITAL;
	private String VL_EXTENSO;
	private String VL_COTA;
	private String VL_EXT_COTA;
	private String COTAS_TOTAL;
	private String COTAS_EXTENSO;
	private String PERC_CAP_1;
	private String PERC_CAP_2;
	private String PERC_CAP_DEMAIS;
	private String SOMA_PERC_CAP;
	private String COTAS_01;
	private String COTAS_02;
	private String PREF;
	private String DATA_IM;
	private String ALIQUOTA_DE_ISS;
	private String ENC_MUNIC;
	private String DT_DISTRATO;
	private String NIRC;
	private String N_REG_CART_OU_NIRE;
	private String DATA_REG_CART_OU_JUCESP;
	private String SIND_CNPJ;
	private String ATIVIDADE;
	private String SINDICATO;
	private String CNAE1;
	private String COMP_CNAE1;
	private String DIG_CNAE1;
	private String CNAE;
	private String COMP_CNAE;
	private String DIG_CNAE;
	private String FPAS;
	private String QUANT_SOCIOS;
	private String CONSULTORIA;
	private String INDICADO;
	private String APELIDO_1;
	private String END_CORRESP;
	private String BAIRRO_CO;
	private String CID_CO;
	private String EST_CO;
	private String CEP_CO;
	private String NOME_SOCIO1;
	private String QUALIDADE1;
	private String NAC_01;
	private String NATURALIDADE_1;
	private String EST_CIVEL_01;
	private String MAIOR_EMANCIPADO_1;
	private String PROF_01;
	private String DIA_NASC1;
	private String MES_NASC1;
	private String ANO_NASC1;
	private String PIS_SOC1;
	private String END_SOC_1;
	private String BAIRRO1;
	private String CIDADE1;
	private String EST1;
	private String CEP1;
	private String RG1;
	private String ORGAO1;
	private String ESTD1;
	private String DATA_EXP1;
	private String CPF1;
	@Column(columnDefinition = "text")
	private String EMAIL_SOC_1;
	@Column(columnDefinition = "text")
	private String EMAIL_FINANCEIRO;
	private String DDD1;
	private String FONERES1;
	private String RES_RAMAL_1;
	private String DDD1COML;
	private String FONECOML1;
	private String COML_RAMAL_1;
	private String DDD1CEL;
	private String CELULAR;
	private String DDD1REC;
	private String FONEREC1;
	private String OBSFONE_REC1;
	private String NOME_SOC_2;
	private String QUALIDADE2;
	private String NAC_02;
	private String NATURALIDADE_2;
	private String EST_CIVEL_02;
	private String MAIOR_EMANCIPADO_2;
	private String PROF_02;
	private String END_SOC_2;
	private String BAIRRO2;
	private String CIDADE2;
	private String EST2;
	private String CEP2;
	private String DIA_NASC2;
	private String MES_NASC2;
	private String ANO_NASC2;
	private String PIS_SOC2;
	private String RG2;
	private String ORGAO2;
	private String ESTD2;
	private String DATA_EXP2;
	private String CPF2;
	private String DDD2RES;
	private String FONERES2;
	private String DDD2COML;
	private String FONECOM2;
	private String DDD2CEL;
	private String CELULAR2;
	@Column(columnDefinition = "text")
	private String OBS;

	private Calendar criadoEm;
	private Calendar ultimaVersao;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	private ClienteComentario comentario;

	@PrePersist
	private void preInsercao() {
		criadoEm = Calendar.getInstance();
		ultimaVersao = criadoEm;
	}

	@PreUpdate
	private void preAtualizacao() {
		ultimaVersao = Calendar.getInstance();
	}

	/**
	 * @return the cOD
	 */
	public int getCOD() {
		return COD;
	}

	/**
	 * @param cOD
	 *            the cOD to set
	 */
	public void setCOD(int cOD) {
		COD = cOD;
	}

	/**
	 * @return the sTATUS
	 */
	public String getSTATUS() {
		return STATUS;
	}

	/**
	 * @param sTATUS
	 *            the sTATUS to set
	 */
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	/**
	 * @return the cOMPL_STS
	 */
	public String getCOMPL_STS() {
		return COMPL_STS;
	}

	/**
	 * @param cOMPL_STS
	 *            the cOMPL_STS to set
	 */
	public void setCOMPL_STS(String cOMPL_STS) {
		COMPL_STS = cOMPL_STS;
	}

	/**
	 * @return the aTENDIMENTO
	 */
	public String getATENDIMENTO() {
		return ATENDIMENTO;
	}

	/**
	 * @param aTENDIMENTO
	 *            the aTENDIMENTO to set
	 */
	public void setATENDIMENTO(String aTENDIMENTO) {
		ATENDIMENTO = aTENDIMENTO;
	}

	/**
	 * @return the pROCESSOS
	 */
	public String getPROCESSOS() {
		return PROCESSOS;
	}

	/**
	 * @param pROCESSOS
	 *            the pROCESSOS to set
	 */
	public void setPROCESSOS(String pROCESSOS) {
		PROCESSOS = pROCESSOS;
	}

	/**
	 * @return the sISTEMA
	 */
	public String getSISTEMA() {
		return SISTEMA;
	}

	/**
	 * @param sISTEMA
	 *            the sISTEMA to set
	 */
	public void setSISTEMA(String sISTEMA) {
		SISTEMA = sISTEMA;
	}

	/**
	 * @return the mP
	 */
	public String getMP() {
		return MP;
	}

	/**
	 * @param mP
	 *            the mP to set
	 */
	public void setMP(String mP) {
		MP = mP;
	}

	/**
	 * @return the tIPO
	 */
	public String getTIPO() {
		return TIPO;
	}

	/**
	 * @param tIPO
	 *            the tIPO to set
	 */
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}

	/**
	 * @return the rEGIME_TRIBUTARIO
	 */
	public String getREGIME_TRIBUTARIO() {
		return REGIME_TRIBUTARIO;
	}

	/**
	 * @param rEGIME_TRIBUTARIO
	 *            the rEGIME_TRIBUTARIO to set
	 */
	public void setREGIME_TRIBUTARIO(String rEGIME_TRIBUTARIO) {
		REGIME_TRIBUTARIO = rEGIME_TRIBUTARIO;
	}

	/**
	 * @return the qUANT_SOC_GER
	 */
	public String getQUANT_SOC_GER() {
		return QUANT_SOC_GER;
	}

	/**
	 * @param qUANT_SOC_GER
	 *            the qUANT_SOC_GER to set
	 */
	public void setQUANT_SOC_GER(String qUANT_SOC_GER) {
		QUANT_SOC_GER = qUANT_SOC_GER;
	}

	/**
	 * @return the eMPRESA
	 */
	public String getEMPRESA() {
		return EMPRESA;
	}

	/**
	 * @param eMPRESA
	 *            the eMPRESA to set
	 */
	public void setEMPRESA(String eMPRESA) {
		EMPRESA = eMPRESA;
	}

	/**
	 * @return the nOME
	 */
	public String getNOME() {
		return NOME;
	}

	/**
	 * @param nOME
	 *            the nOME to set
	 */
	public void setNOME(String nOME) {
		NOME = nOME;
	}

	/**
	 * @return the eNDERECO
	 */
	public String getENDERECO() {
		return ENDERECO;
	}

	/**
	 * @param eNDERECO
	 *            the eNDERECO to set
	 */
	public void setENDERECO(String eNDERECO) {
		ENDERECO = eNDERECO;
	}

	/**
	 * @return the bAIRRO
	 */
	public String getBAIRRO() {
		return BAIRRO;
	}

	/**
	 * @param bAIRRO
	 *            the bAIRRO to set
	 */
	public void setBAIRRO(String bAIRRO) {
		BAIRRO = bAIRRO;
	}

	/**
	 * @return the cIDADE
	 */
	public String getCIDADE() {
		return CIDADE;
	}

	/**
	 * @param cIDADE
	 *            the cIDADE to set
	 */
	public void setCIDADE(String cIDADE) {
		CIDADE = cIDADE;
	}

	/**
	 * @return the eST
	 */
	public String getEST() {
		return EST;
	}

	/**
	 * @param eST
	 *            the eST to set
	 */
	public void setEST(String eST) {
		EST = eST;
	}

	/**
	 * @return the cEP
	 */
	public String getCEP() {
		return CEP;
	}

	/**
	 * @param cEP
	 *            the cEP to set
	 */
	public void setCEP(String cEP) {
		CEP = cEP;
	}

	/**
	 * @return the cNPJ
	 */
	public String getCNPJ() {
		return CNPJ;
	}

	/**
	 * @param cNPJ
	 *            the cNPJ to set
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	/**
	 * @return the dATA_CNPJ
	 */
	public String getDATA_CNPJ() {
		return DATA_CNPJ;
	}

	/**
	 * @param dATA_CNPJ
	 *            the dATA_CNPJ to set
	 */
	public void setDATA_CNPJ(String dATA_CNPJ) {
		DATA_CNPJ = dATA_CNPJ;
	}

	/**
	 * @return the iE
	 */
	public String getIE() {
		return IE;
	}

	/**
	 * @param iE
	 *            the iE to set
	 */
	public void setIE(String iE) {
		IE = iE;
	}

	/**
	 * @return the vLR_CAPITAL
	 */
	public String getVLR_CAPITAL() {
		return VLR_CAPITAL;
	}

	/**
	 * @param vLR_CAPITAL
	 *            the vLR_CAPITAL to set
	 */
	public void setVLR_CAPITAL(String vLR_CAPITAL) {
		VLR_CAPITAL = vLR_CAPITAL;
	}

	/**
	 * @return the vL_EXTENSO
	 */
	public String getVL_EXTENSO() {
		return VL_EXTENSO;
	}

	/**
	 * @param vL_EXTENSO
	 *            the vL_EXTENSO to set
	 */
	public void setVL_EXTENSO(String vL_EXTENSO) {
		VL_EXTENSO = vL_EXTENSO;
	}

	/**
	 * @return the vL_COTA
	 */
	public String getVL_COTA() {
		return VL_COTA;
	}

	/**
	 * @param vL_COTA
	 *            the vL_COTA to set
	 */
	public void setVL_COTA(String vL_COTA) {
		VL_COTA = vL_COTA;
	}

	/**
	 * @return the vL_EXT_COTA
	 */
	public String getVL_EXT_COTA() {
		return VL_EXT_COTA;
	}

	/**
	 * @param vL_EXT_COTA
	 *            the vL_EXT_COTA to set
	 */
	public void setVL_EXT_COTA(String vL_EXT_COTA) {
		VL_EXT_COTA = vL_EXT_COTA;
	}

	/**
	 * @return the cOTAS_TOTAL
	 */
	public String getCOTAS_TOTAL() {
		return COTAS_TOTAL;
	}

	/**
	 * @param cOTAS_TOTAL
	 *            the cOTAS_TOTAL to set
	 */
	public void setCOTAS_TOTAL(String cOTAS_TOTAL) {
		COTAS_TOTAL = cOTAS_TOTAL;
	}

	/**
	 * @return the cOTAS_EXTENSO
	 */
	public String getCOTAS_EXTENSO() {
		return COTAS_EXTENSO;
	}

	/**
	 * @param cOTAS_EXTENSO
	 *            the cOTAS_EXTENSO to set
	 */
	public void setCOTAS_EXTENSO(String cOTAS_EXTENSO) {
		COTAS_EXTENSO = cOTAS_EXTENSO;
	}

	/**
	 * @return the pERC_CAP_1
	 */
	public String getPERC_CAP_1() {
		return PERC_CAP_1;
	}

	/**
	 * @param pERC_CAP_1
	 *            the pERC_CAP_1 to set
	 */
	public void setPERC_CAP_1(String pERC_CAP_1) {
		PERC_CAP_1 = pERC_CAP_1;
	}

	/**
	 * @return the pERC_CAP_2
	 */
	public String getPERC_CAP_2() {
		return PERC_CAP_2;
	}

	/**
	 * @param pERC_CAP_2
	 *            the pERC_CAP_2 to set
	 */
	public void setPERC_CAP_2(String pERC_CAP_2) {
		PERC_CAP_2 = pERC_CAP_2;
	}

	/**
	 * @return the pERC_CAP_DEMAIS
	 */
	public String getPERC_CAP_DEMAIS() {
		return PERC_CAP_DEMAIS;
	}

	/**
	 * @param pERC_CAP_DEMAIS
	 *            the pERC_CAP_DEMAIS to set
	 */
	public void setPERC_CAP_DEMAIS(String pERC_CAP_DEMAIS) {
		PERC_CAP_DEMAIS = pERC_CAP_DEMAIS;
	}

	/**
	 * @return the sOMA_PERC_CAP
	 */
	public String getSOMA_PERC_CAP() {
		return SOMA_PERC_CAP;
	}

	/**
	 * @param sOMA_PERC_CAP
	 *            the sOMA_PERC_CAP to set
	 */
	public void setSOMA_PERC_CAP(String sOMA_PERC_CAP) {
		SOMA_PERC_CAP = sOMA_PERC_CAP;
	}

	/**
	 * @return the cOTAS_01
	 */
	public String getCOTAS_01() {
		return COTAS_01;
	}

	/**
	 * @param cOTAS_01
	 *            the cOTAS_01 to set
	 */
	public void setCOTAS_01(String cOTAS_01) {
		COTAS_01 = cOTAS_01;
	}

	/**
	 * @return the cOTAS_02
	 */
	public String getCOTAS_02() {
		return COTAS_02;
	}

	/**
	 * @param cOTAS_02
	 *            the cOTAS_02 to set
	 */
	public void setCOTAS_02(String cOTAS_02) {
		COTAS_02 = cOTAS_02;
	}

	/**
	 * @return the pREF
	 */
	public String getPREF() {
		return PREF;
	}

	/**
	 * @param pREF
	 *            the pREF to set
	 */
	public void setPREF(String pREF) {
		PREF = pREF;
	}

	/**
	 * @return the dATA_IM
	 */
	public String getDATA_IM() {
		return DATA_IM;
	}

	/**
	 * @param dATA_IM
	 *            the dATA_IM to set
	 */
	public void setDATA_IM(String dATA_IM) {
		DATA_IM = dATA_IM;
	}

	/**
	 * @return the aLIQUOTA_DE_ISS
	 */
	public String getALIQUOTA_DE_ISS() {
		return ALIQUOTA_DE_ISS;
	}

	/**
	 * @param aLIQUOTA_DE_ISS
	 *            the aLIQUOTA_DE_ISS to set
	 */
	public void setALIQUOTA_DE_ISS(String aLIQUOTA_DE_ISS) {
		ALIQUOTA_DE_ISS = aLIQUOTA_DE_ISS;
	}

	/**
	 * @return the eNC_MUNIC
	 */
	public String getENC_MUNIC() {
		return ENC_MUNIC;
	}

	/**
	 * @param eNC_MUNIC
	 *            the eNC_MUNIC to set
	 */
	public void setENC_MUNIC(String eNC_MUNIC) {
		ENC_MUNIC = eNC_MUNIC;
	}

	/**
	 * @return the dT_DISTRATO
	 */
	public String getDT_DISTRATO() {
		return DT_DISTRATO;
	}

	/**
	 * @param dT_DISTRATO
	 *            the dT_DISTRATO to set
	 */
	public void setDT_DISTRATO(String dT_DISTRATO) {
		DT_DISTRATO = dT_DISTRATO;
	}

	/**
	 * @return the nIRC
	 */
	public String getNIRC() {
		return NIRC;
	}

	/**
	 * @param nIRC
	 *            the nIRC to set
	 */
	public void setNIRC(String nIRC) {
		NIRC = nIRC;
	}

	/**
	 * @return the n_REG_CART_OU_NIRE
	 */
	public String getN_REG_CART_OU_NIRE() {
		return N_REG_CART_OU_NIRE;
	}

	/**
	 * @param n_REG_CART_OU_NIRE
	 *            the n_REG_CART_OU_NIRE to set
	 */
	public void setN_REG_CART_OU_NIRE(String n_REG_CART_OU_NIRE) {
		N_REG_CART_OU_NIRE = n_REG_CART_OU_NIRE;
	}

	/**
	 * @return the dATA_REG_CART_OU_JUCESP
	 */
	public String getDATA_REG_CART_OU_JUCESP() {
		return DATA_REG_CART_OU_JUCESP;
	}

	/**
	 * @param dATA_REG_CART_OU_JUCESP
	 *            the dATA_REG_CART_OU_JUCESP to set
	 */
	public void setDATA_REG_CART_OU_JUCESP(String dATA_REG_CART_OU_JUCESP) {
		DATA_REG_CART_OU_JUCESP = dATA_REG_CART_OU_JUCESP;
	}

	/**
	 * @return the sIND_CNPJ
	 */
	public String getSIND_CNPJ() {
		return SIND_CNPJ;
	}

	/**
	 * @param sIND_CNPJ
	 *            the sIND_CNPJ to set
	 */
	public void setSIND_CNPJ(String sIND_CNPJ) {
		SIND_CNPJ = sIND_CNPJ;
	}

	/**
	 * @return the aTIVIDADE
	 */
	public String getATIVIDADE() {
		return ATIVIDADE;
	}

	/**
	 * @param aTIVIDADE
	 *            the aTIVIDADE to set
	 */
	public void setATIVIDADE(String aTIVIDADE) {
		ATIVIDADE = aTIVIDADE;
	}

	/**
	 * @return the sINDICATO
	 */
	public String getSINDICATO() {
		return SINDICATO;
	}

	/**
	 * @param sINDICATO
	 *            the sINDICATO to set
	 */
	public void setSINDICATO(String sINDICATO) {
		SINDICATO = sINDICATO;
	}

	/**
	 * @return the cNAE1
	 */
	public String getCNAE1() {
		return CNAE1;
	}

	/**
	 * @param cNAE1
	 *            the cNAE1 to set
	 */
	public void setCNAE1(String cNAE1) {
		CNAE1 = cNAE1;
	}

	/**
	 * @return the cOMP_CNAE1
	 */
	public String getCOMP_CNAE1() {
		return COMP_CNAE1;
	}

	/**
	 * @param cOMP_CNAE1
	 *            the cOMP_CNAE1 to set
	 */
	public void setCOMP_CNAE1(String cOMP_CNAE1) {
		COMP_CNAE1 = cOMP_CNAE1;
	}

	/**
	 * @return the dIG_CNAE1
	 */
	public String getDIG_CNAE1() {
		return DIG_CNAE1;
	}

	/**
	 * @param dIG_CNAE1
	 *            the dIG_CNAE1 to set
	 */
	public void setDIG_CNAE1(String dIG_CNAE1) {
		DIG_CNAE1 = dIG_CNAE1;
	}

	/**
	 * @return the cNAE
	 */
	public String getCNAE() {
		return CNAE;
	}

	/**
	 * @param cNAE
	 *            the cNAE to set
	 */
	public void setCNAE(String cNAE) {
		CNAE = cNAE;
	}

	/**
	 * @return the cOMP_CNAE
	 */
	public String getCOMP_CNAE() {
		return COMP_CNAE;
	}

	/**
	 * @param cOMP_CNAE
	 *            the cOMP_CNAE to set
	 */
	public void setCOMP_CNAE(String cOMP_CNAE) {
		COMP_CNAE = cOMP_CNAE;
	}

	/**
	 * @return the dIG_CNAE
	 */
	public String getDIG_CNAE() {
		return DIG_CNAE;
	}

	/**
	 * @param dIG_CNAE
	 *            the dIG_CNAE to set
	 */
	public void setDIG_CNAE(String dIG_CNAE) {
		DIG_CNAE = dIG_CNAE;
	}

	/**
	 * @return the fPAS
	 */
	public String getFPAS() {
		return FPAS;
	}

	/**
	 * @param fPAS
	 *            the fPAS to set
	 */
	public void setFPAS(String fPAS) {
		FPAS = fPAS;
	}

	/**
	 * @return the qUANT_SOCIOS
	 */
	public String getQUANT_SOCIOS() {
		return QUANT_SOCIOS;
	}

	/**
	 * @param qUANT_SOCIOS
	 *            the qUANT_SOCIOS to set
	 */
	public void setQUANT_SOCIOS(String qUANT_SOCIOS) {
		QUANT_SOCIOS = qUANT_SOCIOS;
	}

	/**
	 * @return the cONSULTORIA
	 */
	public String getCONSULTORIA() {
		return CONSULTORIA;
	}

	/**
	 * @param cONSULTORIA
	 *            the cONSULTORIA to set
	 */
	public void setCONSULTORIA(String cONSULTORIA) {
		CONSULTORIA = cONSULTORIA;
	}

	/**
	 * @return the iNDICADO
	 */
	public String getINDICADO() {
		return INDICADO;
	}

	/**
	 * @param iNDICADO
	 *            the iNDICADO to set
	 */
	public void setINDICADO(String iNDICADO) {
		INDICADO = iNDICADO;
	}

	/**
	 * @return the aPELIDO_1
	 */
	public String getAPELIDO_1() {
		return APELIDO_1;
	}

	/**
	 * @param aPELIDO_1
	 *            the aPELIDO_1 to set
	 */
	public void setAPELIDO_1(String aPELIDO_1) {
		APELIDO_1 = aPELIDO_1;
	}

	/**
	 * @return the eND_CORRESP
	 */
	public String getEND_CORRESP() {
		return END_CORRESP;
	}

	/**
	 * @param eND_CORRESP
	 *            the eND_CORRESP to set
	 */
	public void setEND_CORRESP(String eND_CORRESP) {
		END_CORRESP = eND_CORRESP;
	}

	/**
	 * @return the bAIRRO_CO
	 */
	public String getBAIRRO_CO() {
		return BAIRRO_CO;
	}

	/**
	 * @param bAIRRO_CO
	 *            the bAIRRO_CO to set
	 */
	public void setBAIRRO_CO(String bAIRRO_CO) {
		BAIRRO_CO = bAIRRO_CO;
	}

	/**
	 * @return the cID_CO
	 */
	public String getCID_CO() {
		return CID_CO;
	}

	/**
	 * @param cID_CO
	 *            the cID_CO to set
	 */
	public void setCID_CO(String cID_CO) {
		CID_CO = cID_CO;
	}

	/**
	 * @return the eST_CO
	 */
	public String getEST_CO() {
		return EST_CO;
	}

	/**
	 * @param eST_CO
	 *            the eST_CO to set
	 */
	public void setEST_CO(String eST_CO) {
		EST_CO = eST_CO;
	}

	/**
	 * @return the cEP_CO
	 */
	public String getCEP_CO() {
		return CEP_CO;
	}

	/**
	 * @param cEP_CO
	 *            the cEP_CO to set
	 */
	public void setCEP_CO(String cEP_CO) {
		CEP_CO = cEP_CO;
	}

	/**
	 * @return the nOME_SOCIO1
	 */
	public String getNOME_SOCIO1() {
		return NOME_SOCIO1;
	}

	/**
	 * @param nOME_SOCIO1
	 *            the nOME_SOCIO1 to set
	 */
	public void setNOME_SOCIO1(String nOME_SOCIO1) {
		NOME_SOCIO1 = nOME_SOCIO1;
	}

	/**
	 * @return the qUALIDADE1
	 */
	public String getQUALIDADE1() {
		return QUALIDADE1;
	}

	/**
	 * @param qUALIDADE1
	 *            the qUALIDADE1 to set
	 */
	public void setQUALIDADE1(String qUALIDADE1) {
		QUALIDADE1 = qUALIDADE1;
	}

	/**
	 * @return the nAC_01
	 */
	public String getNAC_01() {
		return NAC_01;
	}

	/**
	 * @param nAC_01
	 *            the nAC_01 to set
	 */
	public void setNAC_01(String nAC_01) {
		NAC_01 = nAC_01;
	}

	/**
	 * @return the nATURALIDADE_1
	 */
	public String getNATURALIDADE_1() {
		return NATURALIDADE_1;
	}

	/**
	 * @param nATURALIDADE_1
	 *            the nATURALIDADE_1 to set
	 */
	public void setNATURALIDADE_1(String nATURALIDADE_1) {
		NATURALIDADE_1 = nATURALIDADE_1;
	}

	/**
	 * @return the eST_CIVEL_01
	 */
	public String getEST_CIVEL_01() {
		return EST_CIVEL_01;
	}

	/**
	 * @param eST_CIVEL_01
	 *            the eST_CIVEL_01 to set
	 */
	public void setEST_CIVEL_01(String eST_CIVEL_01) {
		EST_CIVEL_01 = eST_CIVEL_01;
	}

	/**
	 * @return the mAIOR_EMANCIPADO_1
	 */
	public String getMAIOR_EMANCIPADO_1() {
		return MAIOR_EMANCIPADO_1;
	}

	/**
	 * @param mAIOR_EMANCIPADO_1
	 *            the mAIOR_EMANCIPADO_1 to set
	 */
	public void setMAIOR_EMANCIPADO_1(String mAIOR_EMANCIPADO_1) {
		MAIOR_EMANCIPADO_1 = mAIOR_EMANCIPADO_1;
	}

	/**
	 * @return the pROF_01
	 */
	public String getPROF_01() {
		return PROF_01;
	}

	/**
	 * @param pROF_01
	 *            the pROF_01 to set
	 */
	public void setPROF_01(String pROF_01) {
		PROF_01 = pROF_01;
	}

	/**
	 * @return the dIA_NASC1
	 */
	public String getDIA_NASC1() {
		return DIA_NASC1;
	}

	/**
	 * @param dIA_NASC1
	 *            the dIA_NASC1 to set
	 */
	public void setDIA_NASC1(String dIA_NASC1) {
		DIA_NASC1 = dIA_NASC1;
	}

	/**
	 * @return the mES_NASC1
	 */
	public String getMES_NASC1() {
		return MES_NASC1;
	}

	/**
	 * @param mES_NASC1
	 *            the mES_NASC1 to set
	 */
	public void setMES_NASC1(String mES_NASC1) {
		MES_NASC1 = mES_NASC1;
	}

	/**
	 * @return the aNO_NASC1
	 */
	public String getANO_NASC1() {
		return ANO_NASC1;
	}

	/**
	 * @param aNO_NASC1
	 *            the aNO_NASC1 to set
	 */
	public void setANO_NASC1(String aNO_NASC1) {
		ANO_NASC1 = aNO_NASC1;
	}

	/**
	 * @return the pIS_SOC1
	 */
	public String getPIS_SOC1() {
		return PIS_SOC1;
	}

	/**
	 * @param pIS_SOC1
	 *            the pIS_SOC1 to set
	 */
	public void setPIS_SOC1(String pIS_SOC1) {
		PIS_SOC1 = pIS_SOC1;
	}

	/**
	 * @return the eND_SOC_1
	 */
	public String getEND_SOC_1() {
		return END_SOC_1;
	}

	/**
	 * @param eND_SOC_1
	 *            the eND_SOC_1 to set
	 */
	public void setEND_SOC_1(String eND_SOC_1) {
		END_SOC_1 = eND_SOC_1;
	}

	/**
	 * @return the bAIRRO1
	 */
	public String getBAIRRO1() {
		return BAIRRO1;
	}

	/**
	 * @param bAIRRO1
	 *            the bAIRRO1 to set
	 */
	public void setBAIRRO1(String bAIRRO1) {
		BAIRRO1 = bAIRRO1;
	}

	/**
	 * @return the cIDADE1
	 */
	public String getCIDADE1() {
		return CIDADE1;
	}

	/**
	 * @param cIDADE1
	 *            the cIDADE1 to set
	 */
	public void setCIDADE1(String cIDADE1) {
		CIDADE1 = cIDADE1;
	}

	/**
	 * @return the eST1
	 */
	public String getEST1() {
		return EST1;
	}

	/**
	 * @param eST1
	 *            the eST1 to set
	 */
	public void setEST1(String eST1) {
		EST1 = eST1;
	}

	/**
	 * @return the cEP1
	 */
	public String getCEP1() {
		return CEP1;
	}

	/**
	 * @param cEP1
	 *            the cEP1 to set
	 */
	public void setCEP1(String cEP1) {
		CEP1 = cEP1;
	}

	/**
	 * @return the rG1
	 */
	public String getRG1() {
		return RG1;
	}

	/**
	 * @param rG1
	 *            the rG1 to set
	 */
	public void setRG1(String rG1) {
		RG1 = rG1;
	}

	/**
	 * @return the oRGAO1
	 */
	public String getORGAO1() {
		return ORGAO1;
	}

	/**
	 * @param oRGAO1
	 *            the oRGAO1 to set
	 */
	public void setORGAO1(String oRGAO1) {
		ORGAO1 = oRGAO1;
	}

	/**
	 * @return the eSTD1
	 */
	public String getESTD1() {
		return ESTD1;
	}

	/**
	 * @param eSTD1
	 *            the eSTD1 to set
	 */
	public void setESTD1(String eSTD1) {
		ESTD1 = eSTD1;
	}

	/**
	 * @return the dATA_EXP1
	 */
	public String getDATA_EXP1() {
		return DATA_EXP1;
	}

	/**
	 * @param dATA_EXP1
	 *            the dATA_EXP1 to set
	 */
	public void setDATA_EXP1(String dATA_EXP1) {
		DATA_EXP1 = dATA_EXP1;
	}

	/**
	 * @return the cPF1
	 */
	public String getCPF1() {
		return CPF1;
	}

	/**
	 * @param cPF1
	 *            the cPF1 to set
	 */
	public void setCPF1(String cPF1) {
		CPF1 = cPF1;
	}

	/**
	 * @return the eMAIL_SOC_1
	 */
	public String getEMAIL_SOC_1() {
		return EMAIL_SOC_1;
	}

	/**
	 * @param eMAIL_SOC_1
	 *            the eMAIL_SOC_1 to set
	 */
	public void setEMAIL_SOC_1(String eMAIL_SOC_1) {
		EMAIL_SOC_1 = eMAIL_SOC_1;
	}

	/**
	 * @return the eMAIL_FINANCEIRO
	 */
	public String getEMAIL_FINANCEIRO() {
		return EMAIL_FINANCEIRO;
	}

	/**
	 * @param eMAIL_FINANCEIRO
	 *            the eMAIL_FINANCEIRO to set
	 */
	public void setEMAIL_FINANCEIRO(String eMAIL_FINANCEIRO) {
		EMAIL_FINANCEIRO = eMAIL_FINANCEIRO;
	}

	/**
	 * @return the dDD1
	 */
	public String getDDD1() {
		return DDD1;
	}

	/**
	 * @param dDD1
	 *            the dDD1 to set
	 */
	public void setDDD1(String dDD1) {
		DDD1 = dDD1;
	}

	/**
	 * @return the fONERES1
	 */
	public String getFONERES1() {
		return FONERES1;
	}

	/**
	 * @param fONERES1
	 *            the fONERES1 to set
	 */
	public void setFONERES1(String fONERES1) {
		FONERES1 = fONERES1;
	}

	/**
	 * @return the rES_RAMAL_1
	 */
	public String getRES_RAMAL_1() {
		return RES_RAMAL_1;
	}

	/**
	 * @param rES_RAMAL_1
	 *            the rES_RAMAL_1 to set
	 */
	public void setRES_RAMAL_1(String rES_RAMAL_1) {
		RES_RAMAL_1 = rES_RAMAL_1;
	}

	/**
	 * @return the dDD1COML
	 */
	public String getDDD1COML() {
		return DDD1COML;
	}

	/**
	 * @param dDD1COML
	 *            the dDD1COML to set
	 */
	public void setDDD1COML(String dDD1COML) {
		DDD1COML = dDD1COML;
	}

	/**
	 * @return the fONECOML1
	 */
	public String getFONECOML1() {
		return FONECOML1;
	}

	/**
	 * @param fONECOML1
	 *            the fONECOML1 to set
	 */
	public void setFONECOML1(String fONECOML1) {
		FONECOML1 = fONECOML1;
	}

	/**
	 * @return the cOML_RAMAL_1
	 */
	public String getCOML_RAMAL_1() {
		return COML_RAMAL_1;
	}

	/**
	 * @param cOML_RAMAL_1
	 *            the cOML_RAMAL_1 to set
	 */
	public void setCOML_RAMAL_1(String cOML_RAMAL_1) {
		COML_RAMAL_1 = cOML_RAMAL_1;
	}

	/**
	 * @return the dDD1CEL
	 */
	public String getDDD1CEL() {
		return DDD1CEL;
	}

	/**
	 * @param dDD1CEL
	 *            the dDD1CEL to set
	 */
	public void setDDD1CEL(String dDD1CEL) {
		DDD1CEL = dDD1CEL;
	}

	/**
	 * @return the cELULAR
	 */
	public String getCELULAR() {
		return CELULAR;
	}

	/**
	 * @param cELULAR
	 *            the cELULAR to set
	 */
	public void setCELULAR(String cELULAR) {
		CELULAR = cELULAR;
	}

	/**
	 * @return the dDD1REC
	 */
	public String getDDD1REC() {
		return DDD1REC;
	}

	/**
	 * @param dDD1REC
	 *            the dDD1REC to set
	 */
	public void setDDD1REC(String dDD1REC) {
		DDD1REC = dDD1REC;
	}

	/**
	 * @return the fONEREC1
	 */
	public String getFONEREC1() {
		return FONEREC1;
	}

	/**
	 * @param fONEREC1
	 *            the fONEREC1 to set
	 */
	public void setFONEREC1(String fONEREC1) {
		FONEREC1 = fONEREC1;
	}

	/**
	 * @return the oBSFONE_REC1
	 */
	public String getOBSFONE_REC1() {
		return OBSFONE_REC1;
	}

	/**
	 * @param oBSFONE_REC1
	 *            the oBSFONE_REC1 to set
	 */
	public void setOBSFONE_REC1(String oBSFONE_REC1) {
		OBSFONE_REC1 = oBSFONE_REC1;
	}

	/**
	 * @return the nOME_SOC_2
	 */
	public String getNOME_SOC_2() {
		return NOME_SOC_2;
	}

	/**
	 * @param nOME_SOC_2
	 *            the nOME_SOC_2 to set
	 */
	public void setNOME_SOC_2(String nOME_SOC_2) {
		NOME_SOC_2 = nOME_SOC_2;
	}

	/**
	 * @return the qUALIDADE2
	 */
	public String getQUALIDADE2() {
		return QUALIDADE2;
	}

	/**
	 * @param qUALIDADE2
	 *            the qUALIDADE2 to set
	 */
	public void setQUALIDADE2(String qUALIDADE2) {
		QUALIDADE2 = qUALIDADE2;
	}

	/**
	 * @return the nAC_02
	 */
	public String getNAC_02() {
		return NAC_02;
	}

	/**
	 * @param nAC_02
	 *            the nAC_02 to set
	 */
	public void setNAC_02(String nAC_02) {
		NAC_02 = nAC_02;
	}

	/**
	 * @return the nATURALIDADE_2
	 */
	public String getNATURALIDADE_2() {
		return NATURALIDADE_2;
	}

	/**
	 * @param nATURALIDADE_2
	 *            the nATURALIDADE_2 to set
	 */
	public void setNATURALIDADE_2(String nATURALIDADE_2) {
		NATURALIDADE_2 = nATURALIDADE_2;
	}

	/**
	 * @return the eST_CIVEL_02
	 */
	public String getEST_CIVEL_02() {
		return EST_CIVEL_02;
	}

	/**
	 * @param eST_CIVEL_02
	 *            the eST_CIVEL_02 to set
	 */
	public void setEST_CIVEL_02(String eST_CIVEL_02) {
		EST_CIVEL_02 = eST_CIVEL_02;
	}

	/**
	 * @return the mAIOR_EMANCIPADO_2
	 */
	public String getMAIOR_EMANCIPADO_2() {
		return MAIOR_EMANCIPADO_2;
	}

	/**
	 * @param mAIOR_EMANCIPADO_2
	 *            the mAIOR_EMANCIPADO_2 to set
	 */
	public void setMAIOR_EMANCIPADO_2(String mAIOR_EMANCIPADO_2) {
		MAIOR_EMANCIPADO_2 = mAIOR_EMANCIPADO_2;
	}

	/**
	 * @return the pROF_02
	 */
	public String getPROF_02() {
		return PROF_02;
	}

	/**
	 * @param pROF_02
	 *            the pROF_02 to set
	 */
	public void setPROF_02(String pROF_02) {
		PROF_02 = pROF_02;
	}

	/**
	 * @return the eND_SOC_2
	 */
	public String getEND_SOC_2() {
		return END_SOC_2;
	}

	/**
	 * @param eND_SOC_2
	 *            the eND_SOC_2 to set
	 */
	public void setEND_SOC_2(String eND_SOC_2) {
		END_SOC_2 = eND_SOC_2;
	}

	/**
	 * @return the bAIRRO2
	 */
	public String getBAIRRO2() {
		return BAIRRO2;
	}

	/**
	 * @param bAIRRO2
	 *            the bAIRRO2 to set
	 */
	public void setBAIRRO2(String bAIRRO2) {
		BAIRRO2 = bAIRRO2;
	}

	/**
	 * @return the cIDADE2
	 */
	public String getCIDADE2() {
		return CIDADE2;
	}

	/**
	 * @param cIDADE2
	 *            the cIDADE2 to set
	 */
	public void setCIDADE2(String cIDADE2) {
		CIDADE2 = cIDADE2;
	}

	/**
	 * @return the eST2
	 */
	public String getEST2() {
		return EST2;
	}

	/**
	 * @param eST2
	 *            the eST2 to set
	 */
	public void setEST2(String eST2) {
		EST2 = eST2;
	}

	/**
	 * @return the cEP2
	 */
	public String getCEP2() {
		return CEP2;
	}

	/**
	 * @param cEP2
	 *            the cEP2 to set
	 */
	public void setCEP2(String cEP2) {
		CEP2 = cEP2;
	}

	/**
	 * @return the dIA_NASC2
	 */
	public String getDIA_NASC2() {
		return DIA_NASC2;
	}

	/**
	 * @param dIA_NASC2
	 *            the dIA_NASC2 to set
	 */
	public void setDIA_NASC2(String dIA_NASC2) {
		DIA_NASC2 = dIA_NASC2;
	}

	/**
	 * @return the mES_NASC2
	 */
	public String getMES_NASC2() {
		return MES_NASC2;
	}

	/**
	 * @param mES_NASC2
	 *            the mES_NASC2 to set
	 */
	public void setMES_NASC2(String mES_NASC2) {
		MES_NASC2 = mES_NASC2;
	}

	/**
	 * @return the aNO_NASC2
	 */
	public String getANO_NASC2() {
		return ANO_NASC2;
	}

	/**
	 * @param aNO_NASC2
	 *            the aNO_NASC2 to set
	 */
	public void setANO_NASC2(String aNO_NASC2) {
		ANO_NASC2 = aNO_NASC2;
	}

	/**
	 * @return the pIS_SOC2
	 */
	public String getPIS_SOC2() {
		return PIS_SOC2;
	}

	/**
	 * @param pIS_SOC2
	 *            the pIS_SOC2 to set
	 */
	public void setPIS_SOC2(String pIS_SOC2) {
		PIS_SOC2 = pIS_SOC2;
	}

	/**
	 * @return the rG2
	 */
	public String getRG2() {
		return RG2;
	}

	/**
	 * @param rG2
	 *            the rG2 to set
	 */
	public void setRG2(String rG2) {
		RG2 = rG2;
	}

	/**
	 * @return the oRGAO2
	 */
	public String getORGAO2() {
		return ORGAO2;
	}

	/**
	 * @param oRGAO2
	 *            the oRGAO2 to set
	 */
	public void setORGAO2(String oRGAO2) {
		ORGAO2 = oRGAO2;
	}

	/**
	 * @return the eSTD2
	 */
	public String getESTD2() {
		return ESTD2;
	}

	/**
	 * @param eSTD2
	 *            the eSTD2 to set
	 */
	public void setESTD2(String eSTD2) {
		ESTD2 = eSTD2;
	}

	/**
	 * @return the dATA_EXP2
	 */
	public String getDATA_EXP2() {
		return DATA_EXP2;
	}

	/**
	 * @param dATA_EXP2
	 *            the dATA_EXP2 to set
	 */
	public void setDATA_EXP2(String dATA_EXP2) {
		DATA_EXP2 = dATA_EXP2;
	}

	/**
	 * @return the cPF2
	 */
	public String getCPF2() {
		return CPF2;
	}

	/**
	 * @param cPF2
	 *            the cPF2 to set
	 */
	public void setCPF2(String cPF2) {
		CPF2 = cPF2;
	}

	/**
	 * @return the dDD2RES
	 */
	public String getDDD2RES() {
		return DDD2RES;
	}

	/**
	 * @param dDD2RES
	 *            the dDD2RES to set
	 */
	public void setDDD2RES(String dDD2RES) {
		DDD2RES = dDD2RES;
	}

	/**
	 * @return the fONERES2
	 */
	public String getFONERES2() {
		return FONERES2;
	}

	/**
	 * @param fONERES2
	 *            the fONERES2 to set
	 */
	public void setFONERES2(String fONERES2) {
		FONERES2 = fONERES2;
	}

	/**
	 * @return the dDD2COML
	 */
	public String getDDD2COML() {
		return DDD2COML;
	}

	/**
	 * @param dDD2COML
	 *            the dDD2COML to set
	 */
	public void setDDD2COML(String dDD2COML) {
		DDD2COML = dDD2COML;
	}

	/**
	 * @return the fONECOM2
	 */
	public String getFONECOM2() {
		return FONECOM2;
	}

	/**
	 * @param fONECOM2
	 *            the fONECOM2 to set
	 */
	public void setFONECOM2(String fONECOM2) {
		FONECOM2 = fONECOM2;
	}

	/**
	 * @return the dDD2CEL
	 */
	public String getDDD2CEL() {
		return DDD2CEL;
	}

	/**
	 * @param dDD2CEL
	 *            the dDD2CEL to set
	 */
	public void setDDD2CEL(String dDD2CEL) {
		DDD2CEL = dDD2CEL;
	}

	/**
	 * @return the cELULAR2
	 */
	public String getCELULAR2() {
		return CELULAR2;
	}

	/**
	 * @param cELULAR2
	 *            the cELULAR2 to set
	 */
	public void setCELULAR2(String cELULAR2) {
		CELULAR2 = cELULAR2;
	}

	/**
	 * @return the oBS
	 */
	public String getOBS() {
		return OBS;
	}

	/**
	 * @param oBS
	 *            the oBS to set
	 */
	public void setOBS(String oBS) {
		OBS = oBS;
	}

	/**
	 * @return the criadoEm
	 */
	public Calendar getCriadoEm() {
		return criadoEm;
	}

	/**
	 * @param criadoEm
	 *            the criadoEm to set
	 */
	public void setCriadoEm(Calendar criadoEm) {
		this.criadoEm = criadoEm;
	}

	/**
	 * @return the ultimaVersao
	 */
	public Calendar getUltimaVersao() {
		return ultimaVersao;
	}

	/**
	 * @param ultimaVersao
	 *            the ultimaVersao to set
	 */
	public void setUltimaVersao(Calendar ultimaVersao) {
		this.ultimaVersao = ultimaVersao;
	}

	/**
	 * @return the comentario
	 */
	public ClienteComentario getComentario() {
		return comentario;
	}

	/**
	 * @param comentario
	 *            the comentario to set
	 */
	public void setComentario(ClienteComentario comentario) {
		this.comentario = comentario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + COD;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (COD != other.COD)
			return false;
		return true;
	}
}
