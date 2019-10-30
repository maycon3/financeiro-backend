package br.com.sib.financeiro.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Integer> {

}
