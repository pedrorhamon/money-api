package com.starking.moneyapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.moneyapi.model.Pessoa;
import com.starking.moneyapi.repositories.PessoaRepository;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	@Transactional
	public Pessoa criar(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}

	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.pessoaRepository.deleteById(id);
	}
}
