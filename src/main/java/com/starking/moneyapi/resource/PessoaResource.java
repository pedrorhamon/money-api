package com.starking.moneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starking.moneyapi.model.Categoria;
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
	public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa, 
			HttpServletResponse response) {
		 Pessoa pessoaNew = this.pessoaService.criar(pessoa);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		 .buildAndExpand(pessoaNew.getCategoria().getCodigo()).toUri();
		 response.setHeader("Location", uri.toASCIIString());	
		 if(pessoaNew != null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.created(uri).body(pessoaNew);
	}
}
