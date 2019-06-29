package com.prolink.synccadastro.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Aniversariante implements Serializable{
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
}
