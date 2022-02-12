package com.starking.moneyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.moneyapi.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String>{
	
	Pessoa findOne(Long codigo);

}
