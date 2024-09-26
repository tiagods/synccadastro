package com.prolink.synccadastro.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of="COD")
@Entity
@Table(name = "cliente_comentario")
public class ClienteComentario implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int ID;
	private String COD;

	@Column(columnDefinition = "text")
	private String STATUS;

	@Column(columnDefinition = "text")
	private String COMPL_STS;

	@Column(columnDefinition = "text")
	private String ATENDIMENTO;

	@Column(columnDefinition = "text")
	private String PROCESSOS;

	@Column(columnDefinition = "text")
	private String SISTEMA;

	@Column(columnDefinition = "text")
	private String MP;
	@Column(columnDefinition = "text")

	private String TIPO;
	@Column(columnDefinition = "text")

	private String REGIME_TRIBUTARIO;
	@Column(columnDefinition = "text")

	private String QUANT_SOC_GER;
	@Column(columnDefinition = "text")

	private String EMPRESA;
	@Column(columnDefinition = "text")

	private String NOME;
	@Column(columnDefinition = "text")

	private String ENDERECO;
	@Column(columnDefinition = "text")

	private String BAIRRO;
	@Column(columnDefinition = "text")

	private String CIDADE;
	@Column(columnDefinition = "text")

	private String EST;
	@Column(columnDefinition = "text")

	private String CEP;
	@Column(columnDefinition = "text")

	private String CNPJ;
	@Column(columnDefinition = "text")

	private String DATA_CNPJ;
	@Column(columnDefinition = "text")

	private String IE;
	@Column(columnDefinition = "text")

	private String VLR_CAPITAL;
	@Column(columnDefinition = "text")

	private String VL_EXTENSO;
	@Column(columnDefinition = "text")

	private String VL_COTA;
	@Column(columnDefinition = "text")

	private String VL_EXT_COTA;
	@Column(columnDefinition = "text")

	private String COTAS_TOTAL;
	@Column(columnDefinition = "text")

	private String COTAS_EXTENSO;
	@Column(columnDefinition = "text")

	private String PERC_CAP_1;
	@Column(columnDefinition = "text")

	private String PERC_CAP_2;
	@Column(columnDefinition = "text")

	private String PERC_CAP_DEMAIS;
	@Column(columnDefinition = "text")

	private String SOMA_PERC_CAP;
	@Column(columnDefinition = "text")

	private String COTAS_01;
	@Column(columnDefinition = "text")

	private String COTAS_02;
	@Column(columnDefinition = "text")

	private String PREF;
	@Column(columnDefinition = "text")

	private String DATA_IM;
	@Column(columnDefinition = "text")

	private String ALIQUOTA_DE_ISS;
	@Column(columnDefinition = "text")

	private String ENC_MUNIC;
	@Column(columnDefinition = "text")

	private String DT_DISTRATO;
	@Column(columnDefinition = "text")

	private String NIRC;
	@Column(columnDefinition = "text")

	private String N_REG_CART_OU_NIRE;
	@Column(columnDefinition = "text")

	private String DATA_REG_CART_OU_JUCESP;
	@Column(columnDefinition = "text")

	private String SIND_CNPJ;
	@Column(columnDefinition = "text")

	private String ATIVIDADE;
	@Column(columnDefinition = "text")

	private String SINDICATO;
	@Column(columnDefinition = "text")

	private String CNAE1;
	@Column(columnDefinition = "text")

	private String COMP_CNAE1;
	@Column(columnDefinition = "text")

	private String DIG_CNAE1;
	@Column(columnDefinition = "text")

	private String CNAE;
	@Column(columnDefinition = "text")

	private String COMP_CNAE;
	@Column(columnDefinition = "text")

	private String DIG_CNAE;
	@Column(columnDefinition = "text")

	private String FPAS;
	@Column(columnDefinition = "text")

	private String QUANT_SOCIOS;
	@Column(columnDefinition = "text")

	private String CONSULTORIA;
	@Column(columnDefinition = "text")

	private String INDICADO;
	@Column(columnDefinition = "text")

	private String APELIDO_1;
	@Column(columnDefinition = "text")

	private String END_CORRESP;
	@Column(columnDefinition = "text")

	private String BAIRRO_CO;
	@Column(columnDefinition = "text")

	private String CID_CO;
	@Column(columnDefinition = "text")

	private String EST_CO;
	@Column(columnDefinition = "text")

	private String CEP_CO;
	@Column(columnDefinition = "text")

	private String NOME_SOCIO1;
	@Column(columnDefinition = "text")

	private String QUALIDADE1;
	@Column(columnDefinition = "text")

	private String NAC_01;
	@Column(columnDefinition = "text")

	private String NATURALIDADE_1;
	@Column(columnDefinition = "text")

	private String EST_CIVEL_01;
	@Column(columnDefinition = "text")

	private String MAIOR_EMANCIPADO_1;
	@Column(columnDefinition = "text")

	private String PROF_01;
	@Column(columnDefinition = "text")

	private String DIA_NASC1;
	@Column(columnDefinition = "text")

	private String MES_NASC1;
	@Column(columnDefinition = "text")

	private String ANO_NASC1;
	@Column(columnDefinition = "text")

	private String PIS_SOC1;
	@Column(columnDefinition = "text")

	private String END_SOC_1;
	@Column(columnDefinition = "text")

	private String BAIRRO1;
	@Column(columnDefinition = "text")

	private String CIDADE1;
	@Column(columnDefinition = "text")

	private String EST1;
	@Column(columnDefinition = "text")

	private String CEP1;
	@Column(columnDefinition = "text")

	private String RG1;
	@Column(columnDefinition = "text")

	private String ORGAO1;
	@Column(columnDefinition = "text")

	private String ESTD1;
	@Column(columnDefinition = "text")

	private String DATA_EXP1;
	@Column(columnDefinition = "text")

	private String CPF1;
	@Column(columnDefinition = "text")

	private String EMAIL_SOC_1;
	@Column(columnDefinition = "text")

	private String EMAIL_FINANCEIRO;
	@Column(columnDefinition = "text")

	private String DDD1;
	@Column(columnDefinition = "text")

	private String FONERES1;
	@Column(columnDefinition = "text")

	private String RES_RAMAL_1;
	@Column(columnDefinition = "text")

	private String DDD1COML;
	@Column(columnDefinition = "text")

	private String FONECOML1;
	@Column(columnDefinition = "text")

	private String COML_RAMAL_1;
	@Column(columnDefinition = "text")

	private String DDD1CEL;
	@Column(columnDefinition = "text")

	private String CELULAR;
	@Column(columnDefinition = "text")

	private String DDD1REC;
	@Column(columnDefinition = "text")

	private String FONEREC1;
	@Column(columnDefinition = "text")

	private String OBSFONE_REC1;
	@Column(columnDefinition = "text")

	private String NOME_SOC_2;
	@Column(columnDefinition = "text")

	private String QUALIDADE2;
	@Column(columnDefinition = "text")

	private String NAC_02;
	@Column(columnDefinition = "text")

	private String NATURALIDADE_2;
	@Column(columnDefinition = "text")

	private String EST_CIVEL_02;
	@Column(columnDefinition = "text")

	private String MAIOR_EMANCIPADO_2;
	@Column(columnDefinition = "text")

	private String PROF_02;
	@Column(columnDefinition = "text")

	private String END_SOC_2;
	@Column(columnDefinition = "text")

	private String BAIRRO2;
	@Column(columnDefinition = "text")

	private String CIDADE2;
	@Column(columnDefinition = "text")

	private String EST2;
	@Column(columnDefinition = "text")

	private String CEP2;
	@Column(columnDefinition = "text")

	private String DIA_NASC2;
	@Column(columnDefinition = "text")

	private String MES_NASC2;
	@Column(columnDefinition = "text")

	private String ANO_NASC2;
	@Column(columnDefinition = "text")

	private String PIS_SOC2;
	@Column(columnDefinition = "text")

	private String RG2;
	@Column(columnDefinition = "text")

	private String ORGAO2;
	@Column(columnDefinition = "text")

	private String ESTD2;
	@Column(columnDefinition = "text")

	private String DATA_EXP2;
	@Column(columnDefinition = "text")

	private String CPF2;
	@Column(columnDefinition = "text")

	private String DDD2RES;
	@Column(columnDefinition = "text")

	private String FONERES2;
	@Column(columnDefinition = "text")

	private String DDD2COML;
	@Column(columnDefinition = "text")

	private String FONECOM2;
	@Column(columnDefinition = "text")

	private String DDD2CEL;
	@Column(columnDefinition = "text")

	private String CELULAR2;
	@Column(columnDefinition = "text")

	private String OBS;
	@Column(columnDefinition = "text")

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar criadoEm;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar ultimaVersao;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

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
