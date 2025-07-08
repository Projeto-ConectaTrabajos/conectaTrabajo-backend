package com.recodepro.conectatrabajoapi.api.repositories;

import com.recodepro.conectatrabajoapi.api.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByCnpj(String cnpj);
    Empresa findByEmail(String email);
}