package br.com.sib.financeiro.modelo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sib.financeiro.modelo.Membro;

public class DetalheMembroDto {

	private Integer id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private String organizador;
	
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private List<String> telefones;
	
	public DetalheMembroDto(Membro membro) {
		this.id = membro.getId();
		this.nome = membro.getNome();
		this.email = membro.getEmail();
		this.dataNascimento = membro.getDataNascimento();
		this.organizador = membro.getOrganizador();
		this.telefones = new ArrayList<>();
		this.telefones.addAll(membro.getTelefones());
		this.rua = membro.getEndereco().getRua();
		this.numero = membro.getEndereco().getNumero();
		this.complemento = membro.getEndereco().getComplemento();
		this.bairro = membro.getEndereco().getBairro();
		this.cep = membro.getEndereco().getCep();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getOrganizador() {
		return organizador;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public List<String> getTelefones() {
		return telefones;
	}
	
	
}
