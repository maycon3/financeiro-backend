package br.com.sib.financeiro.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.sib.financeiro.controller.converte.ConverteData;
import br.com.sib.financeiro.modelo.ContaAPagar;

public class ContaAPagarDto {

	private Integer id;
	private String descricao;
	private String categoria;
	private String contaPaga;
	private BigDecimal valorApagar;
	private String dataVenciomento;
	
	public ContaAPagarDto(ContaAPagar conta) {
		this.id = conta.getId();
		this.descricao = conta.getDescricao();
		this.valorApagar = conta.getValorApagar();
		this.dataVenciomento = this.converteData(conta.getDataVencimento());
		this.contaPaga = (conta.isContaPaga() == true )	? "Pago" : "Não Pago";	
		this.categoria = conta.getCategoria().getDescricaoCodigo();
	}
	
	private String converteData(LocalDate data) {
		ConverteData conversor = new ConverteData();
		return conversor.formatoComBarra(data);
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getContaPaga() {
		return contaPaga;
	}

	public BigDecimal getValorApagar() {
		return valorApagar;
	}

	public String getDataVenciomento() {
		return dataVenciomento;
	}
	
	
}
