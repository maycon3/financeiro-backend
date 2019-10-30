package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.sib.financeiro.modelo.Cartao;

public class ContaAPagarForm {

	private String descricao;
	
	private Integer parcelas;
	
	private BigDecimal valorApagar;
	private BigDecimal valorPago;
	
	private LocalDate dataVencimento;
	private LocalDate dataPago;
	
	private Integer categoria;
	private Integer funcionario;
	
	private Integer conta;
	
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public Integer getParcelas() {
		return parcelas;
	}
	public BigDecimal getValorApagar() {
		return valorApagar;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public LocalDate getDataPago() {
		return dataPago;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public Integer getFuncionario() {
		return funcionario;
	}
	
//	public BigDecimal getValorDaParcela() {
//		return valorDaParcela;
//	}
//	public Integer getNumeroParcelas() {
//		return numeroParcelas;
//	}
//	public LocalDate getDataDoVencimento() {
//		return dataDoVencimento;
//	}
//	public LocalDate getDataPagamento() {
//		return dataPagamento;
//	}
	
	public Integer getConta() {
		return conta;
	}
	
	
	
	
	
	
}
