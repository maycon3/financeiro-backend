package br.com.sib.financeiro.modelo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sib.financeiro.modelo.Dependente;
import br.com.sib.financeiro.modelo.Funcionario;
import br.com.sib.financeiro.modelo.enums.TipoUsuario;

public class DetalhesFuncionarioDto {

	private Integer id;
	
	private String nome;
	private String email;
	private LocalDate dataNascimento;
	private List<String> telefones;
	private String cargo;
	private TipoUsuario tipo;
	private String senha;
	private List<DependenteDto> dependentes;
	
	private Integer idEndereco;
	private String rua;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	
	public DetalhesFuncionarioDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.email = funcionario.getEmail();
		this.dataNascimento = funcionario.getDataNascimento();
		this.telefones = new ArrayList<>();
		this.telefones = funcionario.getTelefones();
		this.cargo = funcionario.getCargo();
		this.tipo = funcionario.getTipo();
		this.senha = funcionario.getSenha();
		this.idEndereco = funcionario.getEndereco().getId();
		this.rua = funcionario.getEndereco().getRua();
		this.complemento = funcionario.getEndereco().getComplemento();
		this.cep = funcionario.getEndereco().getCep();
		this.numero = funcionario.getEndereco().getNumero();
		this.bairro = funcionario.getEndereco().getBairro();
		this.dependentes = new ArrayList<>();
	
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

	public List<String> getTelefones() {
		return telefones;
	}

	public String getCargo() {
		return cargo;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public String getSenha() {
		return senha;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	
	
	
	
}
