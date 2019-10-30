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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sib.financeiro.modelo.ContaAPagar;
import br.com.sib.financeiro.modelo.dto.ContaAPagarDto;
import br.com.sib.financeiro.modelo.dto.DetalheContaAPagarDto;
import br.com.sib.financeiro.modelo.form.AtualizaContaAPagarForm;
import br.com.sib.financeiro.modelo.form.ContaAPagarForm;
import br.com.sib.financeiro.modelo.form.PagaParcelaContaAPagarForm;
import br.com.sib.financeiro.service.ContaAPagarService;

@RestController
@RequestMapping("apagar")
public class ContaAPagarController {

	@Autowired
	public ContaAPagarService apagarService;
	

	
	@PostMapping(value = "/pagamento")
	public ResponseEntity<ContaAPagarDto> salva(@Valid @RequestBody ContaAPagarForm form, UriComponentsBuilder uriBuilder){
		ContaAPagar apagar = this.apagarService.converte(form);
		apagar = this.apagarService.fazPagamento(apagar);	
		URI uri = uriBuilder.path("/apagar/{id}").buildAndExpand(apagar.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaAPagarDto(apagar));
	}
	
	@PostMapping(value = "/agendaPagamento")
	public ResponseEntity<ContaAPagarDto> agenda(@Valid @RequestBody ContaAPagarForm form, UriComponentsBuilder uriBuilder){
		ContaAPagar apagar = this.apagarService.converte(form);
		apagar = this.apagarService.agenda(apagar);
		URI uri = uriBuilder.path("/apagar/{id}").buildAndExpand(apagar.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaAPagarDto(apagar));
	} 
	
	@PutMapping(value = "/pagamentoDeParcela/{id}")
	public ResponseEntity<?> pagamentoDeParcela(@Valid @RequestBody PagaParcelaContaAPagarForm form,@PathVariable() Integer id){
		ContaAPagar apagar = new ContaAPagar();
		apagar = this.apagarService.pagaParcelaDoCartao(form, id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/paginacao")
	public Page<ContaAPagarDto> listaContaAPagar(@RequestParam()Integer pagina, @RequestParam() Integer qtd,
			@RequestParam() String ordenacao, @RequestParam(required = false) String codigo, 
			@RequestParam(required = false) boolean contaPaga){
		Page<ContaAPagar> apagar = this.apagarService.listaDeContasNaoPagas(pagina, qtd, ordenacao, contaPaga, codigo);
		Page<ContaAPagarDto> apagarDto = apagar.map(ContaAPagarDto :: new);
		return apagarDto;
	}
	
	@PutMapping(value = "/fazPagamento/{id}")
	public ResponseEntity<Void> fazPagamento(@RequestBody ContaAPagarForm form, @PathVariable() Integer id){
		ContaAPagar apagar = this.apagarService.buscaPorId(id);
		apagar.setValorPago(form.getValorPago());
		apagar = this.apagarService.fazPagamento(apagar);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}")
	public DetalheContaAPagarDto buscaPorId(@PathVariable() Integer id) {
		ContaAPagar apagar = this.apagarService.buscaPorId(id);
		return new DetalheContaAPagarDto(apagar);
	}
	
	
	//termina. A conta esta saindo como nula 
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody AtualizaContaAPagarForm form, @PathVariable() Integer id){
		System.out.println("conta é essa : " + form.getConta());
		ContaAPagar apagar = this.apagarService.atualiza(form, id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleta(@PathVariable() Integer id){
		this.apagarService.excluir(id);
		return ResponseEntity.ok().build();
		
	}
	
}
