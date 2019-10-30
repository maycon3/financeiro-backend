package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import br.com.sib.financeiro.service.validacao.CategoriaInsert;

@CategoriaInsert
public class CategoriaForm {

	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 30 , message = "O Campo tem que ter entre 5 à 30 caracteres")
	private String descricao;
	
	private Integer categoriaPai;
	
	@NonNull
	@NotEmpty
	@Length(max = 11, message = "O Campo tem que ter no maximo 11 caracteres")
	private String codigo;
	
	 
	@Length(min = 5, max = 70 , message = "O Campo tem que ter entre 5 à 70 caracteres")
	private String nomeDoResponsavel;
	
	private BigDecimal valorDoOrcamento;
	

	public String getDescricao() {
		return descricao;
	}

	public Integer getCategoriaPai() {
		return categoriaPai;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNomeDoResponsavel() {
		return nomeDoResponsavel;
	}

	public BigDecimal getValorDoOrcamento() {
		return valorDoOrcamento;
	}
	
	
}
