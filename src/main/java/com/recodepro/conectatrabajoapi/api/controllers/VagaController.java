package com.recodepro.conectatrabajoapi.api.controllers;

import com.recodepro.conectatrabajoapi.api.models.Vaga;
import com.recodepro.conectatrabajoapi.api.repositories.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

        import java.util.List;

@RestController
@RequestMapping("/vagas")
//@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        return vagaRepository.findById(id)
                .map(vaga -> {
                    vaga.setTitulo(vagaAtualizada.getTitulo());
                    vaga.setLocalizacao(vagaAtualizada.getLocalizacao());
                    vaga.setCompetencias(vagaAtualizada.getCompetencias());
                    vaga.setDataPublicacao(vagaAtualizada.getDataPublicacao());
                    vaga.setModalidade(vagaAtualizada.getModalidade());
                    vaga.setRequisitos(vagaAtualizada.getRequisitos());
                    vaga.setBeneficios(vagaAtualizada.getBeneficios());
                    vaga.setResponsabilidade(vagaAtualizada.getResponsabilidade());
                    vaga.setDescricaoEmpresa(vagaAtualizada.getDescricaoEmpresa());
                    vaga.setDescricao(vagaAtualizada.getDescricao());
                    vaga.setSalario(vagaAtualizada.getSalario());

                    return ResponseEntity.ok(vagaRepository.save(vaga));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletarVaga(@PathVariable Long id) {
        vagaRepository.deleteById(id);
    }
}