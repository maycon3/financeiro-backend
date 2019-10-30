package br.com.sib.financeiro.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
