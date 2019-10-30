package br.com.sib.financeiro.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sib.financeiro.modelo.ContaBancaria;
import br.com.sib.financeiro.modelo.dto.ContaBancariaDto;
import br.com.sib.financeiro.modelo.form.ContaBancariaForm;
import br.com.sib.financeiro.service.ContaBancariaService;

@RestController
@RequestMapping("conta")
public class ContaBancariaController {

	@Autowired
	private ContaBancariaService contaService;
	
	@PostMapping
	public ResponseEntity<ContaBancariaDto> salva(@Valid @RequestBody ContaBancariaForm form, UriComponentsBuilder uriBuilder){
		ContaBancaria conta = this.contaService.converte(form);
		conta = this.contaService.salva(conta);
		URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaBancariaDto(conta));		
	}
	
	@GetMapping(value = "/{id}")
	public ContaBancariaDto buscaPorId(@PathVariable Integer id) {
		ContaBancaria conta = this.contaService.buscaPorId(id);
		return new ContaBancariaDto(conta);
	}
}
