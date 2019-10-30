package br.com.sib.financeiro.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sib.financeiro.modelo.Dependente;
import br.com.sib.financeiro.modelo.Endereco;
import br.com.sib.financeiro.modelo.Funcionario;
import br.com.sib.financeiro.modelo.Pessoa;
import br.com.sib.financeiro.modelo.enums.TipoUsuario;
import br.com.sib.financeiro.modelo.form.AtualizacaoFuncionarioForm;
import br.com.sib.financeiro.modelo.form.FuncionarioForm;
import br.com.sib.financeiro.repositores.DependenteRepository;
import br.com.sib.financeiro.repositores.EnderecoRepository;
import br.com.sib.financeiro.repositores.FuncionarioRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private DependenteRepository dependenteRepository;

	
	public Funcionario coverte(FuncionarioForm form) {
		Funcionario func =  new Funcionario(null, form.getNome(), form.getEmail(), form.getDataNascimento(), form.getCargo(),
				form.getTipo(), form.getSenha());
		Endereco endereco = new Endereco(null, form.getRua(), form.getNumero(), form.getComplemento(), form.getBairro(),
				 form.getCep(), func);
		func.adicionaTelefone(form.getTelefones());
		func.setEndereco(endereco);
		return func;
	}
	
	 
}
