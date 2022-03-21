package com.starking.moneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.moneyapi.exception.PessoaInexistenteOuInativoException;
import com.starking.moneyapi.model.Lancamento;
import com.starking.moneyapi.model.Pessoa;
import com.starking.moneyapi.repositories.LancamentoRepository;
import com.starking.moneyapi.repositories.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@SuppressWarnings("unchecked")
	public Page<Lancamento> findAll() {
		return (Page<Lancamento>) this.lancamentoRepository.findAll();
	}

	@Transactional
	public Lancamento criar(Lancamento lancamento) {
		Pessoa pessoa = this.pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativoException();
		}
		return this.lancamentoRepository.save(lancamento);
	}

	public Lancamento buscarPeloCodigo(Long codigo) {
		return this.lancamentoRepository.findOne(codigo);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalva = atualizarLancamento(codigo);
		BeanUtils.copyProperties(lancamento, lancamentoSalva, "codigo");
		return this.lancamentoRepository.save(lancamentoSalva);		
	}
	
	@Transactional(readOnly = true)
	public void deletar(Long codigo) {
		this.lancamentoRepository.deleteById(codigo);
	}
	
	private Lancamento atualizarLancamento(Long codigo) {
		Lancamento lancamentoSalva = this.lancamentoRepository.findOne(codigo);
		if(lancamentoSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalva;
	}
}
