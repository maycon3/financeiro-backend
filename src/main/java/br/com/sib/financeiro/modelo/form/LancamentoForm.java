package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import br.com.sib.financeiro.modelo.enums.TipoOferta;

public class LancamentoForm {

	@Length(max = 20, message = "Campo poderá ter no máximo 20 caracteres")
	private String descricao;
	
	private TipoOferta tipo;
	
	private BigDecimal valorDoLancamento;
	
	private Integer conta;
	
	private Integer membro;
	

	public String getDescricao() {
		return descricao;
	}

	public TipoOferta getTipo() {
		return tipo;
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
