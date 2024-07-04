package br.univille.backendfabsoft.repository;

import br.univille.backendfabsoft.model.Funcionario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByUsuario(String usuario);
}