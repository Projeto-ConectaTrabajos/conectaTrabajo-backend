package com.recodepro.conectatrabajoapi.api.repositories;

import com.recodepro.conectatrabajoapi.api.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}