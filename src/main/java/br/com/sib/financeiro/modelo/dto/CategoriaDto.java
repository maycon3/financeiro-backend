package br.com.sib.financeiro.modelo.dto;

import br.com.sib.financeiro.modelo.Categoria;

public class CategoriaDto {

	private Integer id;
	private String categoriaPai;
	private String descricao;
	private String codigo;
	private String nomeDoResponsavel;
	
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.categoriaPai = (categoria.getCategoria() == null)? null : categoria.getCategoria().getDescricao();
		this.descricao = categoria.getDescricao();
		this.codigo = categoria.getCodigo();
		this.nomeDoResponsavel = (categoria.getOrcamento() == null)? null: categoria.getOrcamento().getNome();
	}

	public Integer getId() {
		return id;
	}

	public String getCategoriaPai() {
		return categoriaPai;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNomeDoResponsavel() {
		return nomeDoResponsavel;
	}
	
	
}
