package com.recodepro.conectatrabajoapi.api.controllers;

import com.recodepro.conectatrabajoapi.api.models.Endereco;
import com.recodepro.conectatrabajoapi.api.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Criar endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoRepository.save(endereco));
    }

    // Listar todos endereços
    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar endereço
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setRua(enderecoAtualizado.getRua());
                    endereco.setNumero(enderecoAtualizado.getNumero());
                    // Atualize outros campos conforme necessário
                    return ResponseEntity.ok(enderecoRepository.save(endereco));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar endereços por usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<Endereco> listarPorUsuario(@PathVariable Long usuarioId) {
        return enderecoRepository.findByUsuario_IdUsuario(usuarioId);
    }

    // Buscar endereços por empresa
    @GetMapping("/empresa/{empresaId}")
    public List<Endereco> listarPorEmpresa(@PathVariable Long empresaId) {
        return enderecoRepository.findByEmpresa_IdEmpresa(empresaId);
    }

    // Buscar por CEP
    @GetMapping("/cep/{cep}")
    public List<Endereco> listarPorCep(@PathVariable String cep) {
        return enderecoRepository.findByCep(cep);
    }
}