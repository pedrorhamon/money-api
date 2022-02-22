package com.starking.moneyapi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.moneyapi.model.Pessoa;
import com.starking.moneyapi.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return this.pessoaRepository.findAll();
	}

	@Transactional
	public Pessoa criar(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}
	
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = atualizarPessoa(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return this.pessoaRepository.save(pessoaSalva);		
	}


	@Transactional(readOnly = true)
	public void deletar(Long id) {
		this.pessoaRepository.deleteById(id);
	}

	public void atualizarPropriedade(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = atualizarPessoa(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa atualizarPessoa(Long codigo) {
		Pessoa pessoaSalva = this.pessoaRepository.findOne(codigo);
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
	
}
