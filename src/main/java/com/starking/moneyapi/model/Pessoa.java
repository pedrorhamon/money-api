package com.starking.moneyapi.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo")
	private Long codigo;
	
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
	
	@JsonIgnore
	@Transient
	public boolean isInativo() {
		return !this.ativo;
	}

}
