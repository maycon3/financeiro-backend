package br.com.sib.financeiro.repositores;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	Optional<Funcionario> findByDataNascimento(LocalDate data);
	
	Optional<Funcionario> findByNome(String nome);
	
	Funcionario findByEmail(String email);
}
