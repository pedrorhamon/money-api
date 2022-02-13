package com.starking.moneyapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
public class Pessoa {
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "status")
	@NotNull
	private boolean ativo;
	
	@Embedded
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

}
