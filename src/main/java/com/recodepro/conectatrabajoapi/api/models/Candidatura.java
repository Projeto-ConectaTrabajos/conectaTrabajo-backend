package com.recodepro.conectatrabajoapi.api.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Candidatura")
    private Long idCandidatura;

    private LocalDate dataEnvio;
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "Id_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Id_Vaga")
    private Vaga vaga;

    // Getters e Setters
    public Long getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(Long idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}