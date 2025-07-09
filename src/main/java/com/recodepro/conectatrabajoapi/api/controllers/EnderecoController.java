package com.recodepro.conectatrabajoapi.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recodepro.conectatrabajoapi.api.models.Endereco;
import com.recodepro.conectatrabajoapi.api.repositories.EnderecoRepository;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Criar endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        // Validação de relacionamento (usuário OU empresa)
        if ((endereco.getUsuario() == null && endereco.getEmpresa() == null) ||
                (endereco.getUsuario() != null && endereco.getEmpresa() != null)) {
            return ResponseEntity.badRequest().build();
        }

        // Validação e formatação do CEP
        String cep = endereco.getCep().replace("-", "").replace(" ", "");
        if (cep.length() != 8 || !cep.matches("\\d+")) {
            return ResponseEntity.badRequest().build();
        }
        endereco.setCep(cep);

        return ResponseEntity.ok(enderecoRepository.save(endereco));
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
                    endereco.setComplemento(enderecoAtualizado.getComplemento());
                    endereco.setBairro(enderecoAtualizado.getBairro());
                    endereco.setCidade(enderecoAtualizado.getCidade());
                    endereco.setEstado(enderecoAtualizado.getEstado());
                    endereco.setCep(enderecoAtualizado.getCep());
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
}