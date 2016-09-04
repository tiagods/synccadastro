package model;

import java.io.Serializable;

public class CadastroBean implements Serializable{
	/**
	 * 
	 */
	private int COD;
	private String STATUS="";
	private String COMPL_STS;
	private String ATENDIMENTO="";
	private String PROCESSOS="";
	private String SISTEMA="";
	private String MP="";
	private String TIPO="";
	private String REGIME_TRIBUTARIO="";
	private String QUANT_SOC_GER="";
	private String EMPRESA="";
	private String NOME="";
	private String ENDERECO="";
	private String BAIRRO="";
	private String CIDADE="";
	private String EST="";
	private String CEP="";
	private String CNPJ="";
	private String DATA_CNPJ="";
	private String IE="";
	private String VLR_CAPITAL="";
	private String VL_EXTENSO="";
	private String VL_COTA="";
	private String VL_EXT_COTA="";
	private String COTAS_TOTAL="";
	private String COTAS_EXTENSO="";
	private String PERC_CAP_1="";
	private String PERC_CAP_2="";
	private String PERC_CAP_DEMAIS="";
	private String SOMA_PERC_CAP="";
	private String COTAS_01="";
	private String COTAS_02="";
	private String PREF="";
	private String DATA_IM="";
	private String N_CONTRIB="";
	private String ALIQUOTA_DE_ISS="";
	private String ENC_MUNIC="";
	private String DT_DISTRATO="";
	private String NIRC="";
	private String N_REG_CART_OU_NIRE="";
	private String DATA_REG_CART_OU_JUCESP="";
	private String SIND_CNPJ="";
	private String ATIVIDADE="";
	private String SINDICATO="";
	private String CNAE1="";
	private String COMP_CNAE1="";
	private String DIG_CNAE1="";
	private String CNAE="";
	private String COMP_CNAE="";
	private String DIG_CNAE="";
	private String FPAS="";
	private String QUANT_SOCIOS="";
	private String DATA_INSS="";
	private String CONSULTORIA_INICIO="";
	private String ALOCADO="";
	private String INDICADO="";
	private String CONSULT_ATUAL="";
	private String APELIDO_1="";
	private String END_CORRESP="";
	private String BAIRRO_CO="";
	private String CID_CO="";
	private String EST_CO="";
	private String CEP_CO="";
	private String NOME_SOCIO1="";
	private String QUALIDADE1="";
	private String NAC_01="";
	private String NATURALIDADE_1="";
	private String EST_CIVEL_01="";
	private String MAIOR_EMANCIPADO_1="";
	private String PROF_01="";
	private String DIA_NASC1="";
	private String MES_NASC1="";
	private String ANO_NASC1="";
	private String PIS_SOC1="";
	private String END_SOC_1="";
	private String BAIRRO1="";
	private String CIDADE1="";
	private String EST1="";
	private String CEP1="";
	private String RG1="";
	private String ORGAO1="";
	private String ESTD1="";
	private String DATA_EXP1="";
	private String CPF1="";
	private String EMAIL_SOC_1="";
	private String EMAIL_FINANCEIRO="";
	private String DDD1="";
	private String FONERES1="";
	private String RES_RAMAL_1="";
	private String DDD1COML="";
	private String FONECOML1="";
	private String COML_RAMAL_1="";
	private String DDD1CEL="";
	private String CELULAR="";
	private String DDD1REC="";
	private String FONEREC1="";
	private String OBSFONE_REC1="";
	private String NOME_SOC_2="";
	private String QUALIDADE2="";
	private String NAC_02="";
	private String NATURALIDADE_2="";
	private String EST_CIVEL_02="";
	private String MAIOR_EMANCIPADO_2="";
	private String PROF_02="";
	private String END_SOC_2="";
	private String BAIRRO2="";
	private String CIDADE2="";
	private String EST2="";
	private String CEP2="";
	private String DIA_NASC2="";
	private String MES_NASC2="";
	private String ANO_NASC2="";
	private String PIS_SOC2="";
	private String RG2="";
	private String ORGAO2="";
	private String ESTD2="";
	private String DATA_EXP2="";
	private String CPF2="";
	private String DDD2RES="";
	private String FONERES2="";
	private String DDD2COML="";
	private String FONECOM2="";
	private String DDD2CEL="";
	private String CELULAR2="";
	private String DDD2REC="";
	private String FONEREC2="";
	private String OBSFONE_RC2="";
	public int getCOD() {
		return COD;
	}
	public void setCOD(int cOD) {
		COD = cOD;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getCOMPL_STS() {
		return COMPL_STS;
	}
	public void setCOMPL_STS(String cOMPL_STS) {
		COMPL_STS = cOMPL_STS;
	}
	public String getATENDIMENTO() {
		return ATENDIMENTO;
	}
	public void setATENDIMENTO(String aTENDIMENTO) {
		ATENDIMENTO = aTENDIMENTO;
	}
	public String getPROCESSOS() {
		return PROCESSOS;
	}
	public void setPROCESSOS(String pROCESSOS) {
		PROCESSOS = pROCESSOS;
	}
	public String getSISTEMA() {
		return SISTEMA;
	}
	public void setSISTEMA(String sISTEMA) {
		SISTEMA = sISTEMA;
	}
	public String getMP() {
		return MP;
	}
	public void setMP(String mP) {
		MP = mP;
	}
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	public String getREGIME_TRIBUTARIO() {
		return REGIME_TRIBUTARIO;
	}
	public void setREGIME_TRIBUTARIO(String rEGIME_TRIBUTARIO) {
		REGIME_TRIBUTARIO = rEGIME_TRIBUTARIO;
	}
	public String getQUANT_SOC_GER() {
		return QUANT_SOC_GER;
	}
	public void setQUANT_SOC_GER(String qUANT_SOC_GER) {
		QUANT_SOC_GER = qUANT_SOC_GER;
	}
	public String getEMPRESA() {
		return EMPRESA;
	}
	public void setEMPRESA(String eMPRESA) {
		EMPRESA = eMPRESA;
	}
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getENDERECO() {
		return ENDERECO;
	}
	public void setENDERECO(String eNDERECO) {
		ENDERECO = eNDERECO;
	}
	public String getBAIRRO() {
		return BAIRRO;
	}
	public void setBAIRRO(String bAIRRO) {
		BAIRRO = bAIRRO;
	}
	public String getCIDADE() {
		return CIDADE;
	}
	public void setCIDADE(String cIDADE) {
		CIDADE = cIDADE;
	}
	public String getEST() {
		return EST;
	}
	public void setEST(String eST) {
		EST = eST;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getDATA_CNPJ() {
		return DATA_CNPJ;
	}
	public void setDATA_CNPJ(String dATA_CNPJ) {
		DATA_CNPJ = dATA_CNPJ;
	}
	public String getIE() {
		return IE;
	}
	public void setIE(String iE) {
		IE = iE;
	}
	public String getVLR_CAPITAL() {
		return VLR_CAPITAL;
	}
	public void setVLR_CAPITAL(String vLR_CAPITAL) {
		VLR_CAPITAL = vLR_CAPITAL;
	}
	public String getVL_EXTENSO() {
		return VL_EXTENSO;
	}
	public void setVL_EXTENSO(String vL_EXTENSO) {
		VL_EXTENSO = vL_EXTENSO;
	}
	public String getVL_COTA() {
		return VL_COTA;
	}
	public void setVL_COTA(String vL_COTA) {
		VL_COTA = vL_COTA;
	}
	public String getVL_EXT_COTA() {
		return VL_EXT_COTA;
	}
	public void setVL_EXT_COTA(String vL_EXT_COTA) {
		VL_EXT_COTA = vL_EXT_COTA;
	}
	public String getCOTAS_TOTAL() {
		return COTAS_TOTAL;
	}
	public void setCOTAS_TOTAL(String cOTAS_TOTAL) {
		COTAS_TOTAL = cOTAS_TOTAL;
	}
	public String getCOTAS_EXTENSO() {
		return COTAS_EXTENSO;
	}
	public void setCOTAS_EXTENSO(String cOTAS_EXTENSO) {
		COTAS_EXTENSO = cOTAS_EXTENSO;
	}
	public String getPERC_CAP_1() {
		return PERC_CAP_1;
	}
	public void setPERC_CAP_1(String pERC_CAP_1) {
		PERC_CAP_1 = pERC_CAP_1;
	}
	public String getPERC_CAP_2() {
		return PERC_CAP_2;
	}
	public void setPERC_CAP_2(String pERC_CAP_2) {
		PERC_CAP_2 = pERC_CAP_2;
	}
	public String getPERC_CAP_DEMAIS() {
		return PERC_CAP_DEMAIS;
	}
	public void setPERC_CAP_DEMAIS(String pERC_CAP_DEMAIS) {
		PERC_CAP_DEMAIS = pERC_CAP_DEMAIS;
	}
	public String getSOMA_PERC_CAP() {
		return SOMA_PERC_CAP;
	}
	public void setSOMA_PERC_CAP(String sOMA_PERC_CAP) {
		SOMA_PERC_CAP = sOMA_PERC_CAP;
	}
	public String getCOTAS_01() {
		return COTAS_01;
	}
	public void setCOTAS_01(String cOTAS_01) {
		COTAS_01 = cOTAS_01;
	}
	public String getCOTAS_02() {
		return COTAS_02;
	}
	public void setCOTAS_02(String cOTAS_02) {
		COTAS_02 = cOTAS_02;
	}
	public String getPREF() {
		return PREF;
	}
	public void setPREF(String pREF) {
		PREF = pREF;
	}
	public String getDATA_IM() {
		return DATA_IM;
	}
	public void setDATA_IM(String dATA_IM) {
		DATA_IM = dATA_IM;
	}
	public String getN_CONTRIB() {
		return N_CONTRIB;
	}
	public void setN_CONTRIB(String n_CONTRIB) {
		N_CONTRIB = n_CONTRIB;
	}
	public String getALIQUOTA_DE_ISS() {
		return ALIQUOTA_DE_ISS;
	}
	public void setALIQUOTA_DE_ISS(String aLIQUOTA_DE_ISS) {
		ALIQUOTA_DE_ISS = aLIQUOTA_DE_ISS;
	}
	public String getENC_MUNIC() {
		return ENC_MUNIC;
	}
	public void setENC_MUNIC(String eNC_MUNIC) {
		ENC_MUNIC = eNC_MUNIC;
	}
	public String getDT_DISTRATO() {
		return DT_DISTRATO;
	}
	public void setDT_DISTRATO(String dT_DISTRATO) {
		DT_DISTRATO = dT_DISTRATO;
	}
	public String getNIRC() {
		return NIRC;
	}
	public void setNIRC(String nIRC) {
		NIRC = nIRC;
	}
	public String getN_REG_CART_OU_NIRE() {
		return N_REG_CART_OU_NIRE;
	}
	public void setN_REG_CART_OU_NIRE(String n_REG_CART_OU_NIRE) {
		N_REG_CART_OU_NIRE = n_REG_CART_OU_NIRE;
	}
	public String getDATA_REG_CART_OU_JUCESP() {
		return DATA_REG_CART_OU_JUCESP;
	}
	public void setDATA_REG_CART_OU_JUCESP(String dATA_REG_CART_OU_JUCESP) {
		DATA_REG_CART_OU_JUCESP = dATA_REG_CART_OU_JUCESP;
	}
	public String getSIND_CNPJ() {
		return SIND_CNPJ;
	}
	public void setSIND_CNPJ(String sIND_CNPJ) {
		SIND_CNPJ = sIND_CNPJ;
	}
	public String getATIVIDADE() {
		return ATIVIDADE;
	}
	public void setATIVIDADE(String aTIVIDADE) {
		ATIVIDADE = aTIVIDADE;
	}
	public String getSINDICATO() {
		return SINDICATO;
	}
	public void setSINDICATO(String sINDICATO) {
		SINDICATO = sINDICATO;
	}
	public String getCNAE1() {
		return CNAE1;
	}
	public void setCNAE1(String cNAE1) {
		CNAE1 = cNAE1;
	}
	public String getCOMP_CNAE1() {
		return COMP_CNAE1;
	}
	public void setCOMP_CNAE1(String cOMP_CNAE1) {
		COMP_CNAE1 = cOMP_CNAE1;
	}
	public String getDIG_CNAE1() {
		return DIG_CNAE1;
	}
	public void setDIG_CNAE1(String dIG_CNAE1) {
		DIG_CNAE1 = dIG_CNAE1;
	}
	public String getCNAE() {
		return CNAE;
	}
	public void setCNAE(String cNAE) {
		CNAE = cNAE;
	}
	public String getCOMP_CNAE() {
		return COMP_CNAE;
	}
	public void setCOMP_CNAE(String cOMP_CNAE) {
		COMP_CNAE = cOMP_CNAE;
	}
	public String getDIG_CNAE() {
		return DIG_CNAE;
	}
	public void setDIG_CNAE(String dIG_CNAE) {
		DIG_CNAE = dIG_CNAE;
	}
	public String getFPAS() {
		return FPAS;
	}
	public void setFPAS(String fPAS) {
		FPAS = fPAS;
	}
	public String getQUANT_SOCIOS() {
		return QUANT_SOCIOS;
	}
	public void setQUANT_SOCIOS(String qUANT_SOCIOS) {
		QUANT_SOCIOS = qUANT_SOCIOS;
	}
	public String getDATA_INSS() {
		return DATA_INSS;
	}
	public void setDATA_INSS(String dATA_INSS) {
		DATA_INSS = dATA_INSS;
	}
	public String getCONSULTORIA_INICIO() {
		return CONSULTORIA_INICIO;
	}
	public void setCONSULTORIA_INICIO(String cONSULTORIA_INICIO) {
		CONSULTORIA_INICIO = cONSULTORIA_INICIO;
	}
	public String getALOCADO() {
		return ALOCADO;
	}
	public void setALOCADO(String aLOCADO) {
		ALOCADO = aLOCADO;
	}
	public String getINDICADO() {
		return INDICADO;
	}
	public void setINDICADO(String iNDICADO) {
		INDICADO = iNDICADO;
	}
	public String getCONSULT_ATUAL() {
		return CONSULT_ATUAL;
	}
	public void setCONSULT_ATUAL(String cONSULT_ATUAL) {
		CONSULT_ATUAL = cONSULT_ATUAL;
	}
	public String getAPELIDO_1() {
		return APELIDO_1;
	}
	public void setAPELIDO_1(String aPELIDO_1) {
		APELIDO_1 = aPELIDO_1;
	}
	public String getEND_CORRESP() {
		return END_CORRESP;
	}
	public void setEND_CORRESP(String eND_CORRESP) {
		END_CORRESP = eND_CORRESP;
	}
	public String getBAIRRO_CO() {
		return BAIRRO_CO;
	}
	public void setBAIRRO_CO(String bAIRRO_CO) {
		BAIRRO_CO = bAIRRO_CO;
	}
	public String getCID_CO() {
		return CID_CO;
	}
	public void setCID_CO(String cID_CO) {
		CID_CO = cID_CO;
	}
	public String getEST_CO() {
		return EST_CO;
	}
	public void setEST_CO(String eST_CO) {
		EST_CO = eST_CO;
	}
	public String getCEP_CO() {
		return CEP_CO;
	}
	public void setCEP_CO(String cEP_CO) {
		CEP_CO = cEP_CO;
	}
	public String getNOME_SOCIO1() {
		return NOME_SOCIO1;
	}
	public void setNOME_SOCIO1(String nOME_SOCIO1) {
		NOME_SOCIO1 = nOME_SOCIO1;
	}
	public String getQUALIDADE1() {
		return QUALIDADE1;
	}
	public void setQUALIDADE1(String qUALIDADE1) {
		QUALIDADE1 = qUALIDADE1;
	}
	public String getNAC_01() {
		return NAC_01;
	}
	public void setNAC_01(String nAC_01) {
		NAC_01 = nAC_01;
	}
	public String getNATURALIDADE_1() {
		return NATURALIDADE_1;
	}
	public void setNATURALIDADE_1(String nATURALIDADE_1) {
		NATURALIDADE_1 = nATURALIDADE_1;
	}
	public String getEST_CIVEL_01() {
		return EST_CIVEL_01;
	}
	public void setEST_CIVEL_01(String eST_CIVEL_01) {
		EST_CIVEL_01 = eST_CIVEL_01;
	}
	public String getMAIOR_EMANCIPADO_1() {
		return MAIOR_EMANCIPADO_1;
	}
	public void setMAIOR_EMANCIPADO_1(String mAIOR_EMANCIPADO_1) {
		MAIOR_EMANCIPADO_1 = mAIOR_EMANCIPADO_1;
	}
	public String getPROF_01() {
		return PROF_01;
	}
	public void setPROF_01(String pROF_01) {
		PROF_01 = pROF_01;
	}
	public String getDIA_NASC1() {
		return DIA_NASC1;
	}
	public void setDIA_NASC1(String dIA_NASC1) {
		DIA_NASC1 = dIA_NASC1;
	}
	public String getMES_NASC1() {
		return MES_NASC1;
	}
	public void setMES_NASC1(String mES_NASC1) {
		MES_NASC1 = mES_NASC1;
	}
	public String getANO_NASC1() {
		return ANO_NASC1;
	}
	public void setANO_NASC1(String aNO_NASC1) {
		ANO_NASC1 = aNO_NASC1;
	}
	public String getPIS_SOC1() {
		return PIS_SOC1;
	}
	public void setPIS_SOC1(String pIS_SOC1) {
		PIS_SOC1 = pIS_SOC1;
	}
	public String getEND_SOC_1() {
		return END_SOC_1;
	}
	public void setEND_SOC_1(String eND_SOC_1) {
		END_SOC_1 = eND_SOC_1;
	}
	public String getBAIRRO1() {
		return BAIRRO1;
	}
	public void setBAIRRO1(String bAIRRO1) {
		BAIRRO1 = bAIRRO1;
	}
	public String getCIDADE1() {
		return CIDADE1;
	}
	public void setCIDADE1(String cIDADE1) {
		CIDADE1 = cIDADE1;
	}
	public String getEST1() {
		return EST1;
	}
	public void setEST1(String eST1) {
		EST1 = eST1;
	}
	public String getCEP1() {
		return CEP1;
	}
	public void setCEP1(String cEP1) {
		CEP1 = cEP1;
	}
	public String getRG1() {
		return RG1;
	}
	public void setRG1(String rG1) {
		RG1 = rG1;
	}
	public String getORGAO1() {
		return ORGAO1;
	}
	public void setORGAO1(String oRGAO1) {
		ORGAO1 = oRGAO1;
	}
	public String getESTD1() {
		return ESTD1;
	}
	public void setESTD1(String eSTD1) {
		ESTD1 = eSTD1;
	}
	public String getDATA_EXP1() {
		return DATA_EXP1;
	}
	public void setDATA_EXP1(String dATA_EXP1) {
		DATA_EXP1 = dATA_EXP1;
	}
	public String getCPF1() {
		return CPF1;
	}
	public void setCPF1(String cPF1) {
		CPF1 = cPF1;
	}
	public String getEMAIL_SOC_1() {
		return EMAIL_SOC_1;
	}
	public void setEMAIL_SOC_1(String eMAIL_SOC_1) {
		EMAIL_SOC_1 = eMAIL_SOC_1;
	}
	public String getEMAIL_FINANCEIRO() {
		return EMAIL_FINANCEIRO;
	}
	public void setEMAIL_FINANCEIRO(String eMAIL_FINANCEIRO) {
		EMAIL_FINANCEIRO = eMAIL_FINANCEIRO;
	}
	public String getDDD1() {
		return DDD1;
	}
	public void setDDD1(String dDD1) {
		DDD1 = dDD1;
	}
	public String getFONERES1() {
		return FONERES1;
	}
	public void setFONERES1(String fONERES1) {
		FONERES1 = fONERES1;
	}
	public String getRES_RAMAL_1() {
		return RES_RAMAL_1;
	}
	public void setRES_RAMAL_1(String rES_RAMAL_1) {
		RES_RAMAL_1 = rES_RAMAL_1;
	}
	public String getDDD1COML() {
		return DDD1COML;
	}
	public void setDDD1COML(String dDD1COML) {
		DDD1COML = dDD1COML;
	}
	public String getFONECOML1() {
		return FONECOML1;
	}
	public void setFONECOML1(String fONECOML1) {
		FONECOML1 = fONECOML1;
	}
	public String getCOML_RAMAL_1() {
		return COML_RAMAL_1;
	}
	public void setCOML_RAMAL_1(String cOML_RAMAL_1) {
		COML_RAMAL_1 = cOML_RAMAL_1;
	}
	public String getDDD1CEL() {
		return DDD1CEL;
	}
	public void setDDD1CEL(String dDD1CEL) {
		DDD1CEL = dDD1CEL;
	}
	public String getCELULAR() {
		return CELULAR;
	}
	public void setCELULAR(String cELULAR) {
		CELULAR = cELULAR;
	}
	public String getDDD1REC() {
		return DDD1REC;
	}
	public void setDDD1REC(String dDD1REC) {
		DDD1REC = dDD1REC;
	}
	public String getFONEREC1() {
		return FONEREC1;
	}
	public void setFONEREC1(String fONEREC1) {
		FONEREC1 = fONEREC1;
	}
	public String getOBSFONE_REC1() {
		return OBSFONE_REC1;
	}
	public void setOBSFONE_REC1(String oBSFONE_REC1) {
		OBSFONE_REC1 = oBSFONE_REC1;
	}
	public String getNOME_SOC_2() {
		return NOME_SOC_2;
	}
	public void setNOME_SOC_2(String nOME_SOC_2) {
		NOME_SOC_2 = nOME_SOC_2;
	}
	public String getQUALIDADE2() {
		return QUALIDADE2;
	}
	public void setQUALIDADE2(String qUALIDADE2) {
		QUALIDADE2 = qUALIDADE2;
	}
	public String getNAC_02() {
		return NAC_02;
	}
	public void setNAC_02(String nAC_02) {
		NAC_02 = nAC_02;
	}
	public String getNATURALIDADE_2() {
		return NATURALIDADE_2;
	}
	public void setNATURALIDADE_2(String nATURALIDADE_2) {
		NATURALIDADE_2 = nATURALIDADE_2;
	}
	public String getEST_CIVEL_02() {
		return EST_CIVEL_02;
	}
	public void setEST_CIVEL_02(String eST_CIVEL_02) {
		EST_CIVEL_02 = eST_CIVEL_02;
	}
	public String getMAIOR_EMANCIPADO_2() {
		return MAIOR_EMANCIPADO_2;
	}
	public void setMAIOR_EMANCIPADO_2(String mAIOR_EMANCIPADO_2) {
		MAIOR_EMANCIPADO_2 = mAIOR_EMANCIPADO_2;
	}
	public String getPROF_02() {
		return PROF_02;
	}
	public void setPROF_02(String pROF_02) {
		PROF_02 = pROF_02;
	}
	public String getEND_SOC_2() {
		return END_SOC_2;
	}
	public void setEND_SOC_2(String eND_SOC_2) {
		END_SOC_2 = eND_SOC_2;
	}
	public String getBAIRRO2() {
		return BAIRRO2;
	}
	public void setBAIRRO2(String bAIRRO2) {
		BAIRRO2 = bAIRRO2;
	}
	public String getCIDADE2() {
		return CIDADE2;
	}
	public void setCIDADE2(String cIDADE2) {
		CIDADE2 = cIDADE2;
	}
	public String getEST2() {
		return EST2;
	}
	public void setEST2(String eST2) {
		EST2 = eST2;
	}
	public String getCEP2() {
		return CEP2;
	}
	public void setCEP2(String cEP2) {
		CEP2 = cEP2;
	}
	public String getDIA_NASC2() {
		return DIA_NASC2;
	}
	public void setDIA_NASC2(String dIA_NASC2) {
		DIA_NASC2 = dIA_NASC2;
	}
	public String getMES_NASC2() {
		return MES_NASC2;
	}
	public void setMES_NASC2(String mES_NASC2) {
		MES_NASC2 = mES_NASC2;
	}
	public String getANO_NASC2() {
		return ANO_NASC2;
	}
	public void setANO_NASC2(String aNO_NASC2) {
		ANO_NASC2 = aNO_NASC2;
	}
	public String getPIS_SOC2() {
		return PIS_SOC2;
	}
	public void setPIS_SOC2(String pIS_SOC2) {
		PIS_SOC2 = pIS_SOC2;
	}
	public String getRG2() {
		return RG2;
	}
	public void setRG2(String rG2) {
		RG2 = rG2;
	}
	public String getORGAO2() {
		return ORGAO2;
	}
	public void setORGAO2(String oRGAO2) {
		ORGAO2 = oRGAO2;
	}
	public String getESTD2() {
		return ESTD2;
	}
	public void setESTD2(String eSTD2) {
		ESTD2 = eSTD2;
	}
	public String getDATA_EXP2() {
		return DATA_EXP2;
	}
	public void setDATA_EXP2(String dATA_EXP2) {
		DATA_EXP2 = dATA_EXP2;
	}
	public String getCPF2() {
		return CPF2;
	}
	public void setCPF2(String cPF2) {
		CPF2 = cPF2;
	}
	public String getDDD2RES() {
		return DDD2RES;
	}
	public void setDDD2RES(String dDD2RES) {
		DDD2RES = dDD2RES;
	}
	public String getFONERES2() {
		return FONERES2;
	}
	public void setFONERES2(String fONERES2) {
		FONERES2 = fONERES2;
	}
	public String getDDD2COML() {
		return DDD2COML;
	}
	public void setDDD2COML(String dDD2COML) {
		DDD2COML = dDD2COML;
	}
	public String getFONECOM2() {
		return FONECOM2;
	}
	public void setFONECOM2(String fONECOM2) {
		FONECOM2 = fONECOM2;
	}
	public String getDDD2CEL() {
		return DDD2CEL;
	}
	public void setDDD2CEL(String dDD2CEL) {
		DDD2CEL = dDD2CEL;
	}
	public String getCELULAR2() {
		return CELULAR2;
	}
	public void setCELULAR2(String cELULAR2) {
		CELULAR2 = cELULAR2;
	}
	public String getDDD2REC() {
		return DDD2REC;
	}
	public void setDDD2REC(String dDD2REC) {
		DDD2REC = dDD2REC;
	}
	public String getFONEREC2() {
		return FONEREC2;
	}
	public void setFONEREC2(String fONEREC2) {
		FONEREC2 = fONEREC2;
	}
	public String getOBSFONE_RC2() {
		return OBSFONE_RC2;
	}
	public void setOBSFONE_RC2(String oBSFONE_RC2) {
		OBSFONE_RC2 = oBSFONE_RC2;
	}

}
