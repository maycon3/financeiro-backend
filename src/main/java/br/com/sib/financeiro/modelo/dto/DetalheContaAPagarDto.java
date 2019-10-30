package br.com.sib.financeiro.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sib.financeiro.modelo.Cartao;
import br.com.sib.financeiro.modelo.ContaAPagar;

public class DetalheContaAPagarDto {
	
	private Integer id;
	private Integer parcelas;
	private BigDecimal valorApagar;
	private BigDecimal valorPago;
	private LocalDate dataVencimento;
	private LocalDate dataPago;
	private String contaPaga;
	private String categoria;
	private String funcionario;
	private List<Cartao> cartoes;
	private String conta;
	
	public DetalheContaAPagarDto(ContaAPagar apagar) {
		this.id = apagar.getId();
		this.parcelas = apagar.getParcelas();
		this.valorApagar = apagar.getValorApagar();
		this.valorPago = apagar.getValorPago();
		this.dataVencimento = apagar.getDataVencimento();
		this.dataPago = apagar.getDataPago();
		this.contaPaga =(apagar.isContaPaga() == true)? "Pago": "Não Pago";
		this.categoria = apagar.getCategoria().getDescricaoCodigo();
		this.funcionario = (apagar.getFuncionario() == null) ? null: apagar.getFuncionario().getNome();
		this.cartoes = new ArrayList<>();
		this.cartoes = apagar.getCartoes();
		this.conta = apagar.getConta().getBanco();
	}

	public Integer getId() {
		return id;
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

	public String getContaPaga() {
		return contaPaga;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public String getConta() {
		return conta;
	}

	
}
