package br.com.sib.financeiro.modelo.dto;

import java.math.BigDecimal;

import br.com.sib.financeiro.modelo.ContaBancaria;

public class ContaBancariaDto {

	private Integer id;
	private String banco;
	private String agencia;
	private String conta;
	private BigDecimal saldo;
	
	public ContaBancariaDto(ContaBancaria conta) {
		this.id = conta.getId();
		this.agencia = conta.getAgencia();
		this.conta = conta.getConta();
		this.banco = conta.getBanco();
		this.saldo = conta.getSaldo();
	}

	public Integer getId() {
		return id;
	}

	public String getBanco() {
		return banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	
}
