package br.com.sib.financeiro.modelo.dto;

import br.com.sib.financeiro.modelo.Membro;

public class MembroDto {

	private Integer id;
	private String nome;
	private String email;
	private String organizador;
	
	public MembroDto(Membro membro) {
		this.id = membro.getId();
		this.nome = membro.getNome();
		this.email = membro.getEmail();
		this.organizador = membro.getOrganizador();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getOrganizador() {
		return organizador;
	}
	
	
}
