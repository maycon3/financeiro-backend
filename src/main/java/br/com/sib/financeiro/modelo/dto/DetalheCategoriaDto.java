package br.com.sib.financeiro.modelo.dto;

import java.math.BigDecimal;

import br.com.sib.financeiro.modelo.Categoria;

public class DetalheCategoriaDto {

	private Integer id;
	private String descricao;
	private String categoriaPai;
	private String codigo;
	private String nomeDoResponsavel;
	private BigDecimal valorDoOrcamento;
	
	public DetalheCategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
		this.categoriaPai = (categoria.getCategoria() == null)? null : categoria.getCategoria().getDescricao();
		this.codigo = categoria.getCodigo();
		this.nomeDoResponsavel = (categoria.getOrcamento() == null)? null : categoria.getOrcamento().getNome();
		this.valorDoOrcamento = (categoria.getOrcamento() == null)? null : categoria.getOrcamento().getValorOrcado();
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoriaPai() {
		return categoriaPai;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNomeDoResponsavel() {
		return nomeDoResponsavel;
	}

	public BigDecimal getValorDoOrcamento() {
		return valorDoOrcamento;
	}
	
	
}
