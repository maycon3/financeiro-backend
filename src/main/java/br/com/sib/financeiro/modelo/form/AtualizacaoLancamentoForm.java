package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;

public class AtualizacaoLancamentoForm {

	
	private String descricao;
	
	private BigDecimal valorDoLancamento;
	
	private Integer conta;
	
	private Integer membro;
	

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValorDoLancamento() {
		return valorDoLancamento;
	}

	public Integer getConta() {
		return conta;
	}

	public Integer getMembro() {
		return membro;
	}
	
	
}
