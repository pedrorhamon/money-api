package com.starking.moneyapi.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.moneyapi.model.Pessoa;
import com.starking.moneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public List<Pessoa>  findAll(){
		 return this.pessoaService.findAll();
	}
	
	@PostMapping
	public Pessoa save(@Valid @RequestBody Pessoa pessoa) {
	return this.pessoaService.criar(pessoa);
	}
}
