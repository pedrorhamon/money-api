package com.starking.moneyapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.moneyapi.model.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{

}
