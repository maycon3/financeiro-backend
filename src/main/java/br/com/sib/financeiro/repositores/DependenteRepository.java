package br.com.sib.financeiro.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer> {

}
