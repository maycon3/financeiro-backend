package br.com.sib.financeiro.modelo.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public class ContaBancariaForm {

	@NonNull
	@NotEmpty 
	@Length(max = 4 , message = "O Campo terá que ter no máximo 4 caracteres")
	private String agencia;
	
	@NonNull
	@NotEmpty 
	@Length(min = 5, max = 20 , message = "O Campo tem que ter entre 5 à 20 caracteres")
	private String banco;
	
	@NonNull
	@NotEmpty 
	@Length(max = 7 , message = "O Campo terá que ter no máximo 7 caracteres")
	private String conta;
	
	private BigDecimal saldo;
	

	public String getAgencia() {
		return agencia;
	}

	public String getBanco() {
		return banco;
	}

	public String getConta() {
		return conta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	
	
}
