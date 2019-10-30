package br.com.sib.financeiro.modelo.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import br.com.sib.financeiro.modelo.Dependente;
import br.com.sib.financeiro.modelo.enums.TipoUsuario;


public class AtualizacaoFuncionarioForm {

	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 70 , message = "O Campo tem que ter entre 5 à 70 caracteres")
	private String nome;
	
	@NonNull
	@Email
	private String email;
	private TipoUsuario tipo;
	
	private List<Dependente> dependetes;
	
	private List<String> telefones;
	
	@NonNull
	@NotEmpty
	@Length(min = 5, max = 30, message = "O Campo tem que ter entre 5 à 30 caracteres")
	private String rua;
	
	@NonNull
	@NotEmpty
	@Length(min = 2, max = 9, message = "O Campo tem que ter entre 2 à 9 caracteres")
	private String numero;
	
	@Length(min = 5, max = 20, message = "O Campo tem que ter entre 5 à 20 caracteres")
	private String complemento;
	
	@NonNull
	@NotEmpty
	@Length(min = 5, max = 30, message = "O Campo tem que ter entre 5 à 30 caracteres")
	private String bairro;
	
	@NonNull
	@NotEmpty
	@Length(max = 9, message = "O Campo tem que ter no maximo 9 caracteres")
	private String cep;
	
	
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public TipoUsuario getTipo() {
		return tipo;
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
	public List<Dependente> getDependetes() {
		return dependetes;
	}
	public List<String> getTelefones() {
		return telefones;
	}
	
	
	
}
