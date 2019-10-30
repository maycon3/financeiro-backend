package br.com.sib.financeiro.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sib.financeiro.modelo.Categoria;
import br.com.sib.financeiro.modelo.dto.CategoriaDto;
import br.com.sib.financeiro.modelo.dto.DetalheCategoriaDto;
import br.com.sib.financeiro.modelo.form.CategoriaForm;
import br.com.sib.financeiro.service.CategoriaService;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<CategoriaDto> salva(@Valid @RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder){
		Categoria categoria = categoriaService.converte(form);
		categoria = categoriaService.salva(categoria);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@GetMapping(value = "/{id}")
	public DetalheCategoriaDto buscaPorId(@PathVariable Integer id) {
		Categoria categoria = categoriaService.buscaPorId(id);
		return new DetalheCategoriaDto(categoria);
	}
	
	@GetMapping(value = "/paginacao")
	public Page<CategoriaDto> listaDeCategorias(@RequestParam() Integer pagina,@RequestParam() Integer qtd,
			@RequestParam() String ordenacao){
		Page<Categoria> categorias = categoriaService.listaCategoria(pagina, qtd, ordenacao);
		Page<CategoriaDto> categoriaDto = categorias.map(CategoriaDto :: new);
		return categoriaDto;		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		this.categoriaService.exclui(id);
		return ResponseEntity.noContent().build();
	}
}
