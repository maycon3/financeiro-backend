package br.com.sib.financeiro.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sib.financeiro.modelo.Funcionario;
import br.com.sib.financeiro.modelo.dto.DetalhesFuncionarioDto;
import br.com.sib.financeiro.modelo.dto.FuncionarioDto;
import br.com.sib.financeiro.modelo.form.AtualizacaoFuncionarioForm;
import br.com.sib.financeiro.modelo.form.FuncionarioForm;
import br.com.sib.financeiro.service.FuncionarioService;

@RestController
@CrossOrigin(origins  = "http://localhost:4200")
@RequestMapping(value = "funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping
	public ResponseEntity<FuncionarioDto> salva(@Valid @RequestBody FuncionarioForm form, UriComponentsBuilder uriBuilder){
		Funcionario funcionario = funcionarioService.converte(form);
		funcionario = funcionarioService.salva(funcionario);
		URI uri = uriBuilder.path("/funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDto(funcionario));		
	}
	
	@GetMapping
	public List<FuncionarioDto> listaFuncionario(){
		List<Funcionario> funcionarios = funcionarioService.listaFuncionario();
		return funcionarios.stream().map(FuncionarioDto :: new).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}")
	public DetalhesFuncionarioDto buscaPorId(@PathVariable Integer id){
		Funcionario funcionario = funcionarioService.buscaPorId(id);
		return new DetalhesFuncionarioDto(funcionario);
	}
	
	@PutMapping(value = "/{id}/atualiza")
	public ResponseEntity<FuncionarioDto> atualiza(@Valid @RequestBody AtualizacaoFuncionarioForm form, @PathVariable Integer id){
		Funcionario funcionario = funcionarioService.atuliza(id, form);
		return ResponseEntity.ok(new FuncionarioDto(funcionario));
	}
	
	@GetMapping(value = "/email")
	public boolean temEmail(@RequestParam() String email) {
		boolean tem = funcionarioService.temEmail(email);
		return tem;
	}
	
	@GetMapping(value = "/numeroDeFuncionario")
	public int numeroDeFuncionarios() {
		return funcionarioService.numeroDeFuncionario();
	}
}
