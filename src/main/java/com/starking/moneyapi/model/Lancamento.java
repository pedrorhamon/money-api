package com.starking.moneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.starking.moneyapi.model.enums.TipoLancamentoEnum;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.CacheStrategy;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lancamento")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(cacheStrategy = CacheStrategy.LAZY)
public class Lancamento {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull
	@Column(name = "dataLancamento")
	private LocalDate dataLancamento;
	
	@Column(name = "dataPagamento")
	private LocalDate dataPagamento;
	
	@NotNull
	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "observacao")
	private String observacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoLancamentoEnum tipoLancamentoEnum;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	private Pessoa pessoa;
}
