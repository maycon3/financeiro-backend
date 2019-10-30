package br.com.sib.financeiro.service.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sib.financeiro.modelo.Pessoa;

public class Aniversariante {

	private List<Pessoa> anversariantes = new ArrayList<>();
	private LocalDate data = LocalDate.now();
	
	public List<Pessoa> listaDeAniversariantes(List<Pessoa> pessoas){
	  pessoas.forEach(pessoa->{
		  LocalDate dataPessoa = pessoa.getDataNascimento();
		  if(this.data.isEqual(dataPessoa)) {
			  this.anversariantes.add(pessoa);
		  }});
	  return anversariantes;	  
	}
}
