package com.recodepro.conectatrabajoapi.api.repositories;

import com.recodepro.conectatrabajoapi.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca por email (usado para login)
    Optional<Usuario> findByEmail(String email);

    // Verifica se existe um usuário com o CPF
    boolean existsByCpf(String cpf);

    // Verifica se existe um usuário com o RNE
    boolean existsByRne(String rne);

    // Busca por CPF
    Optional<Usuario> findByCpf(String cpf);

    // Busca por RNE
    Optional<Usuario> findByRne(String rne);
}