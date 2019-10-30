package br.com.sib.financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sib.financeiro.modelo.Categoria;
import br.com.sib.financeiro.modelo.Orcamento;
import br.com.sib.financeiro.modelo.form.AtualizacaoCategoriaForm;
import br.com.sib.financeiro.modelo.form.CategoriaForm;
import br.com.sib.financeiro.repositores.CategoriaRepository;
import br.com.sib.financeiro.repositores.OrcamentoRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public Categoria converte(CategoriaForm form) {
		Categoria categoria = new Categoria(null, form.getCodigo(), form.getDescricao());
		if (form.getCategoriaPai() != null) {
			Categoria cat = this.buscaPorId(form.getCategoriaPai());
			categoria.setCategoria(cat);
		}
		if (form.getNomeDoResponsavel() != null) {
			Orcamento orcamento = new Orcamento(null, form.getNomeDoResponsavel(), form.getValorDoOrcamento());
			categoria.setOrcamento(orcamento);
			orcamento.setCategoria(categoria);
		}
		return categoria;

	}

	public Categoria buscaPorId(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}

	@Transactional
	public Categoria salva(Categoria categoria) {
		categoria.setId(null);
		categoria = categoriaRepository.save(categoria);
		if (categoria.getOrcamento() != null) {
			orcamentoRepository.save(categoria.getOrcamento());
		}
		return categoria;
	}

	@Transactional
	public Categoria atualiza(AtualizacaoCategoriaForm form, Integer id) {
		Categoria categoria = this.buscaPorId(id);
		if (form.getCategoriaPai() != null) {
			Categoria catPai = this.buscaPorId(form.getCategoriaPai());
			categoria.setCategoria(catPai);
		}
		if (form.getNomeDoResponsavel() != null) {
			categoria.getOrcamento().setNome(form.getNomeDoResponsavel());
			categoria.getOrcamento().setValorOrcado(form.getValorDoOrcamento());
		}

		categoria.setDescricao(form.getDescricao());
		categoria.setCodigo(form.getCodigo());
		return categoria;
	}

	@Transactional
	public void exclui(Integer id) {
		this.buscaPorId(id);
		categoriaRepository.deleteById(id);
	}

	public Page<Categoria> listaCategoria(Integer pagina, Integer qtd, String ordenacao) {
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
		return categorias;
	}
}
