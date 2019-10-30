package br.com.sib.financeiro.modelo.dto;

import java.math.BigDecimal;

import br.com.sib.financeiro.modelo.Lancamento;
import br.com.sib.financeiro.modelo.enums.TipoOferta;

public class LancamentoDto {

	private Integer id;
	private TipoOferta tipo;
	private String descricao;
	private String contaBancaria;
	private BigDecimal valorDoLancamento;
	private String nomeMembro;
	
	public LancamentoDto(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.tipo = lancamento.getTipo();
		this.descricao = lancamento.getDescricao();
		this.contaBancaria = lancamento.getConta().getBanco();
		this.valorDoLancamento = lancamento.getValorDoLancamento();
		this.nomeMembro = (lancamento.getMembro() == null) ? null: lancamento.getMembro().getNome();
	}

	public Integer getId() {
		return id;
	}

	public TipoOferta getTipo() {
		return tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public BigDecimal getValorDoLancamento() {
		return valorDoLancamento;
	}

	public String getNomeMembro() {
		return nomeMembro;
	}
	
	
}
