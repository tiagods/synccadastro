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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of="COD")
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id private int COD;
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
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm;
	@Temporal(TemporalType.TIMESTAMP)
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
}
