package com.starking.moneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.starking.moneyapi.model.Categoria;
import com.starking.moneyapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria>  findAll(){
		 return this.categoriaService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria, 
			HttpServletResponse response) {
		 Categoria categoriaNew = this.categoriaService.criar(categoria);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		 .buildAndExpand(categoriaNew.getCodigo()).toUri();
		 response.setHeader("Location", uri.toASCIIString());	
		 if(categoriaNew != null) {
			 return ResponseEntity.notFound().build();
		 }
		 return ResponseEntity.created(uri).body(categoriaNew);
	}
	
	@GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(Long codigo) {
		return this.categoriaService.buscarPeloCodigo(codigo);
	}
}
