package br.com.sib.financeiro.modelo.dto;

public class EnderecoCepDto {

	private String rua;
	private String bairro;
	
	
	public EnderecoCepDto(String rua, String bairro) {
		this.rua = rua;
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}
	
	
}
