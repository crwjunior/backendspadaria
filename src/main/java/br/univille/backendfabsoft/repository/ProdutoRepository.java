package br.univille.backendfabsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.univille.backendfabsoft.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}