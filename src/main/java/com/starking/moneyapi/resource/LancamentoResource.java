package com.starking.moneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starking.moneyapi.model.Lancamento;
import com.starking.moneyapi.service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public List<Lancamento>  findAll(){
		 return this.lancamentoService.findAll();
	}
	
	@SuppressWarnings("unused")
	@PostMapping
	public ResponseEntity<Lancamento> save(@Valid @RequestBody Lancamento lancamento, 
			HttpServletResponse response) {
		 Lancamento lancamentoNew = this.lancamentoService.criar(lancamento);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		 .buildAndExpand(lancamentoNew.getCategoria().getCodigo()).toUri();
		 response.setHeader("Location", uri.toASCIIString());	
		 if(lancamentoNew != null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.created(uri).body(lancamentoNew);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		this.lancamentoService.deletar(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, 
			@Valid @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalva = this.lancamentoService.atualizar(codigo, lancamento);
		return ResponseEntity.ok(lancamentoSalva);
		
	}
}
