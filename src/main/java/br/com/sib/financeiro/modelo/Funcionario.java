package br.com.sib.financeiro.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.sib.financeiro.modelo.enums.TipoUsuario;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 30, nullable = false)
	private String cargo;
	
	private String senha; 
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	

	
	@Deprecated
	public Funcionario() {
	}
	
	public Funcionario(Integer id, String nome, String email, LocalDate dataNascimento, String cargo,TipoUsuario tipo, String senha) {
		super(id,nome,email,dataNascimento);
		this.cargo = cargo;
		this.tipo = tipo;
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	

	
	
	
	
	

}
