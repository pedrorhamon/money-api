package com.starking.moneyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.moneyapi.model.Lancamento;

public interface LancamentoRepository extends  JpaRepository<Lancamento, Long>{
	
	Lancamento findOne(Long codigo);

}
