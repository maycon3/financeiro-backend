package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AtualizaContaAPagarForm {
	
	private BigDecimal valorApagar;
	
	private String descricao;
	
	private LocalDate dataVencimento;
	
	private Integer categoria;
	
	private Integer parcela;
	
	private Integer funcionario;
	
	private Integer conta;

	public BigDecimal getValorApagar() {
		return valorApagar;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public Integer getParcela() {
		return parcela;
	}

	public Integer getFuncionario() {
		return funcionario;
	}

	public Integer getConta() {
		return conta;
	}
	
	

}
