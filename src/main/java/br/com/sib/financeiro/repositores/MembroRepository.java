package br.com.sib.financeiro.repositores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sib.financeiro.modelo.Membro;

public interface MembroRepository extends JpaRepository<Membro, Integer> {

	List<Membro> findByNome(String nome);
	
	Membro findByOrganizador(String organizador);
	
	Page<Membro> findByDataNascimento(LocalDate data, Pageable paginacao);
	
	
	Membro findByEmail(String email);
}
