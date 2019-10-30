package br.com.sib.financeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sib.financeiro.modelo.Endereco;
import br.com.sib.financeiro.modelo.Membro;
import br.com.sib.financeiro.modelo.form.AtualizacaoMembroForm;
import br.com.sib.financeiro.modelo.form.MembroForm;
import br.com.sib.financeiro.repositores.EnderecoRepository;
import br.com.sib.financeiro.repositores.MembroRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;

@Service
public class MembroService {

	@Autowired
	private MembroRepository membroRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Membro converte(MembroForm form) {
		Membro membro = new Membro(null, form.getNome(), form.getEmail(), form.getDataNascimento(),
				form.getOrganizador());
		Endereco endereco = new Endereco(null, form.getRua(), form.getNumero(), form.getComplemento(), form.getBairro(),
				form.getCep(), membro);
		membro.setEndereco(endereco);
		membro.adicionaTelefone(form.getTelefones());
		return membro;
	}

	public Membro buscaPorId(Integer id) {
		Optional<Membro> membro = membroRepository.findById(id);
		return membro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}

	public Page<Membro> listaDeMembro(Integer pagina, Integer qtd, String ordenacao, LocalDate data) {
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		if (data == null) {
			Page<Membro> membros = membroRepository.findAll(paginacao);
			return membros;
		} else {
			Page<Membro> membros = membroRepository.findByDataNascimento(data, paginacao);
			return membros;
		}
	}

	@Transactional
	public Membro salva(Membro membro) {
		membro.setId(null);
		membro = membroRepository.save(membro);
		enderecoRepository.save(membro.getEndereco());
		return membro;
	}

	@Transactional
	public Membro atualiza(Integer id, AtualizacaoMembroForm form) {
		Membro membro = this.buscaPorId(id);
		membro.setNome(form.getNome());
		membro.setEmail(form.getEmail());
		membro.getEndereco().setRua(form.getRua());
		membro.getEndereco().setNumero(form.getNumero());
		membro.getEndereco().setComplemento(form.getComplemento());
		membro.getEndereco().setBairro(form.getBairro());
		membro.getEndereco().setCep(form.getCep());
		membro.setTelefones(form.getTelefones());
		return membro;
	}

	public boolean temEsseOrganizador(String organizador) {
		Membro membro = membroRepository.findByOrganizador(organizador);
		if (membro == null) {
			return true;
		}
		return false;
	}

	public boolean temEsseEmail(String email) {
		Membro membro = membroRepository.findByEmail(email);
		if (membro == null) {
			return false;
		}
		return true;
	}

	public Integer quantidadeDeMembros() {
		List<Membro> membros = membroRepository.findAll();
		return membros.size();
	}
	
	public List<Membro> listaMembro(){
		return membroRepository.findAll();
	}
	
}
