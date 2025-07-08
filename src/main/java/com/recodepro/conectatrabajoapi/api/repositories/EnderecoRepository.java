package com.recodepro.conectatrabajoapi.api.repositories;

import com.recodepro.conectatrabajoapi.api.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Busca por ID do usuário (note o EXATO nome do campo)
    //List<Endereco> findByUsuario_Id(Long usuarioId);
    List<Endereco> findByUsuario_IdUsuario(Long usuarioId);

    // Busca por ID da empresa (note o EXATO nome do campo)
    //List<Endereco> findByEmpresa_Id(Long empresaId);
    List<Endereco> findByEmpresa_IdEmpresa(Long empresaId);

    // Buscar por CEP
    List<Endereco> findByCep(String cep);

    // Buscar por cidade
    List<Endereco> findByCidade(String cidade);

    // Buscar por estado
    List<Endereco> findByEstado(String estado);
}