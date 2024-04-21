package com.example.agendatelefonica.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "coontato", schema = "desafio")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contatoId;

    @Column(name = "contato_nome", nullable = false, length = 100)
    private String contatoNome;

    @Column(name = "contato_email", length = 255)
    private String contatoEmail;

    @Column(name = "contato_celular", nullable = false, length = 11, unique = true)
    private String contatoCelular;

    @Column(name = "contato_telefone", length = 10)
    private String contatoTelefone;

    @Column(name = "contato_sn_favorito", length = 1)
    private char contatoSnFavorito;

    @Column(name = "contato_sn_ativo", length = 1)
    private char contatoSnAtivo;

    @Column(name = "contato_dh_cad", nullable = false, updatable = false)
    private LocalDateTime contatoDhCad;

    // Construtores, getters e setters abaixo
    @PrePersist
    protected void prePersist() {
        if (contatoDhCad == null) {
            contatoDhCad = LocalDateTime.now();
        }
    }

    public Contato() {
    }

    // Getters e setters

    public Long getContatoId() {
        return contatoId;
    }

    public void setContatoId(Long contatoId) {
        this.contatoId = contatoId;
    }

    public String getContatoNome() {
        return contatoNome;
    }

    public void setContatoNome(String contatoNome) {
        this.contatoNome = contatoNome;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
    }

    public String getContatoCelular() {
        return contatoCelular;
    }

    public void setContatoCelular(String contatoCelular) {
        this.contatoCelular = contatoCelular;
    }

    public String getContatoTelefone() {
        return contatoTelefone;
    }

    public void setContatoTelefone(String contatoTelefone) {
        this.contatoTelefone = contatoTelefone;
    }

    public char getContatoSnFavorito() {
        return contatoSnFavorito;
    }

    public void setContatoSnFavorito(char contatoSnFavorito) {
        this.contatoSnFavorito = contatoSnFavorito;
    }

    public char getContatoSnAtivo() {
        return contatoSnAtivo;
    }

    public void setContatoSnAtivo(char contatoSnAtivo) {
        this.contatoSnAtivo = contatoSnAtivo;
    }

    public LocalDateTime getContatoDhCad() {
        return contatoDhCad;
    }

    public void setContatoDhCad(LocalDateTime contatoDhCad) {
        this.contatoDhCad = contatoDhCad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return contatoId != null && contatoId.equals(contato.contatoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contatoId);
    }

    @Override
    public String toString() {
        return "Contato{" +
                "contatoId=" + contatoId +
                ", contatoNome='" + contatoNome + '\'' +
                ", contatoEmail='" + contatoEmail + '\'' +
                ", contatoCelular='" + contatoCelular + '\'' +
                ", contatoTelefone='" + contatoTelefone + '\'' +
                ", contatoSnFavorito=" + contatoSnFavorito +
                ", contatoSnAtivo=" + contatoSnAtivo +
                ", contatoDhCad=" + contatoDhCad +
                '}';
    }
}
