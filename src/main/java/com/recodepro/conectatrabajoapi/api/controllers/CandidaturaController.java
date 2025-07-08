package com.recodepro.conectatrabajoapi.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.recodepro.conectatrabajoapi.api.models.Candidatura;
import com.recodepro.conectatrabajoapi.api.repositories.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @PostMapping
    public ResponseEntity<Candidatura> criarCandidatura(@RequestBody Candidatura candidatura) {
        return ResponseEntity.ok(candidaturaRepository.save(candidatura));
    }

    @GetMapping("/vaga/{vagaId}")
    public List<Candidatura> listarPorVaga(@PathVariable Long vagaId) {
        return candidaturaRepository.findByVaga_IdVaga(vagaId);
    }
}