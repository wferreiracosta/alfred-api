package br.com.wferreiracosta.loja.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.wferreiracosta.loja.domain.Categoria;
import br.com.wferreiracosta.loja.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	/**
	 * Faz consulta baseada em uma query
	 * 
	 * @param nome
	 * @param categorias
	 * @param pageRequest
	 * @return
	 */
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
			Pageable pageRequest);

	/**
	 * Faz a consulta sem a necessidade da query
	 * 
	 * @param nome
	 * @param categorias
	 * @param pageRequest
	 * @return
	 */
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,
			Pageable pageRequest);

}
