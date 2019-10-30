package br.com.sib.financeiro.modelo.dto;

import java.time.LocalDate;

import br.com.sib.financeiro.modelo.Funcionario;

public class FuncionarioDto {

	private Integer id;
	
	private String nome;
	
	private String email;
	
	private LocalDate dataNascimento;
	
	private String cargo;
	
	public FuncionarioDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.cargo = funcionario.getCargo();
		this.nome = funcionario.getNome();
		this.email = funcionario.getEmail();
		this.dataNascimento = funcionario.getDataNascimento();
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

	public String getCargo() {
		return cargo;
	}
	
	
}
