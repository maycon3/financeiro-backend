package br.com.sib.financeiro.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {

}
