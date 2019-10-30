package br.com.sib.financeiro.repositores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.ContaAPagar;

public interface ContaAPagarRepository extends JpaRepository<ContaAPagar, Integer> {
	
	
	Page<ContaAPagar> findByContaPaga(boolean contaPaga, Pageable paginacao);
	
	Page<ContaAPagar> findByContaPagaAndCategoriaCodigo(boolean contaPaga, String codigo, Pageable paginacao);
	
	

}
