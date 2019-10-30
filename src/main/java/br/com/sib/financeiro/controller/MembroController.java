package br.com.sib.financeiro.controller;

import java.net.URI;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sib.financeiro.modelo.Membro;
import br.com.sib.financeiro.modelo.ceps.BuscaCep;
import br.com.sib.financeiro.modelo.dto.DetalheMembroDto;
import br.com.sib.financeiro.modelo.dto.EnderecoCepDto;
import br.com.sib.financeiro.modelo.dto.MembroDto;
import br.com.sib.financeiro.modelo.form.AtualizacaoMembroForm;
import br.com.sib.financeiro.modelo.form.MembroForm;
import br.com.sib.financeiro.service.MembroService;


@RestController
@RequestMapping("membro")
public class MembroController {

	@Autowired
	private MembroService membroService;
	
	@PostMapping
	public ResponseEntity<MembroDto> salva(@Valid @RequestBody MembroForm form, UriComponentsBuilder uriBuilder){
		Membro membro = membroService.converte(form);
		membro = membroService.salva(membro);
		URI uri = uriBuilder.path("/membro/{id}").buildAndExpand(membro.getId()).toUri();
		return ResponseEntity.created(uri).body(new MembroDto(membro));		
	}
	
	@GetMapping(value = "/paginacao")
	public Page<MembroDto> paginacao(@RequestParam()Integer pagina,@RequestParam() Integer qtd,
			@RequestParam(required = false) String ordenacao, @RequestParam(required = false) LocalDate data){
		Page<Membro> membros = membroService.listaDeMembro(pagina, qtd, ordenacao, data);
		Page<MembroDto> dtos = membros.map(MembroDto :: new);
		return dtos;
	}
	
	@GetMapping(value = "/{id}")
	public DetalheMembroDto buscaPorId(@PathVariable() Integer id) {
		Membro membro = membroService.buscaPorId(id);
		return new DetalheMembroDto(membro);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualiza(@Valid @RequestBody AtualizacaoMembroForm form, @PathVariable() Integer id ){
		 membroService.atualiza(id, form);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/organiza")
	public boolean temEsseOrganizador(@RequestParam()String organizador) {
		boolean tem = membroService.temEsseOrganizador(organizador);
		return tem;
	}
	
	@GetMapping(value = "/buscaEmail")
	public boolean temEsseEmail(@RequestParam()String email) {
		boolean tem = membroService.temEsseEmail(email);
		return tem;
	}
	
	@GetMapping(value = "/quandedaDeMembro")
	public Integer quantidadeDeMembro() {
		Integer quantidade = membroService.quantidadeDeMembros();
		return quantidade;
	}
	
	@GetMapping(value = "/buscaCep")
	public EnderecoCepDto buscaPorCep(@RequestParam() String cep) {
		BuscaCep busca = new BuscaCep();
		return busca.buscaCepEndereco(cep);
	}
}
