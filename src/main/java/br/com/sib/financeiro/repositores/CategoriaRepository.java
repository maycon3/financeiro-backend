package br.com.sib.financeiro.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sib.financeiro.modelo.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	Categoria findByCodigo(String codigo);
	
	List<Categoria> findByCategoria(Categoria categoria);
	
	
}
