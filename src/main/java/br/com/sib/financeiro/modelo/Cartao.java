package br.com.sib.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class Cartao {
	
	@Column(name = "valor", nullable = true)
	private BigDecimal valorDaParcela;
	
	private Integer numeroParcelas;
	
	@Column(name = "data_vencimento", nullable = true)
	private LocalDate dataVencimento;
	
	@Column(name = "data_pagamento", nullable = true)
	private LocalDate dataPagamento;
	
	
	@Column(name = "parcelas_pagas")
	private boolean parcelasPagas = false;
	
	
	@Deprecated
	public Cartao() {
	}

	
	public Cartao(BigDecimal valorDaParcela, LocalDate dataVencimento, Integer numeroParcelas) {
		this.valorDaParcela = valorDaParcela;
		this.dataVencimento = dataVencimento;
		this.numeroParcelas = numeroParcelas;
	}

	

	public BigDecimal getValorDaParcela() {
		return valorDaParcela;
	}

	public void setValorDaParcela(BigDecimal valorDaParcela) {
		this.valorDaParcela = valorDaParcela;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public boolean isParcelasPagas() {
		return parcelasPagas;
	}

	public void setParcelasPagas(boolean parcelasPagas) {
		this.parcelasPagas = parcelasPagas;
	}

	




	




	
	
	
	
}
