package br.com.sib.financeiro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sib.financeiro.modelo.Cartao;
import br.com.sib.financeiro.modelo.Categoria;
import br.com.sib.financeiro.modelo.ContaAPagar;
import br.com.sib.financeiro.modelo.ContaBancaria;
import br.com.sib.financeiro.modelo.Funcionario;
import br.com.sib.financeiro.modelo.exception.ValorNegativoException;
import br.com.sib.financeiro.modelo.form.AtualizaContaAPagarForm;
import br.com.sib.financeiro.modelo.form.ContaAPagarForm;
import br.com.sib.financeiro.modelo.form.FazPagamentoForm;
import br.com.sib.financeiro.modelo.form.PagaParcelaContaAPagarForm;
import br.com.sib.financeiro.repositores.ContaAPagarRepository;
import br.com.sib.financeiro.service.exception.ObjectNotFoundException;
import br.com.sib.financeiro.service.exception.PagamentoException;

@Service
public class ContaAPagarService {

	@Autowired
	private ContaAPagarRepository apagarRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ContaBancariaService contaService;

	@Autowired
	private FuncionarioService funcionarioService;

	public ContaAPagar converte(ContaAPagarForm form) {
		ContaBancaria conta = this.contaService.buscaPorId(form.getConta());
		Categoria categoria = this.categoriaService.buscaPorId(form.getCategoria());

		ContaAPagar apagar = new ContaAPagar(null, form.getDescricao(), form.getValorApagar(), form.getValorPago(),
				form.getDataVencimento(), form.getDataPago(), form.getParcelas(), categoria, conta);
		if (form.getParcelas() != null) {
			apagar.setCartoes(this.criaParcelas(apagar));
		}
		if (form.getFuncionario() != null) {
			Funcionario funcionario = this.funcionarioService.buscaPorId(form.getFuncionario());
			apagar.setFuncionario(funcionario);
		}
		return apagar;
	}

	@Transactional
	public ContaAPagar agenda(ContaAPagar apagar) {
		apagar.setId(null);
		return this.apagarRepository.save(apagar);
	}

	private List<Cartao> criaParcelas(ContaAPagar apagar) {
		List<Cartao> cartoes = new ArrayList<>();
		BigDecimal divide = new BigDecimal(apagar.getParcelas());
		for (int i = 0; apagar.getParcelas() > i; i++) {
			BigDecimal valor = apagar.getValorApagar().divide(divide);
			LocalDate data = apagar.getDataVencimento().plusMonths((i + 1));
			Cartao cartao = new Cartao(valor, data, (i + 1));
			cartoes.add(cartao);
		}
		return cartoes;
	}

	public ContaAPagar buscaPorId(Integer id) {
		Optional<ContaAPagar> apagar = this.apagarRepository.findById(id);
		return apagar
				.orElseThrow(() -> new ObjectNotFoundException("Objeto de Contas à Pagar não encontro com id: " + id));
	}

	@Transactional
	public ContaAPagar pagaParcelaDoCartao(PagaParcelaContaAPagarForm form, Integer id) {
		ContaAPagar apagar = this.buscaPorId(id);
		apagar.getCartoes().forEach(cartao -> {
			boolean pago = cartao.isParcelasPagas();
			Integer parcela = cartao.getNumeroParcelas();

			if (pago != true && parcela == form.getParcela()) {
				cartao.setValorDaParcela(form.getValorPago());
				cartao.setDataPagamento(LocalDate.now());
				BigDecimal valor = apagar.getValorPago();
				apagar.setValorPago(valor.add(form.getValorPago()));
				this.contaService.saca(apagar.getConta(), cartao.getValorDaParcela());
				cartao.setParcelasPagas(true);
				this.verificaParcelasPagas(apagar);
			}
		});
		return this.apagarRepository.save(apagar);
	}

	private void verificaParcelasPagas(ContaAPagar apagar) {
		BigDecimal valorFinal = new BigDecimal(0.0);
		for (Cartao cartao : apagar.getCartoes()) {
			if (cartao.isParcelasPagas() == true) {
				valorFinal = valorFinal.add(cartao.getValorDaParcela());
			}
		}
		Integer compara = apagar.getValorApagar().compareTo(valorFinal);
		System.out.println("O valor da divida é esse mesmo: " + valorFinal);
		if (compara == 0) {
			apagar.setContaPaga(true);
		}
	}

	@Transactional
	public ContaAPagar fazPagamento(ContaAPagar apagar) {
		Integer maiorOuMenor = apagar.getValorPago().compareTo(apagar.getValorApagar());
		if (maiorOuMenor == -1) {
			throw new ValorNegativoException("O valor do pagamento é menor do que o valor da conta ");
		}
		if (apagar.isContaPaga() == true) {
			throw new PagamentoException("Essa conta já está paga");
		} else {
			apagar.setContaPaga(true);
			apagar.setDataPago(LocalDate.now());
			this.contaService.saca(apagar.getConta(), apagar.getValorPago());
			return this.apagarRepository.save(apagar);
		}

	}

	public Page<ContaAPagar> listaDeContasNaoPagas(Integer pagina, Integer qtd, String ordenacao, boolean contaPaga,
			String codigo) {
		Pageable paginacao = PageRequest.of(pagina, qtd, Direction.ASC, ordenacao);
		if (codigo == null) {
			Page<ContaAPagar> apagar = this.apagarRepository.findByContaPaga(contaPaga, paginacao);
			return apagar;
		} else {
			Page<ContaAPagar> apagar = this.apagarRepository.findByContaPagaAndCategoriaCodigo(contaPaga, codigo,
					paginacao);
			return apagar;
		}
	}

	@Transactional
	public ContaAPagar atualiza(AtualizaContaAPagarForm form, Integer id) {
		ContaAPagar apagar = this.buscaPorId(id);
		if (apagar.isContaPaga() == false) {
			ContaBancaria conta = this.contaService.buscaPorId(form.getConta());
			Categoria categoria = this.categoriaService.buscaPorId(form.getCategoria());
			apagar.setValorApagar(form.getValorApagar());
			apagar.setDataVencimento(form.getDataVencimento());
			apagar.setDescricao(form.getDescricao());
			apagar.setParcelas(form.getParcela());
			apagar.setConta(conta);
			apagar.setCategoria(categoria);
			if (apagar.getFuncionario() != null) {
				Funcionario funcionario = this.funcionarioService.buscaPorId(form.getFuncionario());
				apagar.setFuncionario(funcionario);
			}
			if (form.getParcela() != null) {
				apagar.setCartoes(this.criaParcelas(apagar));
			}
			return apagar;
		} else {
			return apagar;
		}
	}

	@Transactional
	public void excluir(Integer id) {
		ContaAPagar apagar = this.buscaPorId(id);
		if (apagar.isContaPaga() == false) {
			this.apagarRepository.deleteById(id);
		}
	}

}
