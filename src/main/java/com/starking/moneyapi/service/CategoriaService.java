package com.starking.moneyapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.starking.moneyapi.model.Categoria;
import com.starking.moneyapi.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
}
