package br.com.sib.financeiro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sib.financeiro.modelo.Funcionario;
import br.com.sib.financeiro.modelo.Membro;
import br.com.sib.financeiro.modelo.Pessoa;

@Service
public class HomeService {

	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private MembroService membroService;
	
	public List<Pessoa> aniversariantesDoMes(){
		List<Funcionario> funcionarios = funcionarioService.listaFuncionario();
		List<Membro> membros = membroService.listaMembro();
		List<Pessoa> pessoas = new ArrayList<>();
		LocalDate hoje = LocalDate.now();
		
		funcionarios.forEach(funcionario ->{
			LocalDate data = funcionario.getDataNascimento();
			if(hoje.isEqual(data)) {
				pessoas.add(funcionario);
			}
		});
		
		membros.forEach(membro ->{
			LocalDate data = membro.getDataNascimento();
			if(hoje.isEqual(data)) {
				pessoas.add(membro);
			}
		});
		
		return pessoas;
	}
}
