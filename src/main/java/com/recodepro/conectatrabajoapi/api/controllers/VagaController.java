package com.recodepro.conectatrabajoapi.api.controllers;

//package br.com.buildar.api.controllers;

import com.recodepro.conectatrabajoapi.api.models.Vaga;
import com.recodepro.conectatrabajoapi.api.repositories.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @GetMapping
    public List<Vaga> listarVagas() {
        return vagaRepository.findAll();
    }

    @PostMapping
    public Vaga criarVaga(@RequestBody Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    @PutMapping("/{id}")
    public Vaga atualizarVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        return vagaRepository.findById(id)
                .map(vaga -> {
                    vaga.setTitulo(vagaAtualizada.getTitulo());
                    // Atualize outros campos...
                    return vagaRepository.save(vaga);
                })
                .orElseGet(() -> {
                    vagaAtualizada.setIdVaga(id);
                    return vagaRepository.save(vagaAtualizada);
                });
    }

    @DeleteMapping("/{id}")
    public void deletarVaga(@PathVariable Long id) {
        vagaRepository.deleteById(id);
    }
}