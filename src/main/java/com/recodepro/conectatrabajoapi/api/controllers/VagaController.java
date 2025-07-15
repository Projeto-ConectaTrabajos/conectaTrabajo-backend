package com.recodepro.conectatrabajoapi.api.controllers;

import com.recodepro.conectatrabajoapi.api.models.Vaga;
import com.recodepro.conectatrabajoapi.api.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    public ResponseEntity<?> criarVaga(@RequestBody Vaga vaga) {
        try {
            if (vaga.getTitulo() == null || vaga.getTitulo().isEmpty()) {
                return ResponseEntity.badRequest().body("Título é obrigatório");
            }

            if (vaga.getDataPublicacao() == null) {
                vaga.setDataPublicacao(LocalDateTime.now(ZoneOffset.UTC));
            }

            Vaga vagaSalva = vagaRepository.save(vaga);
            return ResponseEntity.ok(vagaSalva);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao criar vaga: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        try {
            return vagaRepository.findById(id)
                    .map(vaga -> {
                        vaga.setTitulo(vagaAtualizada.getTitulo());
                        vaga.setLocalizacao(vagaAtualizada.getLocalizacao());
                        vaga.setCompetencias(vagaAtualizada.getCompetencias());
                        vaga.setModalidade(vagaAtualizada.getModalidade());
                        vaga.setRequisitos(vagaAtualizada.getRequisitos());
                        vaga.setBeneficios(vagaAtualizada.getBeneficios());
                        vaga.setResponsabilidade(vagaAtualizada.getResponsabilidade());
                        vaga.setDescricaoEmpresa(vagaAtualizada.getDescricaoEmpresa());
                        vaga.setDescricao(vagaAtualizada.getDescricao());
                        vaga.setSalario(vagaAtualizada.getSalario());
                        vaga.setAreaAtuacao(vagaAtualizada.getAreaAtuacao());

                        return ResponseEntity.ok(vagaRepository.save(vaga));
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar vaga: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarVaga(@PathVariable Long id) {
        try {
            if (!vagaRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            vagaRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao deletar vaga: " + e.getMessage());
        }
    }
}