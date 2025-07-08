package com.recodepro.conectatrabajoapi.api.controllers;

//package br.com.buildar.api.controllers;
import com.recodepro.conectatrabajoapi.api.models.Empresa;
import com.recodepro.conectatrabajoapi.api.repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    // GET: Listar todas as empresas
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    // POST: Criar uma nova empresa
    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    // PUT: Atualizar uma empresa por ID
    @PutMapping("/{id}")
    public Empresa atualizarEmpresa(
            @PathVariable Long id,
            @RequestBody Empresa empresaAtualizada
    ) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setNome(empresaAtualizada.getNome());
                    empresa.setCnpj(empresaAtualizada.getCnpj());
                    empresa.setEmail(empresaAtualizada.getEmail());
                    // Atualize outros campos conforme necessário...
                    return empresaRepository.save(empresa);
                })
                .orElseGet(() -> {
                    empresaAtualizada.setIdEmpresa(id);
                    return empresaRepository.save(empresaAtualizada);
                });
    }

    // DELETE: Remover uma empresa por ID
    @DeleteMapping("/{id}")
    public void deletarEmpresa(@PathVariable Long id) {
        empresaRepository.deleteById(id);
    }
}