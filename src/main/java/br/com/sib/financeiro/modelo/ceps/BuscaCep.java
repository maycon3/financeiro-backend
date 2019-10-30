package br.com.sib.financeiro.modelo.ceps;

import java.io.IOException;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import br.com.sib.financeiro.modelo.dto.EnderecoCepDto;

public class BuscaCep {

	public EnderecoCepDto buscaCepEndereco(String cep)  {
		ViaCEPClient cliente = new ViaCEPClient();
		ViaCEPEndereco endereco;
		try {
			endereco = cliente.getEndereco(cep);
			EnderecoCepDto dto = new EnderecoCepDto(endereco.getLogradouro(), endereco.getBairro());
			return dto;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
