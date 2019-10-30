package br.com.sib.financeiro.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "membro")
public class Membro extends Pessoa {

	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 5, nullable = false)
	private String organizador;
	
	@Deprecated
	public Membro(){
	}

	public Membro(Integer id,String nome,String email,LocalDate dataNascimento,String organizador) {
		super(id,nome,email,dataNascimento);
		this.organizador = organizador;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	
		

}
