package br.com.sib.financeiro.repositores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sib.financeiro.modelo.Lancamento;
import br.com.sib.financeiro.modelo.Membro;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

	@Query("Select l from Lancamento l where l.dataLancamento between ?1 and ?2")
	Page<Lancamento> findByPorPeriodo(LocalDate dataInicio, LocalDate dataFinal,Pageable paginacao);
	
	
	Page<Lancamento> findByMembro(Membro membro, Pageable paginacao);
	
	
}
