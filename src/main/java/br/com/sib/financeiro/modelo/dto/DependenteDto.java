package br.com.sib.financeiro.modelo.dto;

import br.com.sib.financeiro.modelo.Dependente;

public class DependenteDto {

	private Integer id;
	private String funcionario;
	private String nome;
	
	public DependenteDto(Dependente dependente) {
		this.id = dependente.getId();
		this.funcionario = dependente.getFuncionario().getNome();
		this.nome = dependente.getNome();
	}

	public Integer getId() {
		return id;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public String getNome() {
		return nome;
	}
	
	
}
