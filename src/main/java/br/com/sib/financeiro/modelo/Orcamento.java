package br.com.sib.financeiro.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orcamento")
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 70, nullable = true)
	private String nome;
	
	@Column(precision = 10, scale = 2, nullable = true)
	private BigDecimal valorOrcado;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	
	@Deprecated
	public Orcamento() {
	}

	public Orcamento(Integer id, String nome, BigDecimal valorOrcado) {
		this.id = id;
		this.nome = nome;
		this.valorOrcado = valorOrcado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(BigDecimal valorOrcado) {
		this.valorOrcado = valorOrcado;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
