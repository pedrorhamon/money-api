package com.starking.moneyapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starking.moneyapi.model.Categoria;
import com.starking.moneyapi.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		return this.categoriaRepository.findAll();
	}
	
	@Transactional
	public Categoria criar(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
	}
	
	public Categoria buscarPeloCodigo(Long codigo) {
		return this.categoriaRepository.findOne(codigo);
	}
}
