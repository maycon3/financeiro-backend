package br.com.sib.financeiro.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sib.financeiro.modelo.exception.SaldoInsuficienteException;
import br.com.sib.financeiro.modelo.exception.ValorNegativoException;

@Entity
@Table(name = "contaBancaria")
public class ContaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 4 , nullable = false)
	private String agencia;

	@Column(precision = 10, scale = 2, nullable = true)
	private BigDecimal saldo;

	@Column(length = 20, nullable = false)
	private String banco;

	@Column(length = 9, nullable = false)
	private String conta;

	@Deprecated
	public ContaBancaria() {
	}

	public ContaBancaria(Integer id, String agencia, BigDecimal saldo, String banco, String conta) {
		this.id = id;
		this.agencia = agencia;
		this.saldo = saldo;
		this.banco = banco;
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void deposita(BigDecimal valor) {
		BigDecimal compara = new BigDecimal(0.0);
		Integer numero = valor.compareTo(compara);
		if (numero < 0 || numero == 0) {
			throw new ValorNegativoException("Valor que o senhor está tentando depositar é negativo!");
		} else {
			this.saldo = this.saldo.add(valor);
		}
	}

	public void saca(BigDecimal valor) {
		BigDecimal compara = new BigDecimal(0.0);
		Integer comparacaoParaSaque = this.saldo.compareTo(valor);
		if (comparacaoParaSaque < 0 || comparacaoParaSaque == 0) {
			throw new SaldoInsuficienteException("O Saldo está Insuficiente ou o valor digitado foi negativo!");
		}
		
		
		this.saldo = this.saldo.subtract(valor);
	}
	
	public void tranfere(ContaBancaria outraConta, BigDecimal valor) {
		this.saca(valor);
		outraConta.deposita(valor);
	}

}
