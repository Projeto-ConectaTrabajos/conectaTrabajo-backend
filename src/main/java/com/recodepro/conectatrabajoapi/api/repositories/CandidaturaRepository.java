package com.recodepro.conectatrabajoapi.api.repositories;

import com.recodepro.conectatrabajoapi.api.models.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVaga_IdVaga(Long idVaga);
}