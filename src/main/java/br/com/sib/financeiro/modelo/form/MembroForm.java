package br.com.sib.financeiro.modelo.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public class MembroForm {

	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 70 , message = "O Campo tem que ter entre 5 à 70 caracteres")
	private String nome;
	
	@NonNull
	@Email
	private String email;
	
	@NonNull
	private LocalDate dataNascimento;
	
	@NonNull
	@NotEmpty 
	@Length(min = 2, max = 5 , message = "O Campo tem que ter entre 2 à 5 caracteres")
	private String organizador;
	
	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 30 , message = "O Campo tem que ter entre 5 à 30 caracteres")
	private String rua;
	
	@NonNull
	@NotEmpty 
	@Length(min = 2, max = 9 , message = "O Campo tem que ter entre 2 à 9 caracteres")
	private String numero;
	
	@Length(min = 5, max = 20 , message = "O Campo tem que ter entre 5 à 20 caracteres")
	private String complemento;
	
	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 30 , message = "O Campo tem que ter entre 5 à 30 caracteres")
	private String bairro;
	
	@NonNull
	@NotEmpty 
	@Length(min = 8, max = 9 , message = "O Campo tem que ter entre 8 à 9 caracteres")
	private String cep;
	
	private List<String> telefones;
	
	

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
