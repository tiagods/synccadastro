package com.prolink.synccadastro.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Aniversariante implements Serializable{

	public enum Socio {
		SOCIO1(1),SOCIO2(2);

		private int valor;

		Socio(int valor){
			this.valor = valor;
		}
		public int getValor() {
			return valor;
		}
	}

	private static final long serialVersionUID = 1L;
	private String nome;
	private String id;
	private String empresa;
	private String aniversario;
	private String status;
	private String email;
	private String telefone;
	private int tipoSocio;
	@JsonIgnore
	private LocalDate data;
	@JsonIgnore
	public Socio socio;
}
