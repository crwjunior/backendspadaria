package br.univille.backendfabsoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.univille.backendfabsoft.model.Fornecedor;
import br.univille.backendfabsoft.repository.FornecedorRepository;

@Service
public class FornecedorService {
    @Autowired
    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return repository.findById(id);
    }

    public Fornecedor save(Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}