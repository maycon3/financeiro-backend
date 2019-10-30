package br.com.sib.financeiro.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sib.financeiro.modelo.ContaBancaria;
import br.com.sib.financeiro.modelo.form.ContaBancariaForm;
import br.com.sib.financeiro.repositores.ContaBancariaRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;

@Service
public class ContaBancariaService {

	@Autowired
	private ContaBancariaRepository contaRepository;
	
	public ContaBancaria converte(ContaBancariaForm form) {
		return  new ContaBancaria(null, form.getAgencia(),form.getSaldo(), form.getBanco(), form.getConta());		
	}
	
	public ContaBancaria buscaPorId(Integer id) {
		Optional<ContaBancaria> conta = contaRepository.findById(id);
		return conta.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com Id: "+ id));
	}
	
	public ContaBancaria salva(ContaBancaria conta) {
		conta.setId(null);
		return contaRepository.save(conta);
	}
	
	public ContaBancaria deposita(ContaBancaria conta, BigDecimal valor) {
		  conta.deposita(valor);
		  return contaRepository.save(conta);		 
	}
	
	public ContaBancaria saca(ContaBancaria conta, BigDecimal valor) {
		conta.saca(valor);
		return contaRepository.save(conta);
	}
	
	public ContaBancaria transfere(ContaBancaria conta, Integer id, BigDecimal valor) {
		ContaBancaria banco = this.buscaPorId(id);
		conta.tranfere(banco, valor);
		return contaRepository.save(conta);
	}
}
