package br.com.sib.financeiro.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conta_aPagar")
public class ContaAPagar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 40, nullable = false)
	private String descricao;
	
	private Integer parcelas;
	
	@Column(name = "valor_apagar",precision = 10, scale = 2, nullable = false)
	private BigDecimal valorApagar;
	
	@Column(name = "valor_pago", precision = 10, scale = 2, nullable = true)
	private BigDecimal valorPago;
	
	@Column(name = "data_vencimento", nullable = false)
	private LocalDate dataVencimento;
	
	@Column(name = "data_pago", nullable = true)
	private LocalDate dataPago;
	
	@Column(name = "conta_paga")
	private boolean contaPaga = false;
	
	@OneToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@OneToOne
	@JoinColumn(name = "id_funcionario", nullable = true)
	private Funcionario funcionario;
	

	@ElementCollection
	@CollectionTable(name = "conta_cartao",
	joinColumns = @JoinColumn(name = "id_apagar"))
	@AttributeOverrides({@AttributeOverride(name = "numeroParcelas", column = @Column(name ="numero_parcelas", nullable = false))})
	private List<Cartao> cartoes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "id_conta", nullable = false)
	private ContaBancaria conta;
	
	@Deprecated
	public ContaAPagar() {
	}

	public ContaAPagar(Integer id, String descricao, BigDecimal valorApagar, BigDecimal valorPago,
			LocalDate dataVencimento, LocalDate dataPago, Integer parcelas, Categoria categoria, ContaBancaria conta) {
		this.id = id;
		this.descricao = descricao;
		this.valorApagar = valorApagar;
		this.valorPago = valorPago;
		this.dataVencimento = dataVencimento;
		this.dataPago = dataPago;
		this.parcelas = parcelas;
		this.categoria = categoria;
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

	public BigDecimal getValorApagar() {
		return valorApagar;
	}

	public void setValorApagar(BigDecimal valorApagar) {
		this.valorApagar = valorApagar;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPago() {
		return dataPago;
	}

	public void setDataPago(LocalDate dataPago) {
		this.dataPago = dataPago;
	}

	public boolean isContaPaga() {
		return contaPaga;
	}

	public void setContaPaga(boolean contaPaga) {
		this.contaPaga = contaPaga;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public List<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	
	
	
	
}
