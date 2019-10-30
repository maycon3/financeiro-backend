package br.com.sib.financeiro.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sib.financeiro.modelo.ContaBancaria;
import br.com.sib.financeiro.modelo.Lancamento;
import br.com.sib.financeiro.modelo.Membro;
import br.com.sib.financeiro.modelo.form.AtualizacaoLancamentoForm;
import br.com.sib.financeiro.modelo.form.LancamentoForm;
import br.com.sib.financeiro.repositores.LancamentoRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ContaBancariaService contaService;

	@Autowired
	private MembroService membroService;

	public Lancamento converte(LancamentoForm form) {
		ContaBancaria conta = contaService.buscaPorId(form.getConta());
		if(form.getMembro() == null) {
			return new Lancamento(null, form.getDescricao(), form.getTipo(), null, form.getValorDoLancamento(),conta);
		} else {
			Membro membro = membroService.buscaPorId(form.getMembro());		
			Lancamento lancamento = new Lancamento(null, form.getDescricao(), form.getTipo(), null, form.getValorDoLancamento(),conta);
			lancamento.setMembro(membro);
			return lancamento;
		}
	}

	public Lancamento buscaPorId(Integer id) {
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		return lancamento.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com id: " + id));
	}

	@Transactional
	public Lancamento salva(Lancamento lancamento) {
		lancamento.setId(null);
		return lancamentoRepository.save(lancamento);
	}

	@Transactional
	public Lancamento atualiza(AtualizacaoLancamentoForm form, Integer id) {
		Lancamento lancamento = this.buscaPorId(id);
		ContaBancaria conta = contaService.buscaPorId(form.getConta());
		Membro membro = membroService.buscaPorId(form.getMembro());
		lancamento.setDescricao(form.getDescricao());
		lancamento.setValorDoLancamento(form.getValorDoLancamento());
		lancamento.setConta(conta);
		lancamento.setMembro(membro);
		return lancamento;
	}

	@Transactional
	public void excluir(Integer id) {
		this.buscaPorId(id);
		lancamentoRepository.deleteById(id);
	}

	public Page<Lancamento> listaDeLancamentosPorPeriodo(Integer pagina, Integer qtd, String ordenacao, LocalDate dataInicio,
			LocalDate dataFinal) {
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		if (dataInicio == null && dataFinal == null) {
			Page<Lancamento> lancamentos = lancamentoRepository.findAll(paginacao);
			return lancamentos;
		} else {
			Page<Lancamento> lancamentos = lancamentoRepository.findByPorPeriodo(dataInicio,dataFinal,paginacao);
			return lancamentos;
		}
	}
	
	public Page<Lancamento> lancamentosDoMembro(Integer pagina,Integer qtd,String ordenacao,Integer id){
		Membro membro = membroService.buscaPorId(id);
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		if(membro == null) {
			Page<Lancamento> lancamentos = lancamentoRepository.findAll(paginacao);
			return lancamentos;
		}else {
			Page<Lancamento> lancamentos = lancamentoRepository.findByMembro(membro, paginacao);
			return lancamentos;
		}
		
	}

}
