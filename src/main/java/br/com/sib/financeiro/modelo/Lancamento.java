package br.com.sib.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sib.financeiro.modelo.enums.TipoOferta;

@Entity
@Table(name = "lancamento")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20, nullable = true)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private TipoOferta tipo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataLancamento;
	
	@Column(precision = 10, scale = 2 , nullable = true)
	private BigDecimal valorDoLancamento;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private ContaBancaria conta;
	
	@ManyToOne
	@JoinColumn(name = "id_membro", nullable = true)
	private Membro membro;
	
	@Deprecated
	public Lancamento() {
	}

	public Lancamento(Integer id, String descricao, TipoOferta tipo, LocalDate dataLancamento,
			BigDecimal valorDoLancamento, ContaBancaria conta) {
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.dataLancamento = dataLancamento;
		this.valorDoLancamento = valorDoLancamento;
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoOferta getTipo() {
		return tipo;
	}

	public void setTipo(TipoOferta tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorDoLancamento() {
		return valorDoLancamento;
	}

	public void setValorDoLancamento(BigDecimal valorDoLancamento) {
		this.valorDoLancamento = valorDoLancamento;
	}

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}
	
	
	
	
}
