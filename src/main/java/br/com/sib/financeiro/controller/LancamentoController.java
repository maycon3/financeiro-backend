package br.com.sib.financeiro.controller;

import java.net.URI;
import java.time.LocalDate;

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

import br.com.sib.financeiro.controller.converte.ConverteData;
import br.com.sib.financeiro.modelo.Lancamento;
import br.com.sib.financeiro.modelo.dto.LancamentoDto;
import br.com.sib.financeiro.modelo.form.LancamentoForm;
import br.com.sib.financeiro.service.ContaBancariaService;
import br.com.sib.financeiro.service.LancamentoService;

@RestController
@RequestMapping("lancamento")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ContaBancariaService contaService;
	
	@PostMapping
	public ResponseEntity<LancamentoDto> salva(@Valid @RequestBody LancamentoForm form,UriComponentsBuilder uriBuilder){
		Lancamento lancamento = this.lancamentoService.converte(form);
		LocalDate hoje = LocalDate.now();
		lancamento.setDataLancamento(hoje);
		this.contaService.deposita(lancamento.getConta(), lancamento.getValorDoLancamento());
		lancamento = this.lancamentoService.salva(lancamento);
		URI uri = uriBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new LancamentoDto(lancamento));		
	}
	
	@GetMapping(value = "/{id}")
	public LancamentoDto buscaPorId(@PathVariable Integer id) {
		Lancamento lancamento = this.lancamentoService.buscaPorId(id);
		return new LancamentoDto(lancamento);
	}
	
	@GetMapping(value = "/paginacao/membro")
	public Page<LancamentoDto> paginacaoLancamentoMembro(@RequestParam() Integer pagina,@RequestParam() Integer qtd,@RequestParam() String ordenacao,
			@RequestParam(required = false) Integer idMembro){
		Page<Lancamento> lancamentos = lancamentoService.lancamentosDoMembro(pagina, qtd, ordenacao, idMembro);
		Page<LancamentoDto> lancamentoDto = lancamentos.map(LancamentoDto :: new);
		return lancamentoDto;
	}
	
	@GetMapping(value = "/paginacao/periodo")
	public Page<LancamentoDto> listaDeLancamentosPorPeriodo(@RequestParam() Integer pagina,@RequestParam() Integer qtd,
			@RequestParam() String ordenacao, @RequestParam(required = false) LocalDate dataInicio,
			@RequestParam(required = false) LocalDate dataFinal){
		Page<Lancamento> lancamentos = this.lancamentoService.listaDeLancamentosPorPeriodo(pagina, qtd, ordenacao, dataInicio, dataFinal);
		Page<LancamentoDto> lancamentoDto = lancamentos.map(LancamentoDto :: new);
		return lancamentoDto;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		Lancamento lancamento = this.lancamentoService.buscaPorId(id);
		this.contaService.saca(lancamento.getConta(), lancamento.getValorDoLancamento());
		this.lancamentoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
