package br.univille.backendfabsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.backendfabsoft.model.Funcionario;
import br.univille.backendfabsoft.repository.FuncionarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

 
    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElse(null);
    }

    @PostMapping
    public Funcionario criarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}")
    public Funcionario atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionario.setId(funcionarioAtualizado.getId());
                funcionario.setNome(funcionarioAtualizado.getNome());
                funcionario.setUsuario(funcionarioAtualizado.getUsuario());
                funcionario.setAtivo(funcionarioAtualizado.getAtivo());
                return funcionarioRepository.save(funcionario);
            })
            .orElseGet(() -> {
                funcionarioAtualizado.setId(id);
                return funcionarioRepository.save(funcionarioAtualizado);
            });
    }

    @PutMapping("/senha/{id}")
    public Funcionario mudarSenhaFuncionario(@PathVariable Long id, @RequestBody String senha) {
        System.out.println(senha);
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionario.setSenha(senha);
                return funcionarioRepository.save(funcionario);
            })
            .orElse(null);
    }

    @PutMapping("/status/{id}")
    public Funcionario mudarStatusFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
            .map(funcionario -> {
                funcionario.setAtivo(funcionarioAtualizado.getAtivo());
                return funcionarioRepository.save(funcionario);
            })
            .orElseGet(() -> {
                funcionarioAtualizado.setId(id);
                return funcionarioRepository.save(funcionarioAtualizado);
            });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable Long id) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}